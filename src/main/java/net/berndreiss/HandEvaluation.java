package net.berndreiss;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HandEvaluation {

    public static List<Player> getWinner(List<Player> playerList) {
        List<Player> winners = new ArrayList<>();

        for (Player player : playerList) {
            if (winners.isEmpty()) {
                winners.add(player);
                continue;
            }

            int handComparison = player.getHand().compareTo(winners.get(0).getHand());

            if (handComparison > 0) {
                winners.clear();
                winners.add(player);
            }
            if (handComparison == 0)
                winners.add(player);


        }
        return winners;
    }

    public static HandValues getHandValue(Hand hand) {

        return getHandValue(hand.getCards());
    }

    private static HandValues getHandValue(List<Card> hand) {
        HandValues handValue = checkDistinct(hand);

        return handValue == HandValues.HIGH_CARD ? checkStraightFlush(hand) : handValue;
    }

    private static HandValues checkStraightFlush(List<Card> cards) {

        int beginning = 0;
        List<Card> cardsSorted = cards.stream().sorted().toList();
        int end = cardsSorted.size();

        //Treat special case for ACE being after a KING or before a TWO in a straight
        if (cardsSorted.get(cards.size() - 1).value() == CardValue.ACE && cardsSorted.get(0).value() == CardValue.TWO)
            end = cardsSorted.size() - 1;


        boolean isStraight = true;

        CardValue currentValue = cardsSorted.get(0).value();

        for (int i = 1; i < end; i++) {
            if (cardsSorted.get(i).value().ordinal() - currentValue.ordinal() != 1) {
                isStraight = false;
                break;
            }
            currentValue = cardsSorted.get(i).value();
        }

        boolean isFlush = true;
        CardSuite currentSuite = cardsSorted.get(0).suite();

        for (int i = 1; i < end; i++) {
            if (cardsSorted.get(i).suite() != currentSuite) {
                isFlush = false;
                break;
            }
        }


        if (isStraight && isFlush)
            return HandValues.STRAIGHT_FLUSH;
        if (isStraight)
            return HandValues.STRAIGHT;
        if (isFlush)
            return HandValues.FLUSH;
        return HandValues.HIGH_CARD;

    }


    private static HandValues checkDistinct(List<Card> cards) {

        HandValues handValue = HandValues.HIGH_CARD;

        List<Card> cardsSorted = cards.stream().sorted().toList();

        Card currentCard = cardsSorted.get(0);

        CardValue pairFound = null;
        CardValue threeFound = null;

        for (int i = 1; i < cardsSorted.size(); i++) {
            //if card is the same as the one before
            if (cardsSorted.get(i).value().equals(currentCard.value())) {
                if (handValue == HandValues.HIGH_CARD) {
                    handValue = HandValues.PAIR;
                    pairFound = cardsSorted.get(i).value();
                } else if (handValue == HandValues.PAIR) {
                    if (cardsSorted.get(i).value() == pairFound) {
                        handValue = HandValues.THREE_OF_A_KIND;
                        threeFound = cardsSorted.get(i).value();
                    } else
                        handValue = HandValues.TWO_PAIRS;
                } else if (handValue == HandValues.THREE_OF_A_KIND && cardsSorted.get(i).value() == threeFound)
                    handValue = HandValues.QUADS;
                else
                    handValue = HandValues.FULL_HOUSE;
            } else
                currentCard = cardsSorted.get(i);
        }

        return handValue;

    }

    public static int compareHands(Hand hand1, Hand hand2) {
        return compareHands(hand1.getCards(), hand2.getCards());
    }

    public static int compareHands(List<Card> hand1, List<Card> hand2) {
        HandValues hV1 = getHandValue(hand1);
        HandValues hV2 = getHandValue(hand2);

        if (hV1.ordinal() < hV2.ordinal())
            return -1;
        if (hV1.ordinal() > hV2.ordinal())
            return 1;

        return compareHighCard(hand1, hand2);

    }

    private static int compareHighCard(List<Card> hand1, List<Card> hand2) {


        Map<Long, List<CardValue>> cardValues1 = mapOccurrencesReverse(hand1);
        Map<Long, List<CardValue>> cardValues2 = mapOccurrencesReverse(hand2);

        for (Long l : cardValues1.keySet().stream().sorted(Comparator.reverseOrder()).toList()) {
            List<CardValue> list1 = cardValues1.get(l).stream().sorted(Comparator.reverseOrder()).toList();
            List<CardValue> list2 = cardValues2.get(l).stream().sorted(Comparator.reverseOrder()).toList();

            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i).ordinal() < list2.get(i).ordinal())
                    return -1;
                if (list1.get(i).ordinal() > list2.get(i).ordinal())
                    return 1;
            }
        }

        return 0;
    }

    private static Map<CardValue, Long> mapOccurrences(List<Card> cards) {
        return cards.stream().map(Card::value).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    }

    private static Map<Long, List<CardValue>> mapOccurrencesReverse(List<Card> cards) {
        return mapOccurrences(cards).entrySet().stream().collect(
                Collectors.groupingBy(
                        Map.Entry::getValue, Collectors.mapping(
                                Map.Entry::getKey, Collectors.toList())));
    }


    public static double getPreflopEquity(List<CardValue> values1, StartingHandsType type1, List<CardValue> values2, StartingHandsType type2, boolean print) {

        if (print) System.out.println("CHECKING: " +
                StartingHands.determineStartingHand(values1, type1) +
                " AGAINST " +
                StartingHands.determineStartingHand(values2, type2));
        List<Card> cards1 = getCards(values1, type1);
        List<Hand> combinations1 = getCombinations(cards1, 2).stream().map(l -> new Hand(l.get(0), l.get(1))).toList();

        if (type1 != StartingHandsType.PAIRED)
            combinations1 = combinations1.stream().filter(h -> h.getCards().get(0).value() != h.getCards().get(1).value()).toList();

        if (type1 == StartingHandsType.SUITED)
            combinations1 = combinations1.stream().filter(h -> h.getCards().get(0).suite() == h.getCards().get(1).suite()).toList();

        List<Card> cards2 = getCards(values2, type2);
        List<Hand> combinations2 = getCombinations(cards2, 2).stream().map(l -> new Hand(l.get(0), l.get(1))).toList();

        if (type2 != StartingHandsType.PAIRED)
            combinations2 = combinations2.stream().filter(h -> h.getCards().get(0).value() != h.getCards().get(1).value()).toList();

        if (type2 == StartingHandsType.SUITED)
            combinations2 = combinations2.stream().filter(h -> h.getCards().get(0).suite() == h.getCards().get(1).suite()).toList();

        List<Card> deck = Card.getDeck();

        List<List<Card>> combinations = getCombinations(deck, 3);

        double wins = 0;
        double losses = 0;
        double ties = 0;

        for (Hand h1 : combinations1) {
            for (Hand h2 : combinations2) {
                if (h1.getCards().contains(h2.getCards().get(0)) || h1.getCards().contains(h2.getCards().get(1)))
                    continue;

                outerloop:
                for (List<Card> combination : combinations) {
                    for (Card c : combination)
                        if (h1.getCards().contains(c) || h2.getCards().contains(c))
                            continue outerloop;

                    int result = compareHands(Hand.findBestHand(h1.getHandCards(), combination), Hand.findBestHand(h2.getHandCards(), combination));

                    if (result > 0)
                        wins++;
                    if (result < 0)
                        losses++;
                    if (result == 0)
                        ties++;
                    if ((wins + losses + ties) % 100000 == 0)
                        System.out.println(wins + losses + ties);
                }
            }
        }

        if (print) {
            System.out.println("WIN: " + wins / (wins + losses + ties));
            System.out.println("LOOSE: " + losses / (wins + losses + ties));
            System.out.println("TIE: " + ties / (wins + losses + ties));
            System.out.println("RUNS: " + (wins + losses + ties));
        }
        return 0;


    }

    public static double getPreflopEquityParallel(List<CardValue> values1, StartingHandsType type1, List<CardValue> values2, StartingHandsType type2, int threshold, boolean print) {
        if (print)
        System.out.println("CHECKING: " +
                StartingHands.determineStartingHand(values1, type1) +
                " AGAINST " +
                StartingHands.determineStartingHand(values2, type2));
        List<Card> cards1 = getCards(values1, type1);
        List<Hand> combinations1 = getCombinations(cards1, 2).stream().map(l -> new Hand(l.get(0), l.get(1))).toList();

        if (type1 != StartingHandsType.PAIRED)
            combinations1 = combinations1.stream().filter(h -> h.getCards().get(0).value() != h.getCards().get(1).value()).toList();

        if (type1 == StartingHandsType.SUITED)
            combinations1 = combinations1.stream().filter(h -> h.getCards().get(0).suite() == h.getCards().get(1).suite()).toList();

        List<Card> cards2 = getCards(values2, type2);
        List<Hand> combinations2 = getCombinations(cards2, 2).stream().map(l -> new Hand(l.get(0), l.get(1))).toList();

        if (type2 != StartingHandsType.PAIRED)
            combinations2 = combinations2.stream().filter(h -> h.getCards().get(0).value() != h.getCards().get(1).value()).toList();

        if (type2 == StartingHandsType.SUITED)
            combinations2 = combinations2.stream().filter(h -> h.getCards().get(0).suite() == h.getCards().get(1).suite()).toList();

        List<Card> deck = Card.getDeck();

        List<List<Card>> combinations = getCombinations(deck, 5);

        double wins = 0;
        double losses = 0;
        double ties = 0;

        for (Hand h1 : combinations1) {
            for (Hand h2 : combinations2) {
                if (h1.getCards().contains(h2.getCards().get(0)) || h1.getCards().contains(h2.getCards().get(1)))
                    continue;

                ForkJoinTask<double[]> task = new ParallelEquityCalculator(h1.getCards(), h2.getCards(), combinations, threshold);
                double[] result = new ForkJoinPool().invoke(task);

                wins += result[0];
                losses += result[1];
                ties += result[2];
                System.out.println("RUNS: " + (wins + losses + ties));

            }
        }

        if (print) {
            System.out.println("WIN: " + wins / (wins + losses + ties));
            System.out.println("LOOSE: " + losses / (wins + losses + ties));
            System.out.println("TIE: " + ties / (wins + losses + ties));
            System.out.println("RUNS: " + (wins + losses + ties));
        }

        return 0;
    }

    private static List<Card> getCards(List<CardValue> values, StartingHandsType type) {
        if (type == StartingHandsType.PAIRED)
            return Arrays.stream(CardSuite.values()).map(s -> new Card(s, values.get(0))).toList();
        return values.stream().flatMap(v -> Arrays.stream(CardSuite.values()).map(s -> new Card(s, v))).toList();
    }

    public static List<List<Card>> getCombinations(List<Card> universe, int k) {
        List<List<Card>> combinations = new ArrayList<>();
        if (k == 0) {
            combinations.add(new ArrayList<>());
            return combinations;
        }
        if (k > universe.size()) {
            return combinations; // return empty list
        }
        generateCombinations(universe, k, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    private static void generateCombinations(List<Card> universe, int k, int start, List<Card> current, List<List<Card>> combinations) {
        if (current.size() == k) {
            combinations.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < universe.size(); i++) {
            current.add(universe.get(i));
            generateCombinations(universe, k, i + 1, current, combinations);
            current.remove(current.size() - 1);
        }
    }
}
