package de.dhbwka.exercise.control;

import java.io.*;

/**
 * @author Leonhard Gahr
 */
public class MultiplicationTable {
    public static void main(String[] args) throws IOException {
        double val = 10;
        for (int y = 1; y <= val; ++y) {
            for (int x = 1; x <= val; ++x) {
                System.out.printf("%10d", y * x);
            }
            System.out.println();
        }

    }
}
