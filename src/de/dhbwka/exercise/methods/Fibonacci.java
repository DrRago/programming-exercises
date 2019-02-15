package de.dhbwka.exercise.methods;

import de.dhbwka.exercise.utility.ScannerUtility;

/**
 * @author Leonhard Gahr
 */
public class Fibonacci {

    public static void getFibonacci(long n) {
        System.out.println(getFibonacci(n, 1, 1));
    }

    private static String getFibonacci(long n, long prev1, long prev2) {
        if (n <= 0) return String.valueOf(prev1 + prev2);
        return prev1 + "\n" + getFibonacci(n - 1, prev2, prev1 + prev2);
    }

    public static void main(String[] args) {
        getFibonacci(ScannerUtility.getInt("Please enter the depth: "));
    }
}
