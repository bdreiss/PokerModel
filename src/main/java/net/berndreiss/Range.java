package net.berndreiss;

import java.util.List;
import java.util.function.ToDoubleBiFunction;

public class Range {
    public double[][] range = new double[169][];
    private ToDoubleBiFunction<List<Card>, List<Card>> winningFunction;

    Range(ToDoubleBiFunction<List<Card>, List<Card>> winningFunction){
        this.winningFunction = winningFunction;
        for (int i=0;i<13;i++){
            range[i] = new double[6];
        }
        for (int i=13;i<91;i++){
            range[i] = new double[4];
        }
        for (int i=91;i<169;i++){
            range[i] = new double[12];
        }
    }


    public void handAgainstRange(List<Card> cards){

        double result = 0;
        int hands = 0;
        for (int i = 0; i < 169; i++){
            List<CardValue> values = StartingHands.values()[i].getCardValues();
            StartingHandsType type = StartingHands.values()[i].getStartingHandsType();
            List<Card> cardsRange = HandEvaluation.getCards(values, type);
            List<List<Card>> combos = HandEvaluation.getCombinations(cardsRange, 2);

            if (type == StartingHandsType.SUITED)
                combos = combos.stream().filter(l -> l.get(0).suite()==l.get(1).suite()).toList();

            if (type == StartingHandsType.UNSUITED)
                combos = combos.stream().filter(l->l.get(0).suite()!=l.get(1).suite()).filter(l -> l.get(0).value() != l.get(1).value()).toList();


            for (int j=0; j<combos.size(); j++){
                if (range[i][j] == 0)
                    continue;

                List<Card> cards1 = combos.get(j);
                result += winningFunction.applyAsDouble(cards, cards1)*range[i][j];
                System.out.println(range[i][j]);
                System.out.println(result);
                hands++;
            }

        }
        System.out.println(hands);
        System.out.println(result/hands);
    }



}
