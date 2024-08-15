package core;

import Factory.GameFactory;

public class GameDriver {
    public static void main(String[] args) {
        //Game game = GameFactory.createGame("Classic");
        Game game = GameFactory.createGame("Custom");
        game.play();

    }
}
