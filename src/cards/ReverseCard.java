package cards;

import core.Context;

public class ReverseCard extends ActionCard {
    public ReverseCard(String color) {
        super(color, "Reverse");
    }

    @Override
    public void execute(Context context) {
        context.getGame().reverseDirection();
    }
}
