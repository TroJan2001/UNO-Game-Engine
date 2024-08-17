package core;

import color_utils.Colors;

public class ClassicUNO extends Game {

    public ClassicUNO() {
        currentPlayerIndex = 0;
        isReversed = false;
        skipNextTurn = false;
    }

    @Override
    protected void initializeGame() {
        deck = ClassicDeck.getInstance();
        super.initializeGame();
        dealCards(7); // Each Player is dealt 7 cards
    }

    @Override
    protected boolean checkWinCondition(Player player) {
        return player.getHand().isEmpty();
    }

    @Override
    public String toString(){
        return "This is a Classic Game Engine";
    }


}

