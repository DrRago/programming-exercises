package de.dhbwka.exercise.control;

import static de.dhbwka.exercise.utility.ScannerUtility.getLong;

/**
 * @author Leonhard Gahr
 */
public class LeapYear {
    public static void main(String[] args) {
        long year;
        while ((year = getLong("Enter year: ")) > 0) {
            System.out.format("%04d is %sa leap year\n", year, isLeapYear(year) ? "" : "not ");
        }
    }

    private static boolean isLeapYear(long year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else return year % 100 != 0;
    }
}
