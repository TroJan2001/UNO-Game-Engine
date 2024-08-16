package cards;

import color_utils.ColorGenerator;

public abstract class ColoredCard implements Card {
    protected String color;

    protected ColoredCard(String color) {
        this.color = color;
    }

    abstract String getColor();

    public String toString() {
        return ColorGenerator.getColor(this.color);
    }
}
