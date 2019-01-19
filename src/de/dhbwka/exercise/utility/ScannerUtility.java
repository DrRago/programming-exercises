package de.dhbwka.exercise.utility;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Leonhard Gahr
 */
public class ScannerUtility {
    public static long getLong(String message) {
        return getLong(message, new long[0]);
    }
    public static long getLong(String message, long[] limits) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print(message);
                long input = scanner.nextLong();
                if (limits.length != 0 && Arrays.stream(limits).noneMatch(e -> e == input)) {
                    System.out.println("Invalid input!");
                } else {
                    return input;
                }
            } catch (InputMismatchException e) {
                System.out.println("Illegal character!");
            }
        }
    }

    public static int getInt(String message) {
        return getInt(message, new int[0]);
    }

    public static int getInt(String message, int[] limits) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print(message);
                int input = scanner.nextInt();
                if (limits.length != 0 && Arrays.stream(limits).noneMatch(e -> e == input)) {
                    System.out.println("Invalid input!");
                } else {
                    return input;
                }
            } catch (InputMismatchException e) {
                System.out.println("Illegal character!");
            }
        }
    }

}
