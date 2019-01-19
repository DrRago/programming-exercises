package de.dhbwka.exercise.control;

/**
 * @author Leonhard Gahr
 */
public class MultiplicationTable {
    public static void main(String[] args) {
        for (int y = 1; y <= 10; ++y) {
            for (int x = 1; x <= 10; ++x) {
                System.out.format("%4d", y * x);
            }
            System.out.println();
        }
    }
}
