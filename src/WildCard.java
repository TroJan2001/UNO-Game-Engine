public class WildCard implements Card {
    private final String action;

    public WildCard(String action) {
        this.action = action;
    }

    @Override
    public String getValue() {
        return this.action;
    }

    @Override
    public boolean playable(Card card) {
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof WildCard other))
            return false;

        return this.action.equals(other.action);
    }

    @Override
    public String toString() {

        return "WildCard{action='" + action + '\'' + '}';
    }

}
