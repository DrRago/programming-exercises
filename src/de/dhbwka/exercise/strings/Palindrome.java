package de.dhbwka.exercise.strings;

import de.dhbwka.exercise.utility.ScannerUtility;

/**
 * @author Leonhard Gahr
 */
public class Palindrome {
    public static void main(String[] args) {
        String s = ScannerUtility.getString("Please enter string: ");
        String s2 = new StringBuilder(s).reverse().toString();
        System.out.printf("Reversed: %s%n%s is%s a palindrome", s2, s, s.toLowerCase().equals(s2.toLowerCase()) ? "" : "n't");
    }
}
