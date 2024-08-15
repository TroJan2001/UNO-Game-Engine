package cards;

import core.Context;
import core.Deck;
import core.Game;
import core.Player;

public class DrawTwoCard extends ActionCard {


    public DrawTwoCard(String color) {
        super(color, "Draw Two");
    }

    @Override
    public void execute(Context context) {
        Game game = context.getGame();
        Player nextPlayer = game.getNextPlayer();
        Deck deck = context.getDeck();
        nextPlayer.drawCard(deck.drawCard());
        nextPlayer.drawCard(deck.drawCard());
        System.out.println(nextPlayer.getName() + " draws two cards");
        game.skipNextTurn();
    }
}
