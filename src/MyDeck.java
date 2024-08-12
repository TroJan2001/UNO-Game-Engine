import java.util.ArrayList;
import java.util.Collections;

public class MyDeck implements Deck {
    ArrayList<Card> deck = new ArrayList<>();

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
            deck.add(new ReverseCard(color));
            deck.add(new ReverseCard(color));
            deck.add(new SkipCard(color));
            deck.add(new SkipCard(color));
            deck.add(new DrawTwoCard(color));
            deck.add(new DrawTwoCard(color));
        }
        for (int j = 0; j < 4; j++) {
            deck.add(new WildCard());
            deck.add(new WildCard4());
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
