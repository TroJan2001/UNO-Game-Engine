package cards;

import core.GameContext;

public class SkipCard extends ActionCard {
    public SkipCard(String color) {
        super(color, "Skip");
    }

    @Override
    public void execute(GameContext gameContext) {
        gameContext.getGame().skipNextTurn();
    }
}
