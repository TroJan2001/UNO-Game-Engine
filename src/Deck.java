public interface Deck {
    void shuffle();

    void initializeDeck();

    Card drawCard();

    void addCustomWildCard(WildCard wildCard);
}
