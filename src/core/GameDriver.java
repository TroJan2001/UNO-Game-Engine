package core;

import factory.GameFactory;

public class GameDriver {
    public static void main(String[] args) {
        Game game = GameFactory.createGame("Custom");
        game.play();

    }

    @Override
    public String toString(){
        return "Game Driver";
    }
}
