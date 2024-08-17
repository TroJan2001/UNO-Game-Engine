package cards;

import color_utils.ColorGenerator;
import color_utils.Colors;
import core.GameContext;
import core.DiscardPile;
import core.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WildCard extends BasicWildCard {

    @Override
    public String getValue() {
        return "Wild";
    }

    @Override
    public void execute(GameContext gameContext) {
        DiscardPile discardPile = gameContext.getDiscardPile();
        Player currentPlayer = gameContext.getPlayer();

        // Count occurrences of each color in the player's hand
        Map<String, Integer> colorCount = new HashMap<>();
        for (Card card : currentPlayer.getHand()) {
            if (card instanceof ColoredCard coloredCard) {
                String color = coloredCard.getColor();
                colorCount.put(color, colorCount.getOrDefault(color, 0) + 1);
            }
        }

        if (colorCount.isEmpty()) {
            colorCount.putAll(Map.of("Red", 1, "Blue", 1, "Green", 1, "Yellow", 1));
        }

        // Find the color with the most occurrences
        String chosenColor = Collections.max(colorCount.entrySet(), Map.Entry.comparingByValue()).getKey();

        // Add the card with the chosen color to the discard pile
        discardPile.addCard(new FillerCard(chosenColor, "FillerCard"));
        System.out.println(ColorGenerator.getColor(chosenColor) + "Changing color to " + chosenColor + Colors.RESET);
    }
}
