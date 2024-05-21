package net.berndreiss;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final List<Card> playerCards = new ArrayList<>();
    private final Hand hand;
    private final double stack;
    private final PlayerHistory playerHistory;

    private final double currentBet;
    private final boolean raised;

    public Player(List<Card> playerCards, Hand hand, double stack, double currentBet, boolean raised, PlayerHistory playerHistory){
        this.playerCards.addAll(playerCards);
        this.hand = hand;
        this.stack = stack;
        this.currentBet = currentBet;
        this.raised = raised;
        this.playerHistory = playerHistory;
    }

    public Hand getHand(){
        return hand;
    }

    public double getStack(){
        return stack;
    }

    public PlayerHistory getPlayerHistory(){
        return playerHistory;
    }

    public double getCurrentBet(){
        return currentBet;
    }

    public Player bet(long amount, long raiseThreshold){
        if (stack < amount){
            return new Player(playerCards, hand, 0, currentBet + stack, stack >= raiseThreshold, playerHistory);
        }
        return new Player(playerCards, hand, stack - amount, currentBet + amount, amount > raiseThreshold, playerHistory);

    }

    public Player incorporateCommunityCards(List<Card> communityCards){

        List<Card> allCards = new ArrayList<>();
        Hand bestCombination = null;

        allCards.addAll(communityCards);
        allCards.addAll(playerCards);

        if (allCards.size() <= 5)
            return new Player(playerCards, new Hand(allCards), stack, currentBet, false, playerHistory);

        List<Hand> combinations = new ArrayList<>();

        for (int i = 0; i < allCards.size(); i++){
            for (int j = i+1; j < allCards.size(); j++){
                for (int k = j+1; k < allCards.size(); k++){
                    for (int l = k+1; l < allCards.size(); l++ ){
                        for (int m = l+1; m < allCards.size(); m++){
                            combinations.add(new Hand(allCards.get(i), allCards.get(j), allCards.get(k), allCards.get(l), allCards.get(m)));
                        }
                    }
                }
            }
        }

        for (Hand h: combinations)
            if (bestCombination == null || h.compareTo(bestCombination) > 0)
                bestCombination = h;

        return new Player(playerCards, bestCombination, stack, currentBet, false, playerHistory);
    }

    public boolean isBroke(){
        return stack > 0;
    }

    public boolean raised(){
        return raised;
    }

    @Override
    public String toString(){
        return "Cards: " + playerCards.get(0) + ", " + playerCards.get(1) + "\n" + "Stack: " + stack;
    }

}
