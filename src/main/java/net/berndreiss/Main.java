package net.berndreiss;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        //org.openjdk.jmh.Main.main(args);
        //System.exit(0);

        List<CardValue> l2 = new ArrayList<>();
        l2.add(CardValue.ACE);
        List<CardValue> l1 = new ArrayList<>();
        l1.add(CardValue.KING);
        System.out.println(HandEvaluation.getCombinations(Card.getDeck(), 4).size());

//        HandEvaluation.getPreflopEquity(l1, StartingHandsType.PAIRED, l2, StartingHandsType.PAIRED);
        //HandEvaluation.getPreflopEquityParallel(l1, StartingHandsType.PAIRED, l2, StartingHandsType.PAIRED,1, true);


       Map<String, double[]> preflopEquities = HandEvaluation.getPreflopEquities();


        Range range = new Range((h1, h2) -> {
            StartingHands sh1 = StartingHands.determineStartingHand(h1);
            StartingHands sh2 = StartingHands.determineStartingHand(h2);
            assert sh1 != null;
            assert sh2 != null;

            if (preflopEquities.containsKey(sh1.getShortHand()+sh2.getShortHand())) {
                System.out.println(sh1);
                System.out.println(sh2);

                double[] result = preflopEquities.get(sh1.getShortHand() + sh2.getShortHand());
                System.out.println(result[1] - result[0]);
                return result[0] - result[1];
            }
            System.out.println(sh2);
            System.out.println(sh1);

            double[] result = preflopEquities.get(sh2.getShortHand() + sh1.getShortHand());
            System.out.println(result[0] - result[1]);
            return result[1] - result[0];
        }
        );

        for (int i=0; i < 6; i++){
            range.range[9][i] = 1;
        }

        for (int i=0; i < 4; i++){
            range.range[13][i] = 1;
        }

        for (int i=0; i < 12; i++){
            range.range[91][i] = 1;
        }

        List<Card> queens = new ArrayList<>();
        queens.add(new Card(CardSuite.SPADES, CardValue.QUEEN));
        queens.add(new Card(CardSuite.HEARTS, CardValue.QUEEN));


        range.handAgainstRange(queens);
    }

    private static List<Double> getStackSizes(){

        return new ArrayList<>(Arrays.asList(100.0, 200.0, 300.0, 400.0, 500.0, 600.0));

    }

    private static List<Double> getRaiseSizes(){
        return new ArrayList<>(Arrays.asList(
                0.33, 0.5
        ));

    }

    private static List<PlayerHistory> getHistories(){
        return new ArrayList<>(Arrays.asList(null, null, null, null, null, null));

    }

    @Fork(value = 1, warmups = 2)
    @Benchmark
    public void sequential(){
        List<CardValue> l2 = new ArrayList<>();
        l2.add(CardValue.ACE);
        List<CardValue> l1 = new ArrayList<>();
        l1.add(CardValue.KING);
        HandEvaluation.getPreflopEquity(l1, StartingHandsType.PAIRED, l2, StartingHandsType.PAIRED, false);


    }

    @Fork(value = 1, warmups = 2)
    @Benchmark
    public void parallelEight(){
        List<CardValue> l2 = new ArrayList<>();
        l2.add(CardValue.ACE);
        List<CardValue> l1 = new ArrayList<>();
        l1.add(CardValue.KING);
        HandEvaluation.getPreflopEquityParallel(l1, StartingHandsType.PAIRED, l2, StartingHandsType.PAIRED,8, false);


    }
    @Fork(value = 1, warmups = 2)
    @Benchmark
    public void parallelSixteen(){
        List<CardValue> l2 = new ArrayList<>();
        l2.add(CardValue.ACE);
        List<CardValue> l1 = new ArrayList<>();
        l1.add(CardValue.KING);
//        HandEvaluation.getPreflopEquityParallel(l1, StartingHandsType.PAIRED, l2, StartingHandsType.PAIRED,16, false);


    }
}