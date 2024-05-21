package net.berndreiss;

import org.openjdk.jmh.annotations.Benchmark;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameState {

    private static int runs = 0;

    private final boolean print;

    private final List<Player> players = new ArrayList<>();
    private static final List<Double> raiseSizes = new ArrayList<>();
    private final Street street;
    private final List<Card> communityCards = new ArrayList<>();
    private final List<Card> deck = new ArrayList<>();

    private final long pot;
    private final long currentBet;

    private final int activePlayer;


    public GameState(List<Player> players, List<Double> raiseSizes){
        this(players, raiseSizes, false);
    }

    public GameState(List<Player> players, List<Double> raiseSizes, boolean print){
        this.players.addAll(players);
        GameState.raiseSizes.addAll(raiseSizes);
        this.deck.addAll(Card.getDeck());
        this.print = print;
        this.street = Street.PRE_FLOP;
        pot = 0;
        currentBet = 0;
        activePlayer = players.size() > 2 ? 2 : 0;
        for (Player p: players)
            for (Card c: p.getHand().getCards())
                deck.remove(c);
    }

    public GameState(List<Player> players, Street street, List<Card> communityCards, List<Card> deck, long pot, long currentBet, int activePlayer, boolean print){
        this.players.addAll(players);
        this.street = street;
        this.communityCards.addAll(communityCards);
        this.deck.addAll(deck);
        this.pot = pot;
        this.currentBet = currentBet;
        this.activePlayer = activePlayer;
        this.print = print;
    }

    public Map<Long, GameState> advance(){
        if (players.isEmpty()) {
            Street nextStreet = this.street.getNext();

            if (nextStreet == Street.SHOWDOWN)
                return new HashMap<>();

        }
            return null;
    }

    public void printDeck(){
        System.out.println(deck);
    }

    public static List<GameState> getInitialGameStates(List<Double> stackSizes, List<Double> raiseSizes, List<PlayerHistory> histories){

        List<GameState> gameStates = new ArrayList<>();

        List<Card> deck = Card.getDeck();

        Player[] playersTemp = new Player[stackSizes.size()];

        List<Player[]> playerList = getStartingCombinations(deck, new ArrayList<>(), 0, playersTemp, stackSizes, histories);
  //      System.out.println(playerList.size());

        return null;
    }

    public static List<GameState> getInitialGameStatesParallel(List<Double> stackSizes, List<Double> raiseSizes, List<PlayerHistory> histories){

        List<GameState> gameStates = new ArrayList<>();

        List<Card> deck = Card.getDeck();

        Player[] playersTemp = new Player[stackSizes.size()];

        List<Player[]> playerList = getStartingCombinationsParallel(deck, 0, playersTemp, stackSizes, histories);

//        System.out.println(playerList.size());
        return null;
    }

    private static List<Player[]> getStartingCombinations(List<Card> deck, List<Integer> usedCards, int player, Player[] players, List<Double> stackSizes, List<PlayerHistory> histories){

        if (player == players.length) {
            List<Player[]> playerList = new ArrayList<>();
            playerList.add(players.clone());
            usedCards.clear();
            return playerList;
        }

        List<Player[]> playerList = new ArrayList<>();

        for (int i = 0; i< deck.size(); i++){
            if (usedCards.contains(i))
                continue;
            for (int j = i+1; j < deck.size(); j++){
                if (usedCards.contains(j))
                    continue;
                usedCards.add(i);
                usedCards.add(j);
                Hand hand = new Hand(deck.get(i), deck.get(j));
                players[player] = new Player(hand.getCards(), hand, stackSizes.get(player), 0, false, histories.get(player));
                playerList.addAll(getStartingCombinations(deck, usedCards, player+1, players, stackSizes, histories));
            }
        }
        return playerList;
    }

    public static List<Player[]> getStartingCombinationsParallel(List<Card> deck, int player, Player[] players, List<Double> stackSizes, List<PlayerHistory> histories) {
        if (player == players.length) {
            List<Player[]> playerList = new ArrayList<>();
            playerList.add(players.clone()); // Ensure players array is copied
            return playerList;
        }

        return IntStream.range(0, deck.size())
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, deck.size())
                        .mapToObj(j -> new int[]{i, j}))
                .parallel() // Parallelize the stream
                .flatMap(indices -> {
                    int i = indices[0];
                    int j = indices[1];

                    List<Card> newDeck = new ArrayList<>(deck);
                    newDeck.remove(deck.get(i));
                    newDeck.remove(deck.get(j));

                    Hand hand = new Hand(deck.get(i), deck.get(j));
                    Player[] playersNew = players.clone();
                    playersNew[player] = new Player(hand.getCards(), hand, stackSizes.get(player), 0, false, histories.get(player));

                    return getStartingCombinationsParallel(newDeck, player + 1, playersNew, stackSizes, histories).stream();
                })
                .collect(Collectors.toList());
    }
}
