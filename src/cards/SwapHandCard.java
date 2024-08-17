package cards;

import core.GameContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SwapHandCard extends BasicWildCard {

    // A card that swaps the hand with next player
    @Override
    public String getValue() {
        return "Swap Hand Card";
    }

    @Override
    public void execute(GameContext gameContext) {
        List<Card> tempHand = new ArrayList<>(gameContext.getPlayer().getHand());
        gameContext.getPlayer().setHand(gameContext.getGame().getNextPlayer().getHand());
        gameContext.getGame().getNextPlayer().setHand(tempHand);
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

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
