package cards;

public class NumberedCard implements ColoredCard {
    private final String number;
    private final String color;

    public NumberedCard(String color, String number) {
        this.number = number;
        this.color = color;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getValue() {
        return this.number;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof NumberedCard other))
            return false;

        return this.color.equals(other.color) && this.number.equals(other.number);
    }

    public boolean playable(Card card) {
        if (card instanceof ColoredCard) {
            return this.color.equals(((ColoredCard) card).getColor()) || this.number.equals(card.getValue());
        }
        return true;
    }

    @Override
    public String toString() {
        return "NumberedCard{" + "color='" + color + '\'' + ", number='" + number + '\'' + '}';
    }
}
