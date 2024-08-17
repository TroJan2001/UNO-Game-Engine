package cards;

import color_utils.Colors;
import core.GameContext;
import core.Game;
import core.Player;

public class WildCard4 extends WildCard {

    public WildCard4() {
        super();
    }

    public String getValue() {
        return "Wild Draw Four";
    }

    @Override
    public void execute(GameContext gameContext) {
        super.execute(gameContext);
        Game game = gameContext.getGame();
        Player wildNextPlayer = game.getNextPlayer();
        for (int i = 0; i < 4; i++) {
            wildNextPlayer.drawCard(gameContext.getDeck().drawCard());
        }
        System.out.println(Colors.PURPLE + wildNextPlayer.getName() + " draws four cards" + Colors.RESET);
        game.skipNextTurn();
    }
}
