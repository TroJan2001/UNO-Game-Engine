public interface Deck {
    public void shuffle();

    public void initializeDeck();

    public Card drawCard();

    public void addCustomWildCard(WildCard wildCard);
}
