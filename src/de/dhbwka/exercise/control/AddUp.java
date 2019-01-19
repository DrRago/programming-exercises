package de.dhbwka.exercise.control;

import static de.dhbwka.exercise.utility.ScannerUtility.getLong;

/**
 * @author Leonhard Gahr
 */
public class AddUp {
    public static void main(String[] args) {
        addUpDoWhile();
        addUpWhile();
    }

    private static void addUpDoWhile() {
        long num = 0;
        long input = 0;
        do {
            num += input;
        } while ((input = getLong("Enter number (<0 for abortion): ")) >= 0);

        System.out.println("Sum: " + num);
    }

    private static void addUpWhile() {
        long num = 0;
        long input;
        while ((input = getLong("Enter number (<0 for abortion): ")) >= 0) {
            num += input;
        }
        System.out.println("Sum: " + num);
    }
}
