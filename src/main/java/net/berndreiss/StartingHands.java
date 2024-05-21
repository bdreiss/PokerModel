package net.berndreiss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum StartingHands {

    TWOS (CardValue.TWO, StartingHandsType.PAIRED),
    THREES (CardValue.THREE, StartingHandsType.PAIRED),
    FOURS (CardValue.FOUR, StartingHandsType.PAIRED),
    FIVES (CardValue.FIVE, StartingHandsType.PAIRED),
    SIXES (CardValue.SIX, StartingHandsType.PAIRED),
    SEVENS (CardValue.SEVEN, StartingHandsType.PAIRED),
    EIGHTS (CardValue.EIGHT, StartingHandsType.PAIRED),
    NINES (CardValue.NINE, StartingHandsType.PAIRED),
    TENS (CardValue.TEN, StartingHandsType.PAIRED),
    JACKS (CardValue.JACK, StartingHandsType.PAIRED),
    QUEENS (CardValue.QUEEN, StartingHandsType.PAIRED),
    KINGS (CardValue.KING, StartingHandsType.PAIRED),
    ACES (CardValue.ACE, StartingHandsType.PAIRED),
    ACE_KING_S (CardValue.ACE, CardValue.KING, StartingHandsType.SUITED),
    ACE_QUEEN_S (CardValue.ACE, CardValue.QUEEN, StartingHandsType.SUITED),
    ACE_JACK_S (CardValue.ACE, CardValue.JACK, StartingHandsType.SUITED),
    ACE_TEN_S (CardValue.ACE, CardValue.TEN, StartingHandsType.SUITED),
    ACE_NINE_S (CardValue.ACE, CardValue.NINE, StartingHandsType.SUITED),
    ACE_EIGHT_S (CardValue.ACE, CardValue.EIGHT, StartingHandsType.SUITED),
    ACE_SEVEN_S (CardValue.ACE, CardValue.SEVEN, StartingHandsType.SUITED),
    ACE_SIX_S (CardValue.ACE, CardValue.SIX, StartingHandsType.SUITED),
    ACE_FIVE_S (CardValue.ACE, CardValue.FIVE, StartingHandsType.SUITED),
    ACE_FOUR_S (CardValue.ACE, CardValue.FOUR, StartingHandsType.SUITED),
    ACE_THREE_S (CardValue.ACE, CardValue.THREE, StartingHandsType.SUITED),
    ACE_TWO_S (CardValue.ACE, CardValue.TWO, StartingHandsType.SUITED),
    KING_QUEEN_S (CardValue.KING, CardValue.QUEEN, StartingHandsType.SUITED),
    KING_JACK_S (CardValue.KING, CardValue.JACK, StartingHandsType.SUITED),
    KING_TEN_S (CardValue.KING, CardValue.TEN, StartingHandsType.SUITED),
    KING_NINE_S (CardValue.KING, CardValue.NINE, StartingHandsType.SUITED),
    KING_EIGHT_S (CardValue.KING, CardValue.EIGHT, StartingHandsType.SUITED),
    KING_SEVEN_S (CardValue.KING, CardValue.SEVEN, StartingHandsType.SUITED),
    KING_SIX_S (CardValue.KING, CardValue.SIX, StartingHandsType.SUITED),
    KING_FIVE_S (CardValue.KING, CardValue.FIVE, StartingHandsType.SUITED),
    KING_FOUR_S (CardValue.KING, CardValue.FOUR, StartingHandsType.SUITED),
    KING_THREE_S (CardValue.KING, CardValue.THREE, StartingHandsType.SUITED),
    KING_TWO_S (CardValue.KING, CardValue.TWO, StartingHandsType.SUITED),
    QUEEN_JACK_S (CardValue.QUEEN, CardValue.JACK, StartingHandsType.SUITED),
    QUEEN_TEN_S (CardValue.QUEEN, CardValue.TEN, StartingHandsType.SUITED),
    QUEEN_NINE_S (CardValue.QUEEN, CardValue.NINE, StartingHandsType.SUITED),
    QUEEN_EIGHT_S (CardValue.QUEEN, CardValue.EIGHT, StartingHandsType.SUITED),
    QUEEN_SEVEN_S (CardValue.QUEEN, CardValue.SEVEN, StartingHandsType.SUITED),
    QUEEN_SIX_S (CardValue.QUEEN, CardValue.SIX, StartingHandsType.SUITED),
    QUEEN_FIVE_S (CardValue.QUEEN, CardValue.FIVE, StartingHandsType.SUITED),
    QUEEN_FOUR_S (CardValue.QUEEN, CardValue.FOUR, StartingHandsType.SUITED),
    QUEEN_THREE_S (CardValue.QUEEN, CardValue.THREE, StartingHandsType.SUITED),
    QUEEN_TWO_S (CardValue.QUEEN, CardValue.TWO, StartingHandsType.SUITED),
    JACK_TEN_S (CardValue.JACK, CardValue.TEN, StartingHandsType.SUITED),
    JACK_NINE_S (CardValue.JACK, CardValue.NINE, StartingHandsType.SUITED),
    JACK_EIGHT_S (CardValue.JACK, CardValue.EIGHT, StartingHandsType.SUITED),
    JACK_SEVEN_S (CardValue.JACK, CardValue.SEVEN, StartingHandsType.SUITED),
    JACK_SIX_S (CardValue.JACK, CardValue.SIX, StartingHandsType.SUITED),
    JACK_FIVE_S (CardValue.JACK, CardValue.FIVE, StartingHandsType.SUITED),
    JACK_FOUR_S (CardValue.JACK, CardValue.FOUR, StartingHandsType.SUITED),
    JACK_THREE_S (CardValue.JACK, CardValue.THREE, StartingHandsType.SUITED),
    JACK_TWO_S (CardValue.JACK, CardValue.TWO, StartingHandsType.SUITED),
    TEN_NINE_S (CardValue.TEN, CardValue.NINE, StartingHandsType.SUITED),
    TEN_EIGHT_S (CardValue.TEN, CardValue.EIGHT, StartingHandsType.SUITED),
    TEN_SEVEN_S (CardValue.TEN, CardValue.SEVEN, StartingHandsType.SUITED),
    TEN_SIX_S (CardValue.TEN, CardValue.SIX, StartingHandsType.SUITED),
    TEN_FIVE_S (CardValue.TEN, CardValue.FIVE, StartingHandsType.SUITED),
    TEN_FOUR_S (CardValue.TEN, CardValue.FOUR, StartingHandsType.SUITED),
    TEN_THREE_S (CardValue.TEN, CardValue.THREE, StartingHandsType.SUITED),
    TEN_TWO_S (CardValue.TEN, CardValue.TWO, StartingHandsType.SUITED),
    NINE_EIGHT_S (CardValue.NINE, CardValue.EIGHT, StartingHandsType.SUITED),
    NINE_SEVEN_S (CardValue.NINE, CardValue.SEVEN, StartingHandsType.SUITED),
    NINE_SIX_S (CardValue.NINE, CardValue.SIX, StartingHandsType.SUITED),
    NINE_FIVE_S (CardValue.NINE, CardValue.FIVE, StartingHandsType.SUITED),
    NINE_FOUR_S (CardValue.NINE, CardValue.FOUR, StartingHandsType.SUITED),
    NINE_THREE_S (CardValue.NINE, CardValue.THREE, StartingHandsType.SUITED),
    NINE_TWO_S (CardValue.NINE, CardValue.TWO, StartingHandsType.SUITED),
    EIGHT_SEVEN_S (CardValue.EIGHT, CardValue.SEVEN, StartingHandsType.SUITED),
    EIGHT_SIX_S (CardValue.EIGHT, CardValue.SIX, StartingHandsType.SUITED),
    EIGHT_FIVE_S (CardValue.EIGHT, CardValue.FIVE, StartingHandsType.SUITED),
    EIGHT_FOUR_S (CardValue.EIGHT, CardValue.FOUR, StartingHandsType.SUITED),
    EIGHT_THREE_S (CardValue.EIGHT, CardValue.THREE, StartingHandsType.SUITED),
    EIGHT_TWO_S (CardValue.EIGHT, CardValue.TWO, StartingHandsType.SUITED),
    SEVEN_SIX_S (CardValue.SEVEN, CardValue.SIX, StartingHandsType.SUITED),
    SEVEN_FIVE_S (CardValue.SEVEN, CardValue.FIVE, StartingHandsType.SUITED),
    SEVEN_FOUR_S (CardValue.SEVEN, CardValue.FOUR, StartingHandsType.SUITED),
    SEVEN_THREE_S (CardValue.SEVEN, CardValue.THREE, StartingHandsType.SUITED),
    SEVEN_TWO_S (CardValue.SEVEN, CardValue.TWO, StartingHandsType.SUITED),
    SIX_FIVE_S (CardValue.SIX, CardValue.FIVE, StartingHandsType.SUITED),
    SIX_FOUR_S (CardValue.SIX, CardValue.FOUR, StartingHandsType.SUITED),
    SIX_THREE_S (CardValue.SIX, CardValue.THREE, StartingHandsType.SUITED),
    SIX_TWO_S (CardValue.SIX, CardValue.TWO, StartingHandsType.SUITED),
    FIVE_FOUR_S (CardValue.FIVE, CardValue.FOUR, StartingHandsType.SUITED),
    FIVE_THREE_S (CardValue.FIVE, CardValue.THREE, StartingHandsType.SUITED),
    FIVE_TWO_S (CardValue.FIVE, CardValue.TWO, StartingHandsType.SUITED),
    FOUR_THREE_S (CardValue.FOUR, CardValue.THREE, StartingHandsType.SUITED),
    FOUR_TWO_S (CardValue.FOUR, CardValue.TWO, StartingHandsType.SUITED),
    THREE_TWO_S (CardValue.THREE, CardValue.TWO, StartingHandsType.SUITED),
    ACE_KING(CardValue.ACE, CardValue.KING, StartingHandsType.UNSUITED),
    ACE_QUEEN (CardValue.ACE, CardValue.QUEEN, StartingHandsType.UNSUITED),
    ACE_JACK (CardValue.ACE, CardValue.JACK, StartingHandsType.UNSUITED),
    ACE_TEN (CardValue.ACE, CardValue.TEN, StartingHandsType.UNSUITED),
    ACE_NINE (CardValue.ACE, CardValue.NINE, StartingHandsType.UNSUITED),
    ACE_EIGHT (CardValue.ACE, CardValue.EIGHT, StartingHandsType.UNSUITED),
    ACE_SEVEN (CardValue.ACE, CardValue.SEVEN, StartingHandsType.UNSUITED),
    ACE_SIX (CardValue.ACE, CardValue.SIX, StartingHandsType.UNSUITED),
    ACE_FIVE (CardValue.ACE, CardValue.FIVE, StartingHandsType.UNSUITED),
    ACE_FOUR (CardValue.ACE, CardValue.FOUR, StartingHandsType.UNSUITED),
    ACE_THREE (CardValue.ACE, CardValue.THREE, StartingHandsType.UNSUITED),
    ACE_TWO (CardValue.ACE, CardValue.TWO, StartingHandsType.UNSUITED),
    KING_QUEEN (CardValue.KING, CardValue.QUEEN, StartingHandsType.UNSUITED),
    KING_JACK (CardValue.KING, CardValue.JACK, StartingHandsType.UNSUITED),
    KING_TEN (CardValue.KING, CardValue.TEN, StartingHandsType.UNSUITED),
    KING_NINE (CardValue.KING, CardValue.NINE, StartingHandsType.UNSUITED),
    KING_EIGHT (CardValue.KING, CardValue.EIGHT, StartingHandsType.UNSUITED),
    KING_SEVEN (CardValue.KING, CardValue.SEVEN, StartingHandsType.UNSUITED),
    KING_SIX (CardValue.KING, CardValue.SIX, StartingHandsType.UNSUITED),
    KING_FIVE (CardValue.KING, CardValue.FIVE, StartingHandsType.UNSUITED),
    KING_FOUR (CardValue.KING, CardValue.FOUR, StartingHandsType.UNSUITED),
    KING_THREE (CardValue.KING, CardValue.THREE, StartingHandsType.UNSUITED),
    KING_TWO (CardValue.KING, CardValue.TWO, StartingHandsType.UNSUITED),
    QUEEN_JACK (CardValue.QUEEN, CardValue.JACK, StartingHandsType.UNSUITED),
    QUEEN_TEN (CardValue.QUEEN, CardValue.TEN, StartingHandsType.UNSUITED),
    QUEEN_NINE (CardValue.QUEEN, CardValue.NINE, StartingHandsType.UNSUITED),
    QUEEN_EIGHT (CardValue.QUEEN, CardValue.EIGHT, StartingHandsType.UNSUITED),
    QUEEN_SEVEN (CardValue.QUEEN, CardValue.SEVEN, StartingHandsType.UNSUITED),
    QUEEN_SIX (CardValue.QUEEN, CardValue.SIX, StartingHandsType.UNSUITED),
    QUEEN_FIVE (CardValue.QUEEN, CardValue.FIVE, StartingHandsType.UNSUITED),
    QUEEN_FOUR (CardValue.QUEEN, CardValue.FOUR, StartingHandsType.UNSUITED),
    QUEEN_THREE (CardValue.QUEEN, CardValue.THREE, StartingHandsType.UNSUITED),
    QUEEN_TWO (CardValue.QUEEN, CardValue.TWO, StartingHandsType.UNSUITED),
    JACK_TEN (CardValue.JACK, CardValue.TEN, StartingHandsType.UNSUITED),
    JACK_NINE (CardValue.JACK, CardValue.NINE, StartingHandsType.UNSUITED),
    JACK_EIGHT (CardValue.JACK, CardValue.EIGHT, StartingHandsType.UNSUITED),
    JACK_SEVEN (CardValue.JACK, CardValue.SEVEN, StartingHandsType.UNSUITED),
    JACK_SIX (CardValue.JACK, CardValue.SIX, StartingHandsType.UNSUITED),
    JACK_FIVE (CardValue.JACK, CardValue.FIVE, StartingHandsType.UNSUITED),
    JACK_FOUR (CardValue.JACK, CardValue.FOUR, StartingHandsType.UNSUITED),
    JACK_THREE (CardValue.JACK, CardValue.THREE, StartingHandsType.UNSUITED),
    JACK_TWO (CardValue.JACK, CardValue.TWO, StartingHandsType.UNSUITED),
    TEN_NINE (CardValue.TEN, CardValue.NINE, StartingHandsType.UNSUITED),
    TEN_EIGHT (CardValue.TEN, CardValue.EIGHT, StartingHandsType.UNSUITED),
    TEN_SEVEN (CardValue.TEN, CardValue.SEVEN, StartingHandsType.UNSUITED),
    TEN_SIX (CardValue.TEN, CardValue.SIX, StartingHandsType.UNSUITED),
    TEN_FIVE (CardValue.TEN, CardValue.FIVE, StartingHandsType.UNSUITED),
    TEN_FOUR (CardValue.TEN, CardValue.FOUR, StartingHandsType.UNSUITED),
    TEN_THREE (CardValue.TEN, CardValue.THREE, StartingHandsType.UNSUITED),
    TEN_TWO (CardValue.TEN, CardValue.TWO, StartingHandsType.UNSUITED),
    NINE_EIGHT (CardValue.NINE, CardValue.EIGHT, StartingHandsType.UNSUITED),
    NINE_SEVEN (CardValue.NINE, CardValue.SEVEN, StartingHandsType.UNSUITED),
    NINE_SIX (CardValue.NINE, CardValue.SIX, StartingHandsType.UNSUITED),
    NINE_FIVE (CardValue.NINE, CardValue.FIVE, StartingHandsType.UNSUITED),
    NINE_FOUR (CardValue.NINE, CardValue.FOUR, StartingHandsType.UNSUITED),
    NINE_THREE (CardValue.NINE, CardValue.THREE, StartingHandsType.UNSUITED),
    NINE_TWO (CardValue.NINE, CardValue.TWO, StartingHandsType.UNSUITED),
    EIGHT_SEVEN (CardValue.EIGHT, CardValue.SEVEN, StartingHandsType.UNSUITED),
    EIGHT_SIX (CardValue.EIGHT, CardValue.SIX, StartingHandsType.UNSUITED),
    EIGHT_FIVE (CardValue.EIGHT, CardValue.FIVE, StartingHandsType.UNSUITED),
    EIGHT_FOUR (CardValue.EIGHT, CardValue.FOUR, StartingHandsType.UNSUITED),
    EIGHT_THREE (CardValue.EIGHT, CardValue.THREE, StartingHandsType.UNSUITED),
    EIGHT_TWO (CardValue.EIGHT, CardValue.TWO, StartingHandsType.UNSUITED),
    SEVEN_SIX (CardValue.SEVEN, CardValue.SIX, StartingHandsType.UNSUITED),
    SEVEN_FIVE (CardValue.SEVEN, CardValue.FIVE, StartingHandsType.UNSUITED),
    SEVEN_FOUR (CardValue.SEVEN, CardValue.FOUR, StartingHandsType.UNSUITED),
    SEVEN_THREE (CardValue.SEVEN, CardValue.THREE, StartingHandsType.UNSUITED),
    SEVEN_TWO (CardValue.SEVEN, CardValue.TWO, StartingHandsType.UNSUITED),
    SIX_FIVE (CardValue.SIX, CardValue.FIVE, StartingHandsType.UNSUITED),
    SIX_FOUR (CardValue.SIX, CardValue.FOUR, StartingHandsType.UNSUITED),
    SIX_THREE (CardValue.SIX, CardValue.THREE, StartingHandsType.UNSUITED),
    SIX_TWO (CardValue.SIX, CardValue.TWO, StartingHandsType.UNSUITED),
    FIVE_FOUR (CardValue.FIVE, CardValue.FOUR, StartingHandsType.UNSUITED),
    FIVE_THREE (CardValue.FIVE, CardValue.THREE, StartingHandsType.UNSUITED),
    FIVE_TWO (CardValue.FIVE, CardValue.TWO, StartingHandsType.UNSUITED),
    FOUR_THREE (CardValue.FOUR, CardValue.THREE, StartingHandsType.UNSUITED),
    FOUR_TWO (CardValue.FOUR, CardValue.TWO, StartingHandsType.UNSUITED),
    THREE_TWO (CardValue.THREE, CardValue.TWO, StartingHandsType.UNSUITED),
    ;
    private final List<CardValue> cardValues = new ArrayList<>();
    private final StartingHandsType startingHandsType;

    StartingHands(CardValue cardValue, StartingHandsType type){
        cardValues.add(cardValue);
        startingHandsType = type;
    }

    StartingHands(CardValue cardValue1, CardValue cardValue2, StartingHandsType type){
        cardValues.add(cardValue1);
        cardValues.add(cardValue2);
        startingHandsType = type;
    }

    List<CardValue> getCardValues(){
        return cardValues;
    }

    StartingHandsType getStartingHandsType(){
        return startingHandsType;
    }

    static StartingHands determineStartingHand(List<Card> cards){
        if (cards.size() != 2)
            return null;
        if (cards.get(0).value() == cards.get(1).value())
            return StartingHands.values()[cards.get(0).value().ordinal()];

        List<CardValue> sortedCards = cards.stream().map(Card::value).sorted(Comparator.reverseOrder()).toList();

        if (cards.get(0).suite() == cards.get(1).suite())
            return Arrays.stream(StartingHands.values()).filter(sh -> sh.getCardValues().equals(sortedCards)).findAny().get();

        return Arrays.stream(StartingHands.values()).skip(91).filter(sh -> sh.getCardValues().equals(sortedCards)).findAny().get();

    }

    static StartingHands determineStartingHand(List<CardValue> values, StartingHandsType type){
        List<CardValue> valuesSorted = values.stream().sorted(Comparator.reverseOrder()).toList();
        return Arrays.stream(StartingHands.values()).filter(sh -> sh.getCardValues().equals(valuesSorted) && sh.getStartingHandsType() == type).findAny().get();
    }

}
