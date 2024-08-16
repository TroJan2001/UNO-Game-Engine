package cards;

import core.Context;

public class SkipCard extends ActionCard {
    public SkipCard(String color) {
        super(color, "Skip");
    }

    @Override
    public void execute(Context context) {
        context.getGame().skipNextTurn();
    }
}
