package de.dhbwka.exercise.classes;

import java.util.Arrays;

/**
 * @author Leonhard Gahr
 */
public class Horner {
    private final double[] FACTORS;

    public Horner() {
        this(0);
    }

    public Horner(double... factors) {
        FACTORS = factors;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < FACTORS.length; i++) {
            s.append(FACTORS[i] < 0 ? " - " : " + ").append(Math.abs(FACTORS[i])).append("x^").append(FACTORS.length - i - 1);
        }
        if (FACTORS[FACTORS.length - 1] >= 0) {
            s.delete(0, 3);
        }
        return s.toString().trim();
    }

    public double getValue(double x) {
        return getValue(x, FACTORS);
    }

    private double getValue(double x, double[] factors) {
        return factors.length == 1 ? factors[0] : factors[factors.length - 1] + x * getValue(x, Arrays.copyOf(factors, factors.length - 1));
    }


    public static void main(String[] args) {
        System.out.println(new Horner(0.5, -3, 2, 4, 3, -10, 8, 4.5, 3, -2, 1));
        System.out.println(new Horner(0.5, -3, 2, 4, 3, -10, 8, 4.5, 3, -2, 1).getValue(1.5));
    }
}
