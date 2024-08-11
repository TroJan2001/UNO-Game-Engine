import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ClassicUno extends Game{

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
    }

    @Override
    protected void playTurn(Player player) {
        Card topCard = discardPile.getTopCard();
        for (Card card : player.getHand()) {
                if (card.playable(topCard)) {
                    player.playCard(card);
                    discardPile.addCard(card);
                    System.out.println(player.getName() + " plays " + card);
                    return;
            }

        }
        Card drawnCard = deck.drawCard();
        player.drawCard(drawnCard);
        System.out.println(player.getName() + " draws a card");
    }

    @Override
    protected boolean checkWinCondition(Player player) {
        return player.getHand().isEmpty();
    }
}

