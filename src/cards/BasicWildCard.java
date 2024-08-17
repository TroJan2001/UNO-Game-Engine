package cards;

import core.GameContext;

import java.util.Objects;

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
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return "WildCard{action='" + getValue() + '\'' + '}';
    }

}
