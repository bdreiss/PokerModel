package net.berndreiss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Card(CardSuite suite, CardValue value) implements Comparable<Card> {

    public static List<Card> getDeck() {

        return Stream.of(CardValue.values())
                .flatMap(cv -> Stream.of(CardSuite.values())
                        .map(cs -> new Card(cs, cv)))
                .collect(Collectors.toList());
    }

    public static List<Card> getDeckO() {

        return new ArrayList<>(Arrays.asList(
                new Card(CardSuite.HEARTS, CardValue.QUEEN),
                new Card(CardSuite.SPADES, CardValue.QUEEN),
                new Card(CardSuite.CLUBS, CardValue.QUEEN)/*,
                new Card(CardSuite.DIAMONDS, CardValue.ACE),
                new Card(CardSuite.HEARTS, CardValue.KING),
                new Card(CardSuite.SPADES, CardValue.KING),
                new Card(CardSuite.CLUBS, CardValue.KING),
                new Card(CardSuite.DIAMONDS, CardValue.KING)/*,
                new Card(CardSuite.HEARTS, CardValue.TEN),
                new Card(CardSuite.HEARTS, CardValue.NINE),
                new Card(CardSuite.HEARTS, CardValue.EIGHT),
                new Card(CardSuite.HEARTS, CardValue.SEVEN),
                new Card(CardSuite.HEARTS, CardValue.SIX),
                new Card(CardSuite.HEARTS, CardValue.FIVE),
                new Card(CardSuite.HEARTS, CardValue.FOUR),
                new Card(CardSuite.HEARTS, CardValue.THREE),
                new Card(CardSuite.HEARTS, CardValue.TWO),
                new Card(CardSuite.SPADES, CardValue.ACE),
                new Card(CardSuite.SPADES, CardValue.KING),
                new Card(CardSuite.SPADES, CardValue.QUEEN),
                new Card(CardSuite.SPADES, CardValue.JACK),
                new Card(CardSuite.SPADES, CardValue.TEN),
                new Card(CardSuite.SPADES, CardValue.NINE),
                new Card(CardSuite.SPADES, CardValue.EIGHT),
                new Card(CardSuite.SPADES, CardValue.SEVEN),
                new Card(CardSuite.SPADES, CardValue.SIX),
                new Card(CardSuite.SPADES, CardValue.FIVE),
                new Card(CardSuite.SPADES, CardValue.FOUR),
                new Card(CardSuite.SPADES, CardValue.THREE),
                new Card(CardSuite.SPADES, CardValue.TWO)
*/

        ));
    }

    @Override
    public String toString() {
        return value.toString() + suite;
    }

    @Override
    public int compareTo(Card o) {
        return this.value.ordinal() - o.value.ordinal();
    }
}
