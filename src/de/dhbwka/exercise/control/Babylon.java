package de.dhbwka.exercise.control;

import static de.dhbwka.exercise.utility.ScannerUtility.getLong;

/**
 * @author Leonhard Gahr
 */
public class Babylon {
    public static void main(String[] args) {
        long num;
        System.out.print("Sqrt number: ");
        while ((num = getLong("Sqrt number: ")) >= 0) {
            System.out.printf("Sqrt of %d is %.6f%n", num, sqrt(num));
        }
    }

    private static double sqrt(double num) {
        double x = (num + 1) / 2;
        return sqrt(num, x);
    }

    private static double sqrt(double num, double x) {
        System.out.println("xn: " + x);
        double newX = (x + (num / x)) / 2;
        if (Math.abs(newX - x) < 10e-6) {
            return x;
        }
        return sqrt(num, newX);
    }
}
