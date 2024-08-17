package core;

import java.util.Scanner;

public class GameContext {
    final private DiscardPile discardPile;
    final private Deck deck;
    final private Scanner scanner;
    final private Game game;
    final private Player player;

    // Constructor and getters/setters
    public GameContext(Player player, DiscardPile discardPile, Deck deck, Scanner scanner, Game game) {
        this.discardPile = discardPile;
        this.deck = deck;
        this.scanner = scanner;
        this.game = game;
        this.player = player;
    }


    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public Deck getDeck() {
        return deck;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString(){
        return "Game Context";
    }
}
