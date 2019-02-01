package de.dhbwka.exercise.arrays;

import static de.dhbwka.exercise.utility.ScannerUtility.getInt;

/**
 * @author Leonhard Gahr
 */
public class Pascal {
    public static void main(String[] args) {
        int depth = getInt("Enter depth: ");

        final int MAX_LENGTH = String.valueOf(binomialCoefficient(depth-1, (depth-1)/2)).length() + 1;
        final String FORMAT = "%" + MAX_LENGTH + "d";

        for (int n = 0; n < depth; n++) {
            System.out.print(new String(new char[(depth - (n + 1)) * MAX_LENGTH / 2]).replaceAll("\00", " "));
            for (int k = 0; k < n + 1; k++) {
                System.out.printf(FORMAT, binomialCoefficient(n, k));
            }
            System.out.println();
        }
    }

    private static int binomialCoefficient(int n, int k) {
        if ((n == k) || (k == 0)) {
            return 1;
        } else {
            return binomialCoefficient(n - 1, k) + binomialCoefficient(n - 1, k - 1);
        }
    }
}
