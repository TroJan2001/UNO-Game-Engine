package core;

import cards.BasicWildCard;
import cards.Card;
import cards.SwapHandCard;

public class CustomDeck extends Deck{
    private static CustomDeck instance;

    private CustomDeck() {
        //Adding 3 new Swap Hand Cards
        addCustomWildCard(new SwapHandCard());
        addCustomWildCard(new SwapHandCard());
        addCustomWildCard(new SwapHandCard());
        initializeDeck();
    }

    public static CustomDeck getInstance() {
        if (instance == null) {
            instance = new CustomDeck();
        }
        return instance;
    }

    public Card drawFirstCard() {
        Card card = deck.get(deck.size() - 1);
        deck.remove(deck.size() - 1);
        return card;
    }

    private void addCustomWildCard(BasicWildCard basicWildCard) {
        if (deck.size()>111){
            System.out.println("Maximum Deck Size Reached (112)");
            return;
        }
        deck.add(basicWildCard);
    }



}
