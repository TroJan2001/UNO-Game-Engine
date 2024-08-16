package core;

import cards.Card;
import cards.SwapHandCard;
import color_utils.Colors;
import factory.CardFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class CustomUNO extends Game {

    public CustomUNO() {
        currentPlayerIndex = 0;
        isReversed = false;
        skipNextTurn = false;
    }

    @Override
    protected void initializeGame() {
        deck = CustomDeck.getInstance();
        super.initializeGame();
        dealCards(9);
    }

    @Override
    protected void drawCard(Player player) {
        Card drawnCard = deck.drawCard();
        player.drawCard(drawnCard);
        System.out.println(Colors.PURPLE + player.getName() + " draws a card" + Colors.RESET);

        if (drawnCard instanceof SwapHandCard) { // play extra turn and skip next player turn if you get Swap Hand Card
            System.out.println(Colors.PURPLE + player.getName() + " drew a Swap Hand Card card, taking another turn and skipping " + playerContexts.get(player).getGame().getNextPlayer() + "'s turn" + Colors.RESET);
            playTurn(player);
        }
    }

    @Override
    protected boolean checkWinCondition(Player player) {
        if (player.getHand().isEmpty())
            return true;
        if (player.getHand().contains(CardFactory.getWildCardInstance("Swap Hand")) && player.getHand().contains(CardFactory.getWildCardInstance("Wild"))) {
            showHand(player);
            System.out.println(Colors.PURPLE + "Winning by (Swap Hand - Wildcard) Advantage" + Colors.RESET);
            return true;
        }
        return false;
    }

    @Override
    protected void win(Player player) {
        if (playerContexts.size() > 2) {
            List<Map.Entry<Player, Context>> sortedPlayers = playerContexts.entrySet().stream()
                    .sorted(Comparator.comparingInt(entry -> entry.getValue().getPlayer().getHand().size()))
                    .toList();
            Player secondWinner = sortedPlayers.get(1).getKey();
            System.out.println(Colors.PURPLE + player.getName() + " wins and " + secondWinner.getName() + " is the second winner" + Colors.RESET);
        }
        else
            System.out.println(Colors.PURPLE + player.getName() + " wins!" + Colors.RESET);
    }

    @Override
    protected boolean playTurn(Player player) { //also check if the player wins by Swap Hand Advantage at the beginning of the turn
        if (checkWinCondition(player)) {
            win(player);
            return true;
        }
        return super.playTurn(player);
    }

}

