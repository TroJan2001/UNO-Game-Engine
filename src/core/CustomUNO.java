package core;

import cards.Card;
import cards.SwapHandCard;
import cards.WildCard;

public class CustomUNO extends Game {

    public CustomUNO(){
        currentPlayerIndex=0;
        isReversed=false;
        skipNextTurn=false;
    }


    @Override
    protected void initializeGame(){
        deck = CustomDeck.getInstance();
        super.initializeGame();
        dealCards(9);
    }


    @Override
    protected void firstDraw() {
        discardPile.addCard(((CustomDeck)deck).drawFirstCard()); // add last card as the first card in discard pile
        System.out.println("Top card is " + discardPile.getTopCard());
    }


    @Override
    protected void drawCard(Player player) {
        Card drawnCard = deck.drawCard();
        player.drawCard(drawnCard);
        System.out.println(player.getName() + " draws a card");

        if (drawnCard instanceof SwapHandCard) { // play extra turn and skip next player if you get Swap Hand Card
            System.out.println(player.getName() + " drew a Swap Hand Card card, taking another turn and skipping " + playerContexts.get(player).getGame().getNextPlayer() + "'s turn") ;
            playTurn(player); // Give the player another turn
        }
    }

    @Override
    protected boolean checkWinCondition(Player player) {
        if (player.getHand().isEmpty())
            return true;
        if (player.getHand().contains(new SwapHandCard()) && player.getHand().contains(new WildCard())) {
            System.out.println("Winning by (Swap Hand - Wildcard) Advantage");
            return true;
        }
        return false;
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

