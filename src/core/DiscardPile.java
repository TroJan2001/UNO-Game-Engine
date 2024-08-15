package core;

import cards.Card;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile {

    private final List<Card> pile;
    private static DiscardPile instance;

    private DiscardPile() {
        pile = new ArrayList<>();
    }

    public static DiscardPile getInstance() {
        if (instance == null)
            instance = new DiscardPile();
        return instance;
    }

    public void clear() {
        pile.clear();
    }

    public void addCard(Card card) {
        pile.add(0, card);
    }

    public Card getTopCard() {
        return pile.get(0);
    }

    public List<Card> getPile() {
        return pile;
    }

}
