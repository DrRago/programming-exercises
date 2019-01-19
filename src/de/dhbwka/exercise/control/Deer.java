package de.dhbwka.exercise.control;

import static de.dhbwka.exercise.utility.ScannerUtility.getLong;

/**
 * @author Leonhard Gahr
 */
public class Deer {
    public static void main(String[] args) {
        long deer = getLong("Enter start number of deers: ");
        int counter = 0;
        String format = "%d: %d%n";
        do {
            deer = (long) (deer * 1.1) - 15;
            System.out.format(format, ++counter, deer);
        } while (deer < 300);
    }
}
