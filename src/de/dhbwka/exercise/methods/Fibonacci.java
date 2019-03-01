package de.dhbwka.exercise.methods;

import de.dhbwka.exercise.utility.ScannerUtility;

/**
 * @author Leonhard Gahr
 */
public class Fibonacci {

    private static long getFibonacci(long n) {
        return n <= 1 ? n : getFibonacci(--n) + getFibonacci(--n);
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println(getFibonacci(ScannerUtility.getInt("#fibonacci: ")));
        }
    }
}
