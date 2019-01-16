package de.dhbwka.exercise.operators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Leonhard Gahr
 */
public class Easter {
    public static void main(String[] args) {
        int year = 2020;
        int offset = easter(year);

        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        String inputString1 = "01 03 " + year;

        try {
            Date date1 = myFormat.parse(inputString1);
            date1.setDate(offset);
            System.out.println(String.format("%02d/%02d/%04d", date1.getDate(), date1.getMonth(), year));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


    private static int easter(int year) {
        int a = year % 19;
        int b = year % 4;
        int c = year % 7;
        int k = year / 100;
        int p = (8 * k + 13) / 25;
        int q = k / 4;
        int m = (15 + k - p - q) % 30;
        int n = (4 + k - q) % 7;
        int d = (19 * a + m) % 30;
        int e = (2 * b + 4 * c + 6 * d + n) % 7;
        return (22 + d + e);
    }
}
