package core;

public class ClassicUNO extends Game {

    public ClassicUNO(){
        currentPlayerIndex=0;
        isReversed=false;
        skipNextTurn=false;
    }

    @Override
    protected void initializeGame(){
        deck = ClassicDeck.getInstance();
        super.initializeGame();
        dealCards(7); // Each Player is dealt 7 cards
    }

    @Override
    protected void firstDraw() {
        discardPile.addCard(deck.drawCard());
        System.out.println("Top card is " + discardPile.getTopCard());
    }

    @Override
    protected boolean checkWinCondition(Player player) {
        return player.getHand().isEmpty();
    }

    @Override
    protected void win(Player player) {
        System.out.println(player.getName() + " wins!");
    }

    @Override
    public void reverseDirection() {
        isReversed = !isReversed; // Toggle the direction
        System.out.println("Direction reversed");
    }

    @Override
    public void skipNextTurn() {
       skipNextTurn=true;
       System.out.println("Skipping "  + getNextPlayer().getName() + "'s Turn");
    }

}

