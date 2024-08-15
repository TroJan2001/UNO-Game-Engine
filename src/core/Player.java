package core;

import cards.Card;

import java.util.*;

public class Player {
    private final String name;
    private List<Card> hand;


    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> newHand) {
        this.hand = new ArrayList<>(newHand); // Set the player's hand to the new set of cards
    }

    public void drawCard(Card card) {
        hand.add(card);
    }

    public void playCard(Card card) {
        hand.remove(card);
    }

    @Override
    public String toString() {
        return getName();
    }

}
