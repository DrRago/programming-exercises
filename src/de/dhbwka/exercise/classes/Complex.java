package de.dhbwka.exercise.classes;

import de.dhbwka.exercise.utility.Sort;
import lombok.Getter;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @author Leonhard Gahr
 */

@Getter
public class Complex implements Comparable {
    private final double REAL;
    private final double IMAG;

    private final double MAGNITUDE;

    public Complex(double real, double imag) {
        this.REAL = real;
        this.IMAG = imag;

        this.MAGNITUDE = Math.hypot(real, imag);
    }

    public Complex() {
        this(0, 0);
    }

    public Complex add(Complex complex) {
        return new Complex(REAL + complex.REAL, IMAG + complex.IMAG);
    }

    public Complex subtract(Complex complex) {
        return new Complex(REAL - complex.REAL, IMAG - complex.IMAG);
    }

    public Complex mult(Complex complex) {
        return new Complex(REAL * complex.REAL - IMAG * complex.IMAG, REAL * complex.IMAG + IMAG * complex.REAL);
    }

    public Complex div(Complex complex) {
        double newReal = (REAL * complex.REAL + IMAG * complex.IMAG) / (Math.pow(complex.REAL, 2) + Math.pow(complex.IMAG, 2));
        double newImag = ((IMAG * complex.REAL) - (REAL * complex.IMAG)) / (Math.pow(complex.REAL, 2) + Math.pow(complex.IMAG, 2));
        return new Complex(newReal, newImag);
    }

    public boolean isLess(Complex complex) {
        return MAGNITUDE < complex.MAGNITUDE;
    }

    @Override
    public String toString() {
        return String.format("%.3f + %.3fi", REAL, IMAG);
    }

    @Override
    public int compareTo(Object o) {
        return Double.compare(MAGNITUDE, ((Complex) o).getMAGNITUDE());
    }

    public static void main(String[] args) {
        Complex c1 = new Complex(1, 2);
        Complex c2 = new Complex(2, 1);

        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);

        System.out.println("c1 + c2: " + c1.add(c2));
        System.out.println("c1 - c2: " + c1.subtract(c2));
        System.out.println("c1 * c2: " + c1.mult(c2));
        System.out.println("c1 / c2: " + c1.div(c2));
        System.out.println("c1 < c2: " + c1.isLess(c2));


        System.out.printf("%n%n");

        Complex[] comps = new Complex[10];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        IntStream.range(0, 10).forEach(i -> comps[i] = new Complex(random.nextDouble(0, 20), random.nextDouble(0, 20)));


        System.out.println("Unordered:");
        Arrays.stream(comps).forEach(v -> System.out.printf("%s magnitude: %.3f %n", v.toString(), v.getMAGNITUDE()));

        Sort.bogoSort(comps);

        System.out.println("Ordered:");
        Arrays.stream(comps).forEach(v -> System.out.printf("%s magnitude: %.3f %n", v.toString(), v.getMAGNITUDE()));

    }
}
