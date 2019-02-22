package de.dhbwka.exercise.strings;

import de.dhbwka.exercise.utility.ScannerUtility;

/**
 * @author Leonhard Gahr
 */
public class Palindrome {
    public static void main(String[] args) {
        final String INPUT = ScannerUtility.getString("Please enter string: ");
        final String REVERSED = new StringBuffer(INPUT).reverse().toString();
        System.out.printf("Reversed: '%s'%n'%s' is%s a palindrome", REVERSED, INPUT, INPUT.equalsIgnoreCase(REVERSED) ? "" : "n't");
    }
}
