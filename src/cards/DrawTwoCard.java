package cards;

import color_utils.Colors;
import core.GameContext;
import core.Deck;
import core.Game;
import core.Player;

public class DrawTwoCard extends ActionCard {


    public DrawTwoCard(String color) {
        super(color, "Draw Two");
    }

    @Override
    public void execute(GameContext gameContext) {
        Game game = gameContext.getGame();
        Player nextPlayer = game.getNextPlayer();
        Deck deck = gameContext.getDeck();
        nextPlayer.drawCard(deck.drawCard());
        nextPlayer.drawCard(deck.drawCard());
        System.out.println(Colors.PURPLE + nextPlayer.getName() + " draws two cards");
        game.skipNextTurn();
    }
}
