package de.dhbwka.exercise.control;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static de.dhbwka.exercise.utility.ScannerUtility.getInt;

/**
 * @author Leonhard Gahr
 */
public class NumberGuess {
    public static void main(String[] args) {
        String guessTemplate = "%s, guess a number [1 - 100]: ";
        String guessHighTemplate = "Try %d: %d is too high.%n";
        String guessLowTemplate = "Try %d: %d is too low.%n";
        String guessCorrectTemplate = "Try %d: %d is correct.%n";
        String endMessage = "What would you like to do?\n" +
                "0 - Exit\n" +
                "1 - Continue ";
        int[] states = new int[]{0, 1};

        Scanner scanner = new Scanner(System.in);
        System.out.print("What's your name? ");
        String name = scanner.nextLine();

        int state = 1;
        while (state == 1) {
            int tryNum = 0;
            int num = ThreadLocalRandom.current().nextInt(1, 101);
            int guess;

            do {
                guess = getInt(String.format(guessTemplate, name));

                if (guess > num) {
                    System.out.printf(guessHighTemplate, ++tryNum, guess);
                } else if (guess < num) {
                    System.out.printf(guessLowTemplate, ++tryNum, guess);
                } else {
                    System.out.printf(guessCorrectTemplate, ++tryNum, guess);
                }
            } while (num != guess);
            state = getInt(endMessage, states);
        }
    }
}
