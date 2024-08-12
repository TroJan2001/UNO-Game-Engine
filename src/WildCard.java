import java.util.*;
public class WildCard implements Card,Executable {

    @Override
    public String getValue() {
        return "Wild";
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

        return this.getValue().equals(other.getValue());
    }

    @Override
    public String toString() {

        return "WildCard{action='" + getValue() + '\'' + '}';
    }

    @Override
    public void execute(Context context) {
        Scanner scanner = context.getScanner();
        DiscardPile discardPile = context.getDiscardPile();

        String chosenColor;
        while (true) {
            System.out.println("Choose a color (Red, Blue, Green, Yellow): ");
            chosenColor = scanner.nextLine().trim();

            if (chosenColor.equalsIgnoreCase("Red") ||
                    chosenColor.equalsIgnoreCase("Blue") ||
                    chosenColor.equalsIgnoreCase("Green") ||
                    chosenColor.equalsIgnoreCase("Yellow")) {
                break;
            } else {
                System.out.println("Invalid color. Please enter Red, Blue, Green, or Yellow.");
            }
        }

        discardPile.addCard(new Filler(chosenColor));
        System.out.println("Changing color to " + chosenColor);
    }

}
