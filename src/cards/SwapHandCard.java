package cards;

import core.Context;

import java.util.ArrayList;
import java.util.List;

public class SwapHandCard extends BasicWildCard {

    // A card that swaps the hand with next player
    @Override
    public String getValue() {
        return "Swap Hand Card";
    }

    @Override
    public void execute(Context context) {
        List<Card> tempHand = new ArrayList<>(context.getPlayer().getHand());
        context.getPlayer().setHand(context.getGame().getNextPlayer().getHand());
        context.getGame().getNextPlayer().setHand(tempHand);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof SwapHandCard other)) {
            return false;
        }
        return this.getValue().equals(other.getValue());
    }
}
