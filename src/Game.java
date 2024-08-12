import java.util.*;

public abstract class Game {

    protected List<Player> players;
    protected Deck deck;
    protected DiscardPile discardPile;


    protected abstract void initializeGame();


    protected abstract void dealCards();

    protected abstract void firstDraw();

    protected abstract void playTurn(Player player);


    protected abstract boolean checkWinCondition(Player player);

    // Main game loop
    public void play() {
        initializeGame();
        dealCards();
        firstDraw();
        while (true) {
            for (Player player : players) {
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

}
