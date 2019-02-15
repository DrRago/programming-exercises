package de.dhbwka.exercise.classes;

import de.dhbwka.exercise.utility.ScannerUtility;

import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Leonhard Gahr
 */
public class MasterMind {
    /**
     * Play the mastermind game
     *
     * @param tries the maximum amount of tries allowed
     * @return whether the player won or not
     */
    public static boolean play(int tries) {
        String toGuess = randomString();
        StringBuilder hints = new StringBuilder();
        char[] allowed = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        int n = 0;
        String input;
        while (n < tries && !(input = ScannerUtility.getString("Enter hier: ", allowed, 5)).equals(toGuess)) {
            hints.append(input).append(" ").append(String.join(" ", getHint(toGuess, input))).append("\n");
            System.out.println(hints);
            n++;
        }

        if (n == tries) {
            System.out.printf("You're to stupid for this game and lost after %d tries", n);
            return false;
        } else {
            System.out.printf("You won after %d tries", n);
            return true;
        }
    }

    /**
     * Generate a random string with the length of 5 between the letters 'a' amd 'h' (inclusive)
     *
     * @return the generated string
     */
    private static String randomString() {
        return new Random().ints(5, 97, 105).mapToObj(x -> Character.toString((char) x)).collect(Collectors.joining());
    }

    /**
     * Generate the hints for the game. the first value is the number of correct letters at the correct indices,
     * the second the correct letters at wrong indices, excluding the first ones
     *
     * @param s1 the string to guess
     * @param s2 the guessed string
     * @return the values as strings
     */
    private static String[] getHint(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int[] result = new int[2];

        for (int i = 0; i < c1.length; i++) {
            if (c1[i] == c2[i]) {
                c1[i] = '\0';
                c2[i] = '\0';
                result[0]++;
            }
        }

        for (int i = 0; i < c1.length; i ++){
            if(c1[i] == '\0') continue;
            for (int y = 0; y < c2.length; y++) {
                if (c2[y]=='\0') continue;
                if (c1[i] == c2[y]) {
                    result[1] ++;
                    c1[i] = '\0';
                    c2[y] = '\0';
                }
            }
        }


        return new String[]{String.valueOf(result[0]), String.valueOf(result[1])};
    }

    public static boolean play() {
        return play(20);
    }

    public static void main(String[] args) {
        MasterMind.play();
    }
}
