package core;

import cards.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Deck {
     public ArrayList<Card> deck = new ArrayList<>();

     void initializeDeck(){
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
         shuffle();
     }

    public Card drawCard(){
        if (deck.isEmpty()) {
            refillDeck();
        }
        return deck.remove(0);
    }

    protected void shuffle(){
        Collections.shuffle(deck);
    }

    protected void refillDeck(){
        System.out.println("Refilling Core.Deck");
        DiscardPile discardPile = DiscardPile.getInstance();

        Card topCard = discardPile.getTopCard();

        List<Card> tempDeck = new ArrayList<>();

        for (Card card : discardPile.getPile()) {
            if (!card.equals(topCard) && !card.getValue().equals("cards.FillerCard")) {
                tempDeck.add(card);
            }
        }
        deck.addAll(tempDeck);

        discardPile.clear();
        discardPile.addCard(topCard);

        shuffle();
    }

}
