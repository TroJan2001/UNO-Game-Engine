package cards;

import core.GameContext;

public class FillerCard extends ColoredCard {
    public FillerCard(String color) {
        super(color);
    }

    @Override
    public String getValue() {
        return "Filler Card";
    }

    @Override
    public boolean playable(Card card) {
        return false;
    }

}
