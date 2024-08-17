package cards;

import color_utils.Colors;

import java.util.Objects;

public abstract class ActionCard extends ColoredCard implements Executable {
    private final String action;

    public ActionCard(String color, String action) {
        super(color);
        this.action = action;
    }

    @Override
    public String getValue() {
        return this.action;
    }

    @Override
    public boolean playable(Card card) {
        if (card instanceof ColoredCard) {
            return this.color.equals(((ColoredCard) card).getColor()) || this.action.equals(card.getValue());
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof ActionCard other))
            return false;

        return this.color.equals(other.color) && this.action.equals(other.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, action);
    }

    @Override
    public String toString() {
        return super.toString() + "ActionCard{" + "color='" + color + '\'' + ", action='" + action + '\'' + '}' + Colors.RESET;
    }

}
