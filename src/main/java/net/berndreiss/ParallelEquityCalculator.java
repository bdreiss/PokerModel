package net.berndreiss;

import jdk.jfr.Threshold;

import java.util.List;
import java.util.concurrent.RecursiveTask;

import static net.berndreiss.HandEvaluation.compareHands;

public class ParallelEquityCalculator extends RecursiveTask<double[]> {

    private static int THRESHOLD;
    private static List<Card> hand1;
    private static List<Card> hand2;
    private static List<List<Card>> combinations;
    private final int start;
    private final int end;

    ParallelEquityCalculator(int start, int end){
        this.start = start;
        this.end = end;
    }

    ParallelEquityCalculator(List<Card> hand1, List<Card> hand2, List<List<Card>> combinations, int THRESHOLD){
        this.hand1 = hand1;
        this.hand2 = hand2;
        this.combinations = combinations;
        this.THRESHOLD = THRESHOLD;
        start = 0;
        end = combinations.size();
    }


    @Override
    protected double[] compute() {
        int length = end - start;
        if (length <= THRESHOLD)
            return computeSequentially();

        ParallelEquityCalculator leftTask = new ParallelEquityCalculator(start, start+length/2);
        leftTask.fork();
        ParallelEquityCalculator rightTask = new ParallelEquityCalculator(start+length/2, end);
        double[] rightResult = rightTask.compute();
        double[] leftResult = leftTask.join();
        return combineArrays(rightResult, leftResult);

    }

    private double[] computeSequentially(){
        double[] results = new double[]{0,0,0};
        outerloop:
        for (int i = start; i < end; i++) {
            List<Card> combination = combinations.get(i);
            for (Card c: combination)
                if (hand1.contains(c) || hand2.contains(c))
                    continue outerloop;

            int result = compareHands(Hand.findBestHand(hand1, combination), Hand.findBestHand(hand2, combination));

            if (result > 0)
                results[0]++;
            if (result < 0)
                results[1]++;
            if (result == 0)
                results[2]++;

        }
        return results;
    }

    private double[] combineArrays(double[] a1, double[] a2){
        return new double[]{a1[0]+a2[0], a1[1]+a2[1], a1[2]+a2[2]};

    }

}
