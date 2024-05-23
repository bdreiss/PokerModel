package net.berndreiss;

import java.util.Arrays;
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

            for (int j=0; j<range[i].length; j++){
                if (range[i][j] == 0)
                    continue;
                List<Card> cards1 = Arrays.stream(StartingHands.startingHands[i][j]).toList();
                result += winningFunction.applyAsDouble(cards, cards1)*range[i][j];
                hands++;
            }

        }
    }



}
