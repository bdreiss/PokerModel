package net.berndreiss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Hand  implements  Comparable<Hand>{

    private final List<Card> cards = new ArrayList<>();
    private final List<Card> handCards = new ArrayList<>();

    public Hand(Card card1, Card card2){
        cards.add(card1);
        cards.add(card2);
        handCards.addAll(cards);
        cards.sort(Card::compareTo);
    }

    public Hand(Card card1, Card card2, Card card3, Card card4, Card card5){
        cards.add(card1);
        cards.add(card2);
        handCards.addAll(cards);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.sort(Card::compareTo);
    }

    public Hand(List<Card> cards){
        assert cards.size() <= 5;
        assert cards.size() >= 2;

        handCards.add(cards.get(0));
        handCards.add(cards.get(1));

        this.cards.addAll(cards);
        cards.sort(Card::compareTo);
    }

    public void setFlop(List<Card> flop){
        setFlop(flop.get(0), flop.get(1), flop.get(2));
    }

    public void setFlop(Card card1, Card card2, Card card3){

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        cards.sort(Card::compareTo);

    }

    public void setCommunityCards(List<Card> communityCards){

        if (communityCards.size() < 3)
            return;

        if (communityCards.size() == 3) {
            setFlop(communityCards);
            return;
        }

        cards.clear();
        cards.addAll(findBestHand(handCards, communityCards));
    }

    public static List<Card> findBestHand(List<Card> handCards, List<Card> communityCards){
        List<Card> availableCards = new ArrayList<>(communityCards);
        availableCards.addAll(handCards);
       return HandEvaluation.getCombinations(availableCards, 5).stream().max(HandEvaluation::compareHands).get();
    }

    public void resetHand(){
        cards.clear();
        cards.addAll(handCards);
    }

    public List<Card> getCards(){
        return cards;
    }

    public List<Card> getHandCards(){
        return handCards;
    }
    @Override
    public String toString(){
        return cards.stream().map(Card::toString).collect(Collectors.joining(", "));
    }

    @Override
    public int compareTo(Hand o){
        return HandEvaluation.compareHands(this, o);
    }

}
