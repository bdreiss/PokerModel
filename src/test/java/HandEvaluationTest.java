import net.berndreiss.*;
import org.junit.Test;

import java.util.*;

public class HandEvaluationTest {

    private static final Map<HandValues, List<Hand>> handValueMap;

    static {
        handValueMap = new HashMap<>();
        handValueMap.put(HandValues.HIGH_CARD, new ArrayList<>(Arrays.asList(
                //NO HIGH CARD IN COMMON
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.TEN),
                        new Card(CardSuite.SPADES, CardValue.TWO),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.KING),
                        new Card(CardSuite.DIAMONDS, CardValue.NINE),
                        new Card(CardSuite.SPADES, CardValue.THREE),
                        new Card(CardSuite.HEARTS, CardValue.FOUR),
                        new Card(CardSuite.HEARTS, CardValue.SEVEN)
                ),
                //ONE HIGH CARD IN COMMON
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.TEN),
                        new Card(CardSuite.SPADES, CardValue.TWO),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.NINE),
                        new Card(CardSuite.SPADES, CardValue.THREE),
                        new Card(CardSuite.HEARTS, CardValue.FOUR),
                        new Card(CardSuite.HEARTS, CardValue.SEVEN)
                ),
                //TWO HIGH CARDS IN COMMON
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.TEN),
                        new Card(CardSuite.SPADES, CardValue.TWO),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.TEN),
                        new Card(CardSuite.SPADES, CardValue.THREE),
                        new Card(CardSuite.HEARTS, CardValue.FOUR),
                        new Card(CardSuite.HEARTS, CardValue.SEVEN)
                ),
                //THREE HIGH CARDS IN COMMON
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.TEN),
                        new Card(CardSuite.SPADES, CardValue.JACK),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.TEN),
                        new Card(CardSuite.SPADES, CardValue.JACK),
                        new Card(CardSuite.HEARTS, CardValue.FOUR),
                        new Card(CardSuite.HEARTS, CardValue.SEVEN)
                ),
                //FOUR HIGH CARDS IN COMMON
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.TEN),
                        new Card(CardSuite.SPADES, CardValue.JACK),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.TEN),
                        new Card(CardSuite.SPADES, CardValue.JACK),
                        new Card(CardSuite.HEARTS, CardValue.FOUR),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                //ALL HIGH CARDS IN COMMON
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.TEN),
                        new Card(CardSuite.SPADES, CardValue.JACK),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.TEN),
                        new Card(CardSuite.SPADES, CardValue.JACK),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                )

        )));
        handValueMap.put(HandValues.PAIR, new ArrayList<>(Arrays.asList(
                //ONE PAIR WINS
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.TWO),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.TEN),
                        new Card(CardSuite.DIAMONDS, CardValue.TEN),
                        new Card(CardSuite.SPADES, CardValue.THREE),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                //SAME PAIR NO HIGH CARDS IN COMMON
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.KING),
                        new Card(CardSuite.HEARTS, CardValue.QUEEN),
                        new Card(CardSuite.HEARTS, CardValue.JACK)
                ),
                new Hand(
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.CLUBS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.TWO),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                //SAME PAIR ONE HIGH CARD IN COMMON
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.KING),
                        new Card(CardSuite.HEARTS, CardValue.QUEEN),
                        new Card(CardSuite.HEARTS, CardValue.JACK)
                ),
                new Hand(
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.CLUBS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.KING),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                //SAME PAIR TWO HIGH CARDS IN COMMON
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.KING),
                        new Card(CardSuite.HEARTS, CardValue.QUEEN),
                        new Card(CardSuite.HEARTS, CardValue.JACK)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.KING),
                        new Card(CardSuite.HEARTS, CardValue.QUEEN),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                //SAME PAIR ALL HIGH CARDS IN COMMON
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.KING),
                        new Card(CardSuite.HEARTS, CardValue.QUEEN),
                        new Card(CardSuite.HEARTS, CardValue.JACK)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.KING),
                        new Card(CardSuite.HEARTS, CardValue.QUEEN),
                        new Card(CardSuite.HEARTS, CardValue.JACK)
                )
        )));
        handValueMap.put(HandValues.TWO_PAIRS, new ArrayList<>(Arrays.asList(
                //BOTH PAIRS ARE DIFFERENT
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.TWO),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.DIAMONDS, CardValue.FIVE)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.TEN),
                        new Card(CardSuite.DIAMONDS, CardValue.TEN),
                        new Card(CardSuite.SPADES, CardValue.FOUR),
                        new Card(CardSuite.HEARTS, CardValue.THREE),
                        new Card(CardSuite.DIAMONDS, CardValue.THREE)
                ),
                //LOWER PAIR IS EQUAL
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.TWO),
                        new Card(CardSuite.HEARTS, CardValue.THREE),
                        new Card(CardSuite.DIAMONDS, CardValue.THREE)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.TEN),
                        new Card(CardSuite.DIAMONDS, CardValue.TEN),
                        new Card(CardSuite.SPADES, CardValue.FOUR),
                        new Card(CardSuite.HEARTS, CardValue.THREE),
                        new Card(CardSuite.DIAMONDS, CardValue.THREE)
                ),
                //HIGHER PAIR IS EQUAL
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.TWO),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.DIAMONDS, CardValue.FIVE)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.FOUR),
                        new Card(CardSuite.HEARTS, CardValue.THREE),
                        new Card(CardSuite.DIAMONDS, CardValue.THREE)
                ),
                //HIGH CARD WINS
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.TEN),
                        new Card(CardSuite.HEARTS, CardValue.THREE),
                        new Card(CardSuite.DIAMONDS, CardValue.THREE)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.FOUR),
                        new Card(CardSuite.HEARTS, CardValue.THREE),
                        new Card(CardSuite.DIAMONDS, CardValue.THREE)
                ),
                //HIGH CARD IS EQUAL
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.TEN),
                        new Card(CardSuite.HEARTS, CardValue.THREE),
                        new Card(CardSuite.DIAMONDS, CardValue.THREE)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.TEN),
                        new Card(CardSuite.HEARTS, CardValue.THREE),
                        new Card(CardSuite.DIAMONDS, CardValue.THREE)
                )
        )));

        handValueMap.put(HandValues.THREE_OF_A_KIND, new ArrayList<>(Arrays.asList(
                //THREE OF A KIND WINS
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.TEN),
                        new Card(CardSuite.DIAMONDS, CardValue.TEN),
                        new Card(CardSuite.SPADES, CardValue.TEN),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                //THREE OF A KIND IS EQUAL BUT NO HIGH CARDS ARE EQUAL
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.TWO),
                        new Card(CardSuite.HEARTS, CardValue.THREE)
                ),
                //THREE OF A KIND AND ONE HIGH CARD ARE EQUAL
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.TWO),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                //ALL IS EQUAL
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                )


        )));

        handValueMap.put(HandValues.STRAIGHT, new ArrayList<>(Arrays.asList(
                //WINNING STRAIGHT
                new Hand(
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.CLUBS, CardValue.KING),
                        new Card(CardSuite.SPADES, CardValue.QUEEN),
                        new Card(CardSuite.SPADES, CardValue.JACK),
                        new Card(CardSuite.SPADES, CardValue.TEN)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.KING),
                        new Card(CardSuite.DIAMONDS, CardValue.QUEEN),
                        new Card(CardSuite.HEARTS, CardValue.JACK),
                        new Card(CardSuite.HEARTS, CardValue.TEN),
                        new Card(CardSuite.HEARTS, CardValue.NINE)
                ),
                //SAME STRAIGHT
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.KING),
                        new Card(CardSuite.HEARTS, CardValue.QUEEN),
                        new Card(CardSuite.HEARTS, CardValue.JACK),
                        new Card(CardSuite.HEARTS, CardValue.TEN)
                ),
                new Hand(
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.CLUBS, CardValue.KING),
                        new Card(CardSuite.SPADES, CardValue.QUEEN),
                        new Card(CardSuite.SPADES, CardValue.JACK),
                        new Card(CardSuite.SPADES, CardValue.TEN)
                )

        )));

        handValueMap.put(HandValues.FLUSH, new ArrayList<>(Arrays.asList(
                //FLUSH WITH NO EQUAL HIGH CARDS
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.KING),
                        new Card(CardSuite.HEARTS, CardValue.QUEEN),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.SPADES, CardValue.KING),
                        new Card(CardSuite.SPADES, CardValue.QUEEN),
                        new Card(CardSuite.SPADES, CardValue.JACK),
                        new Card(CardSuite.SPADES, CardValue.FIVE),
                        new Card(CardSuite.SPADES, CardValue.EIGHT)
                ),
                //FLUSH WITH ONE EQUAL HIGH CARD
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.KING),
                        new Card(CardSuite.HEARTS, CardValue.QUEEN),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.QUEEN),
                        new Card(CardSuite.SPADES, CardValue.JACK),
                        new Card(CardSuite.SPADES, CardValue.FIVE),
                        new Card(CardSuite.SPADES, CardValue.EIGHT)
                ),
                //FLUSH WITH TWO EQUAL HIGH CARDS
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.KING),
                        new Card(CardSuite.HEARTS, CardValue.QUEEN),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.KING),
                        new Card(CardSuite.SPADES, CardValue.JACK),
                        new Card(CardSuite.SPADES, CardValue.FIVE),
                        new Card(CardSuite.SPADES, CardValue.EIGHT)
                ),
                //FLUSH WITH THREE EQUAL HIGH CARDS
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.KING),
                        new Card(CardSuite.HEARTS, CardValue.QUEEN),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.KING),
                        new Card(CardSuite.SPADES, CardValue.QUEEN),
                        new Card(CardSuite.SPADES, CardValue.FOUR),
                        new Card(CardSuite.SPADES, CardValue.TWO)
                ),
                //FLUSH WITH FOUR EQUAL HIGH CARDS
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.KING),
                        new Card(CardSuite.HEARTS, CardValue.QUEEN),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.KING),
                        new Card(CardSuite.SPADES, CardValue.QUEEN),
                        new Card(CardSuite.SPADES, CardValue.EIGHT),
                        new Card(CardSuite.SPADES, CardValue.TWO)
                ),
                //FLUSH WITH FIVE EQUAL HIGH CARDS
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.KING),
                        new Card(CardSuite.HEARTS, CardValue.QUEEN),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.KING),
                        new Card(CardSuite.SPADES, CardValue.QUEEN),
                        new Card(CardSuite.SPADES, CardValue.FIVE),
                        new Card(CardSuite.SPADES, CardValue.EIGHT)
                )

        )));
        handValueMap.put(HandValues.FULL_HOUSE, new ArrayList<>(Arrays.asList(
                //THREE OF A KIND WINS
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.DIAMONDS, CardValue.FIVE)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.TEN),
                        new Card(CardSuite.DIAMONDS, CardValue.TEN),
                        new Card(CardSuite.SPADES, CardValue.TEN),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT),
                        new Card(CardSuite.DIAMONDS, CardValue.EIGHT)
                ),
                //PAIR WINS
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.DIAMONDS, CardValue.FIVE)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.TWO),
                        new Card(CardSuite.DIAMONDS, CardValue.TWO)
                ),
                //NO ONE WINS
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.DIAMONDS, CardValue.FIVE)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.FIVE),
                        new Card(CardSuite.DIAMONDS, CardValue.FIVE)
                )
        )));
        handValueMap.put(HandValues.QUADS, new ArrayList<>(Arrays.asList(
                //QUADS WIN
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.CLUBS, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.TEN),
                        new Card(CardSuite.DIAMONDS, CardValue.TEN),
                        new Card(CardSuite.SPADES, CardValue.TEN),
                        new Card(CardSuite.CLUBS, CardValue.TEN),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                //HIGH CARD WINS
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.CLUBS, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.CLUBS, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.SEVEN)
                ),
                //NO ONE WINS
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.CLUBS, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.DIAMONDS, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.CLUBS, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.EIGHT)
                )

        )));
        handValueMap.put(HandValues.STRAIGHT_FLUSH, new ArrayList<>(Arrays.asList(
                //WINNING STRAIGHT
                new Hand(
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.KING),
                        new Card(CardSuite.SPADES, CardValue.QUEEN),
                        new Card(CardSuite.SPADES, CardValue.JACK),
                        new Card(CardSuite.SPADES, CardValue.TEN)
                ),
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.KING),
                        new Card(CardSuite.HEARTS, CardValue.QUEEN),
                        new Card(CardSuite.HEARTS, CardValue.JACK),
                        new Card(CardSuite.HEARTS, CardValue.TEN),
                        new Card(CardSuite.HEARTS, CardValue.NINE)
                ),
                //SAME STRAIGHT
                new Hand(
                        new Card(CardSuite.HEARTS, CardValue.ACE),
                        new Card(CardSuite.HEARTS, CardValue.KING),
                        new Card(CardSuite.HEARTS, CardValue.QUEEN),
                        new Card(CardSuite.HEARTS, CardValue.JACK),
                        new Card(CardSuite.HEARTS, CardValue.TEN)
                ),
                new Hand(
                        new Card(CardSuite.SPADES, CardValue.ACE),
                        new Card(CardSuite.SPADES, CardValue.KING),
                        new Card(CardSuite.SPADES, CardValue.QUEEN),
                        new Card(CardSuite.SPADES, CardValue.JACK),
                        new Card(CardSuite.SPADES, CardValue.TEN)
                )

        )));
    }

    @Test
    public void testHighCardDetection(){
        for (Hand h: handValueMap.get(HandValues.HIGH_CARD))
            assert HandEvaluation.getHandValue(h) == HandValues.HIGH_CARD;
    }

    @Test
    public void testPairDetection(){
        for (Hand h: handValueMap.get(HandValues.PAIR))
            assert HandEvaluation.getHandValue(h) == HandValues.PAIR;
    }
    @Test
    public void testTwoPairDetection(){
        for (Hand h: handValueMap.get(HandValues.TWO_PAIRS))
            assert HandEvaluation.getHandValue(h) == HandValues.TWO_PAIRS;
    }

    @Test
    public void testThreeOfAKindDetection(){
        for (Hand h: handValueMap.get(HandValues.THREE_OF_A_KIND))
            assert HandEvaluation.getHandValue(h) == HandValues.THREE_OF_A_KIND;
    }
    @Test
    public void testStraightDetection(){
        for (Hand h: handValueMap.get(HandValues.STRAIGHT))
            assert HandEvaluation.getHandValue(h) == HandValues.STRAIGHT;
        Hand aceHigh = new Hand (
                new Card(CardSuite.HEARTS, CardValue.ACE),
                new Card(CardSuite.SPADES, CardValue.KING),
                new Card(CardSuite.HEARTS, CardValue.QUEEN),
                new Card(CardSuite.HEARTS, CardValue.JACK),
                new Card(CardSuite.HEARTS, CardValue.TEN)
                );
        Hand aceLow = new Hand (
                new Card(CardSuite.HEARTS, CardValue.ACE),
                new Card(CardSuite.SPADES, CardValue.TWO),
                new Card(CardSuite.HEARTS, CardValue.THREE),
                new Card(CardSuite.HEARTS, CardValue.FOUR),
                new Card(CardSuite.HEARTS, CardValue.FIVE)
        );

        assert HandEvaluation.getHandValue(aceHigh) == HandValues.STRAIGHT;
        assert HandEvaluation.getHandValue(aceLow) == HandValues.STRAIGHT;
    }

    @Test
    public void testFlushDetection(){
        for (Hand h: handValueMap.get(HandValues.FLUSH))
            assert HandEvaluation.getHandValue(h) == HandValues.FLUSH;
    }

    @Test
    public void testFullHouseDetection(){
        for (Hand h: handValueMap.get(HandValues.FULL_HOUSE))
            assert HandEvaluation.getHandValue(h) == HandValues.FULL_HOUSE;
    }

    @Test
    public void testQuadsDetection(){
        for (Hand h: handValueMap.get(HandValues.QUADS))
            assert HandEvaluation.getHandValue(h) == HandValues.QUADS;
    }

    @Test
    public void testStraightFlushDetection(){
        for (Hand h: handValueMap.get(HandValues.STRAIGHT_FLUSH))
            assert HandEvaluation.getHandValue(h) == HandValues.STRAIGHT_FLUSH;
    }

    private static void testHandEvaluation(HandValues hv) {

        List<Hand> list = handValueMap.get(hv);
        for (int i = 0; i < list.size(); i += 2) {

                if (i < list.size() - 2) {
                    assert list.get(i).compareTo(list.get(i + 1)) > 0;
                    assert list.get(i+1).compareTo(list.get(i)) < 0;
                } else
                    assert list.get(i).compareTo(list.get(i + 1)) == 0;
         }


    }

    @Test
    public void testHighCard() {
        testHandEvaluation(HandValues.HIGH_CARD);
    }

    @Test
    public void testPairs() {
        testHandEvaluation(HandValues.PAIR);
    }

    @Test
    public void testTwoPairs() {
        testHandEvaluation(HandValues.TWO_PAIRS);
    }

    @Test
    public void testThreeOfAKind() {
        testHandEvaluation(HandValues.THREE_OF_A_KIND);
    }

    @Test
    public void testStraight() {
        testHandEvaluation(HandValues.STRAIGHT);
    }

    @Test
    public void testFlush() {
        testHandEvaluation(HandValues.FLUSH);
    }

    @Test
    public void testFullHouse() {
        testHandEvaluation(HandValues.FULL_HOUSE);
    }

    @Test
    public void testQuads() {
        testHandEvaluation(HandValues.QUADS);
    }

    @Test
    public void testStraightFlush() {
        testHandEvaluation(HandValues.STRAIGHT_FLUSH);
    }


    @Test
    public void testFindWinner(){
        Player player1 = new Player(new ArrayList<>(), handValueMap.get(HandValues.HIGH_CARD).get(0), 0, 0, false, null);
        Player player2 = new Player(new ArrayList<>(), handValueMap.get(HandValues.FLUSH).get(0), 0, 0, false,  null);
        Player player3 = new Player(new ArrayList<>(), handValueMap.get(HandValues.FLUSH).get(0), 0, 0, false,  null);
        Player player4 = new Player(new ArrayList<>(), handValueMap.get(HandValues.PAIR).get(0), 0, 0, false,  null);
        Player player5 = new Player(new ArrayList<>(), handValueMap.get(HandValues.STRAIGHT).get(0), 0, 0, false,  null);

        List<Player> playerList1 = new ArrayList<>(Arrays.asList(player1, player2, player4, player5));

        List<Player> winners1 = HandEvaluation.getWinner(playerList1);

        assert winners1.size() == 1;
        assert winners1.contains(player2);

        List<Player> playerList2 = new ArrayList<>(Arrays.asList(player1, player4, player5));

        List<Player> winners2 = HandEvaluation.getWinner(playerList2);

        assert winners2.size() == 1;
        assert winners2.contains(player5);

        List<Player> playerList3 = new ArrayList<>(Arrays.asList(player1, player4));

        List<Player> winners3 = HandEvaluation.getWinner(playerList3);

        assert winners3.size() == 1;
        assert winners3.contains(player4);

        List<Player> playerList4 = new ArrayList<>(Arrays.asList(player1, player2, player3, player4, player5));

        List<Player> winners4 = HandEvaluation.getWinner(playerList4);

        assert winners4.size() == 2;
        assert winners4.contains(player2);
        assert winners4.contains(player3);
    }

}
