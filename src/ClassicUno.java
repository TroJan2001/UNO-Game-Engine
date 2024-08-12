import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ClassicUno extends Game{
    protected int currentPlayerIndex = 0;
    protected boolean isReversed = false;
    protected boolean skipNextTurn = false;

    @Override
    protected void initializeGame() {
        players = new ArrayList<>();
        discardPile = new DiscardPile();
        deck = new MyDeck();
        deck.shuffle();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter Player 1 name?");
        players.add(new Player(scanner.nextLine()));
        System.out.println("Please Enter Player 2 name?");
        players.add(new Player(scanner.nextLine()));
        while(true){
            System.out.println("Do you want to add a new player? (Y/N)");
            if(scanner.nextLine().equalsIgnoreCase("y") && players.size()<10){
                System.out.println("Please Enter Player name?");
                players.add(new Player(scanner.nextLine()));
            }
            else
                break;
        }
    }

    @Override
    protected void dealCards() {
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.drawCard(deck.drawCard());
            }
        }
    }

    @Override
    protected void firstDraw() {
       discardPile.addCard(deck.drawCard());
        System.out.println("Current card is " + discardPile.getTopCard());
    }

    @Override
    protected void playTurn(Player player) {

        if (skipNextTurn) {
            skipNextTurn = false;
            // Move to the next player
            getNextPlayer();
            return;
        }
        // Show the player's hand
        System.out.println(player.getName() + "'s turn. Your cards: ");
        for (Card card : player.getHand()) {
            System.out.println(card);
        }

        Card topCard = discardPile.getTopCard();
        boolean cardPlayed = false;

        for (Card card : player.getHand()) {
            if (card.playable(topCard)) {
                player.playCard(card);
                discardPile.addCard(card);
                System.out.println(player.getName() + " plays " + card);
                cardPlayed = true;

                // Handle special cards
                if (card instanceof ActionCard actionCard) {
                    switch (actionCard.getValue()) {
                        case "Reverse":
                            isReversed = !isReversed;
                            System.out.println("Reversing turn");
                            break;
                        case "Draw Two":
                            Player nextPlayer = getNextPlayer();
                            nextPlayer.drawCard(deck.drawCard());
                            nextPlayer.drawCard(deck.drawCard());
                            System.out.println(nextPlayer.getName() + " draws two cards and loses their turn");
                            skipNextTurn = true;
                            break;
                        case "Skip":
                            System.out.println("Next player's turn is skipped!");
                            skipNextTurn = true;
                            break;
                        default:
                            break;
                    }
                }
                else if (card instanceof WildCard wildCard) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Choose a color (Red, Blue, Green, Yellow): ");
                    String chosenColor = scanner.nextLine();
                    discardPile.addCard(new ActionCard(chosenColor, "None"));
                    System.out.println("Changing color to " + chosenColor);

                    if (wildCard.getValue().equals("Wild Draw Four")) {
                        Player wildNextPlayer = getNextPlayer();
                        for (int i = 0; i < 4; i++) {
                            wildNextPlayer.drawCard(deck.drawCard());
                        }
                        System.out.println(wildNextPlayer.getName() + " draws four cards and loses their turn");
                        skipNextTurn = true;
                    }
                }
                break;
            }
        }

        if (!cardPlayed) {
            // If no card was played, draw a card
            Card drawnCard = deck.drawCard();
            player.drawCard(drawnCard);
            System.out.println(player.getName() + " draws a card");
        }
    }

    @Override
    protected boolean checkWinCondition(Player player) {
        return player.getHand().isEmpty();
    }

    protected Player getNextPlayer() {
        if (isReversed) {
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
        return players.get(currentPlayerIndex);
    }

}

