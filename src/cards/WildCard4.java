package cards;
import core.Context;
import core.*;
public class WildCard4 extends WildCard {

    public WildCard4() {
        super();
    }

    public String getValue() {
        return "Wild Draw Four";
    }

    @Override
    public void execute(Context context) {
        super.execute(context);
        Game game = context.getGame();
        Player wildNextPlayer = game.getNextPlayer();
        for (int i = 0; i < 4; i++) {
            wildNextPlayer.drawCard(context.getDeck().drawCard());
        }
        System.out.println(wildNextPlayer.getName() + " draws four cards");
        game.skipNextTurn();
    }
}
