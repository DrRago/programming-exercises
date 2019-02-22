package de.dhbwka.exercise.classes;

import de.dhbwka.exercise.utility.ScannerUtility;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author Leonhard Gahr
 */
public class Nimmspiel {

    private final String MAKE_MOVE_TEMPLATE_PILE = "Player %s'%s turn: from which pile do you want to drag? (1, 2) ";
    private final String MAKE_MOVE_TEMPLATE_NUM = "Player %s: how many balls? (1 %s %d) ";

    private String currentPlayer;
    private String player1;
    private String player2;
    private final int[] piles;

    /**
     * Initialize the game with the player names and random pile sizes between 1 and 10
     *
     * @param player1 the first player, who starts the game
     * @param player2 the second player
     */
    public Nimmspiel(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;

        // player one starts the game, so player 2 gets initialized due to do-while loop
        this.currentPlayer = player2;

        piles = new Random().ints(2, 1, 11).toArray();

    }

    /**
     * Check whether the game is over
     *
     * @return the boolean if the game is over
     */
    private boolean gameOver() {
        return Arrays.stream(piles).sum() == 0;
    }

    /**
     * Change the current player to the next one
     */
    private void nextPlayer() {
        currentPlayer = currentPlayer.equals(player1) ? player2 : player1;
    }

    private void makeMove(int pile, int num) throws OperationNotSupportedException {
        if (piles[pile] < num) throw new OperationNotSupportedException("You cannot take more balls than there are!");
        piles[pile] -= num;
    }

    /**
     * Play the game until it's finished
     *
     * @return the name of the winning player
     */
    private String play() throws OperationNotSupportedException {
        do {
            nextPlayer();

            System.out.println(this);

            // automatically select the correct pile, if only one pile is possible
            int pile;
            if (piles[0] == 0) {
                pile = 1;
            } else if (piles[1] == 0) {
                pile = 0;
            } else {
                pile = ScannerUtility.getInt(String.format(MAKE_MOVE_TEMPLATE_PILE, currentPlayer, currentPlayer.matches("^[a-zA-Z]*[szSZ]$") ? "" : "s"), new int[]{1, 2}) - 1;
            }

            // automatically make that move when there is no other possibility
            if (piles[pile] == 1) {
                makeMove(pile, 1);
                continue;
            }

            int num = ScannerUtility.getInt(String.format(MAKE_MOVE_TEMPLATE_NUM, currentPlayer, piles[pile] == 2 ? "or" : "-", piles[pile]), IntStream.range(1, piles[pile] + 1).toArray());
            makeMove(pile, num);

        } while (!gameOver());

        System.out.printf("Game over%nPlayer %s has won!", currentPlayer);
        return currentPlayer;
    }

    @Override
    public String toString() {
        return String.format("Players %s and %s, pile 1: %d ball%s, pile 2: %d ball%s", player1, player2, piles[0], piles[0] == 1 ? "" : "s", piles[1], piles[1] == 1 ? "" : "s");
    }

    public static void main(String[] args) throws OperationNotSupportedException {
        Nimmspiel game = new Nimmspiel("Hans", "Peter");
        game.play();
    }
}
