package color_utils;

public class ColorGenerator {
    private ColorGenerator() {
    }

    public static String getColor(String color) {
        return switch (color) {
            case "Blue" -> Colors.BLUE;
            case "Red" -> Colors.RED;
            case "Yellow" -> Colors.YELLOW;
            case "Green" -> Colors.GREEN;
            default -> Colors.PURPLE;
        };
    }

}
