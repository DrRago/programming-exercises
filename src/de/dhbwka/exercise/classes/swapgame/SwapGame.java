package de.dhbwka.exercise.classes.swapgame;

import de.dhbwka.exercise.utility.ScannerUtility;

/**
 * Controller for the swapgame field
 *
 * @author Leonhard Gahr
 */
public class SwapGame {

    public static void main(String[] args) {
        Field field = new Field(10, 10, 7);
        mainLoop(field);
    }

    private static void mainLoop(Field field) {
        System.out.println(field);
        while (true) {
            int[] coords1 = getInput("Enter point 1: ");
            int[] coords2 = getInput("Enter point 2: ");

            field.makeMove(new Point2D(coords1), new Point2D(coords2));
            System.out.printf("New Score: %d%n", field.getScore());
            System.out.println(field);
        }
    }

    private static int[] getInput(String message) {
        char[] allowed = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        String in = ScannerUtility.getString(message, allowed, 2);
        return in.chars().map(Character::getNumericValue).toArray();
    }
}
