package core;

public class ClassicDeck extends Deck {

    private static ClassicDeck instance;

    private ClassicDeck() {
        initializeDeck();
    }

    public static ClassicDeck getInstance() {
        if (instance == null) {
            instance = new ClassicDeck();
        }
        return instance;
    }

}
