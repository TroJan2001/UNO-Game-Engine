import java.util.*;

public class ClassicUno extends Game{

    private Map<Player, Context> playerContexts;


    @Override
    protected void initializeGame() {
        players = new ArrayList<>();
        discardPile = new DiscardPile();
        deck = new MyDeck();
        deck.shuffle();
        playerContexts = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter Player 1 name?");
        Player player1 = new Player(scanner.nextLine());
        players.add(player1);
        playerContexts.put(player1, new Context(discardPile,deck,scanner,this));
        System.out.println("Please Enter Player 2 name?");
        Player player2 = new Player(scanner.nextLine());
        players.add(player2);
        playerContexts.put(player2, new Context(discardPile,deck,scanner,this));
        while(true){
            System.out.println("Do you want to add a new player? (Y/N)");
            if(scanner.nextLine().equalsIgnoreCase("y") && players.size()<10){
                System.out.println("Please Enter Player name?");
                Player newPlayer = new Player(scanner.nextLine());
                players.add(newPlayer);
                playerContexts.put(newPlayer, new Context(discardPile,deck,scanner,this));
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
            currentPlayerIndex = getNextPlayerIndex();
            return;
        }

        Context context = playerContexts.get(player);
        // Show the player's hand
        System.out.println(player.getName() + "'s turn. " + player.getName() + "'s cards: ");
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
                if (card instanceof Executable executable) {
                    executable.execute(context);
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
        currentPlayerIndex = getNextPlayerIndex();
    }

    @Override
    protected boolean checkWinCondition(Player player) {
        return player.getHand().isEmpty();
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

    @Override
    protected Player getNextPlayer() {
        return players.get(getNextPlayerIndex());
    }

    @Override
    protected void reverseDirection() {
        isReversed = !isReversed; // Toggle the direction
        System.out.println("Direction reversed");
    }

    @Override
    protected void skipNextTurn() {
       skipNextTurn=true;
       System.out.println("Skipping Next Player Turn");
    }


}

