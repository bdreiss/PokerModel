package net.berndreiss;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        HandEvaluation.getPreflopEquityParallel(l1, StartingHandsType.PAIRED, l2, StartingHandsType.PAIRED,1, true);

        Runtime.getRuntime().availableProcessors();
        List<Card> cardList = new ArrayList<>(Arrays.asList(
                new Card(CardSuite.HEARTS, CardValue.ACE),
                new Card(CardSuite.SPADES, CardValue.KING)
        ));

        System.out.println(StartingHands.determineStartingHand(cardList));

        List<Card> cardy = new ArrayList<>(Arrays.asList(new Card(CardSuite.DIAMONDS, CardValue.ACE)));
        Card card = new Card(CardSuite.HEARTS, CardValue.ACE);
        System.out.println(cardy.contains(card));

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