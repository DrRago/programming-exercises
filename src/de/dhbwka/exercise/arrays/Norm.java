package de.dhbwka.exercise.arrays;

import static de.dhbwka.exercise.utility.ScannerUtility.getInt;

/**
 * @author Leonhard Gahr
 */
public class Norm {
    public static void main(String[] args) {
        final String template = "Please enter x_%d: ";

        final int N = getInt("Please enter the number n of elements: ");
        int[] elements = new int[N];
        for (int i = 0; i < N; i++) {
            elements[i] = getInt(String.format(template, i));
        }

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
