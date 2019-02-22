package de.dhbwka.exercise.strings;

import de.dhbwka.exercise.utility.ScannerUtility;

/**
 * @author Leonhard Gahr
 */
public class CrossTotal {
    public static void main(String[] args) {
        final char[] ALLOWED = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        final String USER_INPUT = ScannerUtility.getString("Please enter number: ", ALLOWED);
        System.out.printf("Crosstotal is %d", USER_INPUT.chars().map(Character::getNumericValue).sum());
    }
}
