package core;

import cards.Card;
import factory.CardFactory;

import java.util.Random;

public class CustomDeck extends Deck {
    private static CustomDeck instance;

    private CustomDeck() {
        //Adding 3 new Swap Hand Cards
        addCustomWildCard(CardFactory.getWildCardInstance("Swap Hand"));
        addCustomWildCard(CardFactory.getWildCardInstance("Swap Hand"));
        addCustomWildCard(CardFactory.getWildCardInstance("Swap Hand"));
        initializeDeck();
    }

    public static CustomDeck getInstance() {
        if (instance == null) {
            instance = new CustomDeck();
        }
        return instance;
    }

    @Override
    protected Card drawFirstCard() {
        Random random = new Random();
        int randomIndex = random.nextInt(deck.size());
        return removeCard(randomIndex);
    }

}
