package core;

import cards.Card;
import cards.Executable;
import color_utils.Colors;

import java.util.*;

public abstract class Game {

    protected List<Player> players;
    protected Deck deck;
    protected DiscardPile discardPile;
    protected int currentPlayerIndex;
    protected boolean isReversed;
    protected boolean skipNextTurn;
    protected Map<Player, GameContext> playerContexts;

    protected abstract boolean checkWinCondition(Player player);

    protected abstract void win(Player player);

    public void skipNextTurn() {
        skipNextTurn = true;
        System.out.println(Colors.PURPLE + "Skipping " + getNextPlayer().getName() + "'s Turn" + Colors.RESET);
    }

    public void reverseDirection() {
        isReversed = !isReversed; // Toggle the direction
        System.out.println(Colors.PURPLE + "Direction reversed" + Colors.RESET);
    }

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
                System.out.println("Invalid input!");
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
        playerContexts.put(player, new GameContext(player, discardPile, deck, scanner, this));
        return false;
    }

    protected void firstCard() {
        discardPile.addCard(deck.drawFirstCard());
        System.out.println(Colors.PURPLE + "Top card is " + discardPile.getTopCard() + Colors.RESET);
    }

    protected boolean isNameInUse(String playerName) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(playerName)) {
                return true;
            }
        }
        return false;
    }

    protected boolean playTurn(Player player) {
        if (skipNextTurn) {
            skipNextTurn = false;
            currentPlayerIndex = getNextPlayerIndex();
            return false;
        }
        showHand(player);
        boolean cardPlayed = attemptPlayCard(player);


        if (!cardPlayed) {
            drawCard(player);
        }
        currentPlayerIndex = getNextPlayerIndex();

        if (checkWinCondition(player)) {
            win(player);
            return true;
        }
        return false;
    }

    protected void dealCards(int numOfCards) {
        if (numOfCards < 2 || numOfCards > 9) {
            System.out.println(Colors.PURPLE + "number of cards not in range ... Defaulting to 7" + Colors.RESET);
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
                System.out.println(Colors.PURPLE + player.getName() + " plays " + Colors.RESET + card);
                cardPlayed = true;

                // Handle Executable cards
                if (card instanceof Executable executable) {
                    GameContext gameContext = playerContexts.get(player);
                    executable.execute(gameContext);
                }
                break;
            }
        }

        return cardPlayed;
    }

    protected void showHand(Player player) {
        System.out.println(Colors.PURPLE + player.getName() + "'s turn. " + player.getName() + "'s cards:" + Colors.RESET);
        System.out.println("----------------------------------------------------------------------------------------------");
        for (Card card : player.getHand()) {
            System.out.println(card);
        }
        System.out.println("----------------------------------------------------------------------------------------------");
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
        System.out.println(Colors.PURPLE + player.getName() + " draws a card" + Colors.RESET);
    }

    // Main game loop
    public void play() {
        initializeGame();
        firstCard();
        while (true) {
            if (players.isEmpty()) {
                System.out.println(Colors.PURPLE + "No players available." + Colors.RESET);
                return;
            }

            Player player = players.get(currentPlayerIndex);

            if(playTurn(player))
                return;
        }
    }


}
