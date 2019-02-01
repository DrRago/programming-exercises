package de.dhbwka.exercise.arrays;

import static de.dhbwka.exercise.utility.ScannerUtility.getIntArray;

/**
 * @author Leonhard Gahr
 */
public class Norm {
    public static void main(String[] args) {
        int[] elements = getIntArray("Please enter x_%d: ");
        System.out.println("The norm of x is " + getNorm(elements));
    }

    private static double getNorm(int[] elements) {
        int sum = 0;
        for (int element : elements) {
            sum += Math.pow(element, 2);
        }
        return Math.sqrt(sum);
    }
}
