import java.util.*;

public class DiscardPile {

    private final List<Card> pile;

    public DiscardPile() {
        pile = new ArrayList<>();
    }

    public void addCard(Card card) {
        pile.add(0, card);
    }

    public Card getTopCard() {
        return pile.get(0);
    }
}
