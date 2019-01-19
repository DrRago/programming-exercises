package de.dhbwka.exercise.operators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Leonhard Gahr
 */
public class Easter {
    public static void main(String[] args) throws ParseException {
        while (true) {
            System.out.print("Enter year: ");
            long year;

            try {
                Scanner in = new Scanner(System.in);
                year = in.nextLong();
            } catch (InputMismatchException e) {
                System.out.print("Illegal character");
                System.out.println(String.format("Number must be between %s and %s", Long.toString(Long.MIN_VALUE), Long.toString(Long.MAX_VALUE)));
                continue;
            }

            if (year <= 0) {
                break;
            }
            int offset = easter(year);

            SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
            String inputString1 = "01 03 " + year;

            Date date1 = myFormat.parse(inputString1);
            date1.setDate(offset);
            System.out.println(String.format("%02d/%02d/%04d", date1.getDate(), date1.getMonth(), year));
        }
    }


    private static int easter(long year) {
        long a = year % 19;
        long b = year % 4;
        long c = year % 7;
        long k = year / 100;
        long p = (8 * k + 13) / 25;
        long q = k / 4;
        long m = (15 + k - p - q) % 30;
        long n = (4 + k - q) % 7;
        long d = (19 * a + m) % 30;
        long e = (2 * b + 4 * c + 6 * d + n) % 7;
        return (int) (22 + d + e);
    }
}
