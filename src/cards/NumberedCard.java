package cards;

import color_utils.Colors;

import java.util.Objects;

public class NumberedCard extends ColoredCard {
    private final String number;

    public NumberedCard(String color, String number) {
        super(color);
        this.number = number;
    }

    @Override
    public String getValue() {
        return this.number;
    }

    public boolean playable(Card card) {
        if (card instanceof ColoredCard) {
            return this.color.equals(((ColoredCard) card).getColor()) || this.number.equals(card.getValue());
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof NumberedCard other))
            return false;

        return this.color.equals(other.color) && this.number.equals(other.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, number);
    }

    @Override
    public String toString() {
        return super.toString() + "NumberedCard{" + "color='" + color + '\'' + ", number='" + number + '\'' + '}' + Colors.RESET;
    }
}
