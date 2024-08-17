package factory;

import cards.*;

public class CardFactory {
    private CardFactory() {
    }

    public static BasicWildCard getWildCardInstance(String wildCardType) {
        return switch (wildCardType) {
            case "Wild" -> new WildCard();
            case "Wild 4" -> new WildCard4();
            case "Swap Hand" -> new SwapHandCard();
            default -> throw new IllegalArgumentException("Invalid Wild Card Type");
        };

    }

    public static ActionCard getActionCardInstance(String actionCardType, String color) {
        return switch (actionCardType) {
            case "Draw Two" -> new DrawTwoCard(color);
            case "Reverse" -> new ReverseCard(color);
            case "Skip" -> new SkipCard(color);
            default -> throw new IllegalArgumentException("Invalid Color Card Type");
        };
    }

    public static NumberedCard getNumberedCardInstance(String color, String number) {
        return new NumberedCard(color, number);
    }
}
