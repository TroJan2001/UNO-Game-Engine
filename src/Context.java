import java.util.Scanner;

public class Context {
    final private DiscardPile discardPile;
    final private Deck deck;
    final private Scanner scanner;
    final private Game game;

    // Constructor and getters/setters
    public Context(DiscardPile discardPile, Deck deck,Scanner scanner, Game game) {
        this.discardPile = discardPile;
        this.deck = deck;
        this.scanner = scanner;
        this.game = game;
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
}
