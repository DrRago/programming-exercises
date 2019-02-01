package de.dhbwka.exercise.arrays;

import java.util.concurrent.ThreadLocalRandom;

import static de.dhbwka.exercise.utility.ScannerUtility.getInt;

/**
 * @author Leonhard Gahr
 */
public class MatrixSubtraction {
    public static void main(String[] args) {
        int n = getInt("Enter number of rows n: ");
        int m = getInt("Enter number of columns m: ");

        int[][] X = randomMatrix(n, m);
        int[][] Y = randomMatrix(n, m);

        int[][] res = subtract(X, Y);

        System.out.println("X:");
        printArray(X);

        System.out.println("Y:");
        printArray(Y);

        System.out.println("X-Y:");
        printArray(res);

    }

    private static void printArray(int[][] arr) {
        for (int[] ints : arr) {
            System.out.print("\t");
            for (int val : ints) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }

    private static int[][] subtract(int[][] m1, int[][] m2) {
        assert m1.length == m2.length & m2.length != 0 & m1[0].length == m2[0].length;

        int[][] m = new int[m1.length][m1[0].length];

        for (int row = 0; row < m1.length; row++) {
            for (int col = 0; col < m1[0].length; col++) {
                m[row][col] = m1[row][col] - m2[row][col];
            }
        }

        return m;
    }

    private static int[][] randomMatrix(int n, int m) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int[][] matrix = new int[n][m];

        for (int row = 0; row < n; row++) {
            matrix[row] = random.ints(m).toArray();
        }
        return matrix;
    }
}
