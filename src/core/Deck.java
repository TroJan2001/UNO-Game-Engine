package core;

import cards.*;
import color_utils.Colors;
import factory.CardFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Deck {
    public List<Card> deck = new ArrayList<>();

    protected Card drawFirstCard(){
        return drawCard();
    }

    void initializeDeck() {
        String[] Colors = {"Red", "Blue", "Green", "Yellow"};
        for (String color : Colors) {
            for (int j = 1; j < 10; j++) {
                deck.add(CardFactory.getNumberedCardInstance(color,String.valueOf(j)));
                deck.add(CardFactory.getNumberedCardInstance(color,String.valueOf(j)));
            }
            deck.add(CardFactory.getNumberedCardInstance(color,String.valueOf(0)));
            deck.add(CardFactory.getActionCardInstance("Reverse",color));
            deck.add(CardFactory.getActionCardInstance("Reverse",color));
            deck.add(CardFactory.getActionCardInstance("Skip",color));
            deck.add(CardFactory.getActionCardInstance("Skip",color));
            deck.add(CardFactory.getActionCardInstance("Draw Two", color));
            deck.add(CardFactory.getActionCardInstance("Draw Two", color));
        }
        for (int j = 0; j < 4; j++) {
            deck.add(CardFactory.getWildCardInstance("Wild"));
            deck.add(CardFactory.getWildCardInstance("Wild 4"));
        }
        shuffle();
    }

    public Card drawCard() {
        return removeCard(0);
    }

    public Card removeCard(int index) {
        if (deck.isEmpty())
            refillDeck();
        if (index >= 0 && index < deck.size()) {
            return deck.remove(index);
        }
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + deck.size());
    }

    protected void shuffle() {
        Collections.shuffle(deck);
    }

    protected void refillDeck() {
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

    protected void addCustomWildCard(BasicWildCard basicWildCard) {
        if (deck.size() > 111) {
            System.out.println(Colors.PURPLE + "Maximum Deck Size Reached (112)" + Colors.RESET);
            return;
        }
        deck.add(basicWildCard);
    }

}
