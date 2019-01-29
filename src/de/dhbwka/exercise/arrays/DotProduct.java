package de.dhbwka.exercise.arrays;

import static de.dhbwka.exercise.utility.ScannerUtility.getInt;

/**
 * @author Leonhard Gahr
 */
public class DotProduct {
    public static void main(String[] args) {
        final int N = getInt("Please enter the number n of elements: ");
        final String template = "Please enter %s_%d: ";

        int[] x = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = getInt(String.format(template, "x", i));
        }

        int[] y = new int[N];
        for (int i = 0; i < N; i++) {
            y[i] = getInt(String.format(template, "y", i));
        }

        System.out.println("The dot product of x and y is " + getDotProduct(x, y));
    }

    private static int getDotProduct(int[] x, int[] y) {
        int sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += x[i] * y[i];
        }
        return sum;
    }
}
