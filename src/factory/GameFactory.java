package factory;

import core.ClassicUNO;
import core.CustomUNO;
import core.Game;

public class GameFactory {

    public static Game createGame(String gameTYPE) {
        return switch (gameTYPE) {
            case "Classic" -> new ClassicUNO();
            case "Custom" -> new CustomUNO();
            default -> throw new IllegalArgumentException("Invalid game type");
        };

    }
}
