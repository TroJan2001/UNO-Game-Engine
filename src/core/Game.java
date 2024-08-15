package core;

import cards.Card;
import cards.Executable;

import java.util.*;

public abstract class Game {

    protected List<Player> players;
    protected Deck deck;
    protected DiscardPile discardPile;
    protected int currentPlayerIndex;
    protected boolean isReversed;
    protected boolean skipNextTurn;
    protected Map<Player, Context> playerContexts;

    protected abstract void firstDraw();

    protected abstract boolean checkWinCondition(Player player);

    protected abstract void win(Player player);


    public abstract void skipNextTurn();

    public abstract void reverseDirection();

    protected void initializeGame() {
        players = new ArrayList<>();
        discardPile = DiscardPile.getInstance();
        playerContexts = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        // Add the first two players
        for (int i = 1; i <= 2; i++) {
            while (addPlayer(scanner, "Please Enter Player " + i + " name:")) {
                System.out.println("Player's name is already in use, please choose a different name.");
            }
        }

        // Add additional players
        while (players.size() < 10) {
            System.out.println("Do you want to add a new player? (Y/N)");
            String input = scanner.nextLine();
            if ("n".equalsIgnoreCase(input)) {
                break;
            } else if ("y".equalsIgnoreCase(input)) {
                while (addPlayer(scanner, "Please Enter Player name:")) {
                    System.out.println("Player's name is already in use, please choose a different name.");
                }
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    protected boolean addPlayer(Scanner scanner, String prompt) {
        System.out.println(prompt);
        String playerName = scanner.nextLine();

        if (isNameInUse(playerName)) {
            return true;
        }

        Player player = new Player(playerName);
        players.add(player);
        playerContexts.put(player, new Context(player, discardPile, deck, scanner, this));
        return false;
    }

    protected boolean isNameInUse(String playerName) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(playerName)) {
                return true;
            }
        }
        return false;
    }

    protected void playTurn(Player player) {
        if (skipNextTurn) {
            skipNextTurn = false;
            currentPlayerIndex = getNextPlayerIndex();
            return;
        }

        showHand(player);
        boolean cardPlayed = attemptPlayCard(player);


        if (!cardPlayed) {
            drawCard(player);
        }
        currentPlayerIndex = getNextPlayerIndex();
    }

    protected void dealCards(int numOfCards) {
        if (numOfCards < 2 || numOfCards > 9) {
            System.out.println("number of cards not in range ... Defaulting to 7");
            numOfCards = 7;
        }
        for (Player player : players) {
            for (int i = 0; i < numOfCards; i++) {
                player.drawCard(deck.drawCard());
            }
        }
    }

    public Player getNextPlayer() {
        return players.get(getNextPlayerIndex());
    }

    protected boolean attemptPlayCard(Player player) {
        Card topCard = discardPile.getTopCard();
        boolean cardPlayed = false;

        for (Card card : player.getHand()) {
            if (card.playable(topCard)) {
                player.playCard(card);
                discardPile.addCard(card);
                System.out.println(player.getName() + " plays " + card);
                cardPlayed = true;

                // Handle special cards
                if (card instanceof Executable executable) {
                    Context context = playerContexts.get(player);
                    executable.execute(context);
                }
                break;
            }
        }

        return cardPlayed;
    }

    protected void showHand(Player player) {
        System.out.println(player.getName() + "'s turn. " + player.getName() + "'s cards: ");
        for (Card card : player.getHand()) {
            System.out.println(card);
        }
    }

    protected int getNextPlayerIndex() {
        int index;
        if (isReversed) {
            index = (currentPlayerIndex - 1 + players.size()) % players.size();
        } else {
            index = (currentPlayerIndex + 1) % players.size();
        }
        return index;
    }

    protected void drawCard(Player player) {
        Card drawnCard = deck.drawCard();
        player.drawCard(drawnCard);
        System.out.println(player.getName() + " draws a card");
    }

    // Main game loop
    public void play() {
        initializeGame();
        firstDraw();
        while (true) {
            if (players.isEmpty()) {
                System.out.println("No players available.");
                return;
            }

            Player player = players.get(currentPlayerIndex);
            playTurn(player);

            if (checkWinCondition(player)) {
                win(player);
                return;
            }
        }
    }


}
