import java.util.ArrayList;
import java.util.Collections;

public class MyDeck implements Deck {
    ArrayList<Card> deck = new ArrayList<Card>();

    public MyDeck() {
        initializeDeck();
        System.out.println(deck.size());
    }

    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    @Override
    public void initializeDeck() {
        String[] Colors = {"Red", "Blue", "Green", "Yellow"};
        for (String color : Colors) {
            for (int j = 1; j < 10; j++) {
                deck.add(new NumberedCard(color, String.valueOf(j)));
                deck.add(new NumberedCard(color, String.valueOf(j)));
            }
            deck.add(new NumberedCard(color, String.valueOf(0)));
            deck.add(new ActionCard(color, "Reverse"));
            deck.add(new ActionCard(color, "Reverse"));
            deck.add(new ActionCard(color, "Skip"));
            deck.add(new ActionCard(color, "Skip"));
            deck.add(new ActionCard(color, "Draw Two"));
            deck.add(new ActionCard(color, "Draw Two"));
        }
        String[] Actions = {"Wild", "Wild Draw Four"};
        for (String action : Actions) {
            for (int j = 0; j < 4; j++) {
                deck.add(new WildCard(action));
            }
        }
    }

    @Override
    public Card drawCard() {
        return deck.remove(0);
    }

    @Override
    public void addCustomWildCard(WildCard wildCard) {
        if (deck.size() < 112)
            deck.add(wildCard);
    }
}
