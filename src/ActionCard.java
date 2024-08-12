public abstract class ActionCard implements ColoredCard,Executable {
    private final String color;
    private final String action;

    public ActionCard(String color, String action) {
        this.color = color;
        this.action = action;
    }

    @Override
    public String getColor() {
        return this.color;
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
    public String toString() {
        return "ActionCard{" + "color='" + color + '\'' + ", action='" + action + '\'' + '}';
    }

}
