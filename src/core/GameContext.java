package core;

import java.util.Scanner;

public class GameContext {
    final private DiscardPile discardPile;
    final private Deck deck;
    final private Game game;
    final private Player player;

    // Private constructor to enforce the use of the Builder
    private GameContext(Builder builder) {
        this.discardPile = builder.discardPile;
        this.deck = builder.deck;
        this.game = builder.game;
        this.player = builder.player;
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public Deck getDeck() {
        return deck;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "Game = " +getGame() + " Current Player = "  + getPlayer() + " Deck = " + getDeck() + " Discard Pile = " + getDiscardPile();
    }

    // Static Builder class
    public static class Builder {
        private DiscardPile discardPile;
        private Deck deck;
        private Game game;
        private Player player;

        public Builder setDiscardPile(DiscardPile discardPile) {
            this.discardPile = discardPile;
            return this;
        }

        public Builder setDeck(Deck deck) {
            this.deck = deck;
            return this;
        }

        public Builder setGame(Game game) {
            this.game = game;
            return this;
        }

        public Builder setPlayer(Player player) {
            this.player = player;
            return this;
        }

        public GameContext build() {
            return new GameContext(this);
        }
    }
}
