package cards;

import core.GameContext;

public class ReverseCard extends ActionCard {
    public ReverseCard(String color) {
        super(color, "Reverse");
    }

    @Override
    public void execute(GameContext gameContext) {
        gameContext.getGame().reverseDirection();
    }
}
