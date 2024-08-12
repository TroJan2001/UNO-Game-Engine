import java.util.List;

public abstract class Game {

    protected List<Player> players;
    protected Deck deck;
    protected DiscardPile discardPile;
    protected int currentPlayerIndex = 0;
    protected boolean isReversed = false;
    protected boolean skipNextTurn = false;


    protected abstract void initializeGame();

    protected abstract void skipNextTurn();

    protected abstract void reverseDirection();

    protected abstract void dealCards();

    protected abstract void firstDraw();

    protected abstract void playTurn(Player player);


    protected abstract boolean checkWinCondition(Player player);

    protected abstract Player getNextPlayer();

    // Main game loop
    public void play() {
        initializeGame();
        dealCards();
        firstDraw();
        while (true) {
            if (players.isEmpty()) {
                System.out.println("No players available.");
                return;
            }

            Player player = players.get(currentPlayerIndex);
            playTurn(player);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (checkWinCondition(player)) {
                System.out.println(player.getName() + " wins!");
                return;
            }
        }
    }


}
