package de.dhbwka.exercise.datatypes;

/**
 * @author Leonhard Gahr
 */
public class Round {
    public static void main(String[] args) {
        double pi = 3.1515926;
        double e = 2.7182818;

        int piInt = round(pi);
        int eInt = round(e);

        System.out.println(piInt);
        System.out.println(eInt);
    }

    private static int round(double value) {
        int intValue = (int) value;
        if (value - intValue >= 0.5) {
            return intValue + 1;
        }
        return intValue;
    }
}