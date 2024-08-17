package cards;

import core.GameContext;

public abstract class BasicWildCard implements Card, Executable {

    @Override
    abstract public String getValue();

    @Override
    abstract public void execute(GameContext gameContext);

    @Override
    public boolean playable(Card card) {
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof WildCard other))
            return false;

        return this.getValue().equals(other.getValue());
    }

    @Override
    public String toString() {
        return "WildCard{action='" + getValue() + '\'' + '}';
    }

}
