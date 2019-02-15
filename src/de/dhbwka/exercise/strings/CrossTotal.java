package de.dhbwka.exercise.strings;

import de.dhbwka.exercise.utility.ScannerUtility;

/**
 * @author Leonhard Gahr
 */
public class CrossTotal {
    public static void main(String[] args) {
        System.out.printf("Crosstotal is %d", ScannerUtility.getString("Please enter number: ").chars().map(x -> x -= 48).sum());
    }
}
