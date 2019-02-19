package de.dhbwka.exercise.utility;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Leonhard Gahr
 */
public class ScannerUtility {
    public static int[] getIntArray(String message) {
        final int N = getInt("Please enter the number n of elements: ");
        return getIntArray(message, N);
    }

    public static int[] getIntArray(String message, int N) {
        int[] values = new int[N];
        for (int i = 0; i < N; i++) {
            values[i] = getInt(String.format(message, i));
        }

        return values;
    }

    public static String getString(String message, char[] allowed, int len) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print(message);

            String result = scanner.nextLine().toLowerCase();
            if (result.chars().allMatch(c -> new String(allowed).indexOf(c) != -1)) {
                if (result.length() != len) {
                    System.out.printf("Illegal input. Must be exactly %d characters.%n", len);
                    continue;
                }
                return result;
            } else {
                System.out.printf("Illegal input. Mut be one of (%s)%n", String.join(", ", CharBuffer.wrap(allowed).chars().mapToObj(intValue -> String.valueOf((char) intValue)).toArray(String[]::new)));
            }
        }
    }

    public static String getString(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);

        return scanner.nextLine();
    }

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
