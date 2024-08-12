public class Context {
    private Player currentPlayer;
    private DiscardPile discardPile;
    private Deck deck;

    // Constructor and getters/setters
    public Context(Player currentPlayer, DiscardPile discardPile, Deck deck) {
        this.currentPlayer = currentPlayer;
        this.discardPile = discardPile;
        this.deck = deck;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }


    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public Deck getDeck() {
        return deck;
    }

}
