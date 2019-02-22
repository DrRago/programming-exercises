package de.dhbwka.exercise.classes;

import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Leonhard Gahr
 */

@Setter
public class Polynomial {
    private final double A;
    private final double B;
    private final double C;

    /**
     * initialize zero polynomial
     */
    public Polynomial() {
        this(0, 0, 0);
    }

    /**
     * Initialize polynomial with double values
     *
     * @param a the a
     * @param b the b
     * @param c the c
     */
    public Polynomial(double a, double b, double c) {
        this.A = a;
        this.B = b;
        this.C = c;
    }

    /**
     * Parse a polynomial string
     *
     * @param polynomial the polynomial string
     */
    public Polynomial(String polynomial) {
        List<Double> as = new ArrayList<>();
        List<Double> bs = new ArrayList<>();
        List<Double> cs = new ArrayList<>();


        final String regexA = "^([+-]? ?\\d+([.,]\\d+)?x(\\^2|²))";
        final String regexB = "[+-] ?\\d+([.,]\\d+)?x";
        final String regexC = "[+-] ?\\d+([.,]\\d+)?$";

        final Pattern patternA = Pattern.compile(regexA);
        final Pattern patternB = Pattern.compile(regexB);
        final Pattern patternC = Pattern.compile(regexC);

        final Matcher matcherA = patternA.matcher(polynomial);
        final Matcher matcherB = patternB.matcher(polynomial);
        final Matcher matcherC = patternC.matcher(polynomial);

        while (matcherA.find()) {
            String match = matcherA.group(0).toLowerCase().replaceAll(" ", "");
            as.add(Double.parseDouble(match.substring(0, match.indexOf('x'))));
        }

        while (matcherB.find()) {
            String match = matcherB.group(0).toLowerCase().replaceAll(" ", "");
            bs.add(Double.parseDouble(match.substring(0, match.indexOf('x'))));
        }

        while (matcherC.find()) {
            String match = matcherC.group(0).toLowerCase().replaceAll(" ", "");
            cs.add(Double.parseDouble(match));
        }

        this.A = as.stream().mapToDouble(Double::doubleValue).sum();
        this.B = bs.stream().mapToDouble(Double::doubleValue).sum();
        this.C = cs.stream().mapToDouble(Double::doubleValue).sum();
    }

    public Polynomial add(Polynomial p) {
        return new Polynomial(A + p.A, B + p.B, C + p.C);
    }

    public Polynomial subtract(Polynomial p) {
        return new Polynomial(A - p.A, B - p.B, C - p.C);
    }

    public Polynomial multiply(double d) {
        return new Polynomial(A * d, B * d, C * d);
    }

    /**
     * calculate roots of the polynomial
     *
     * @return the roots as array of length 2
     */
    public double[] roots() {
        double[] roots = new double[2];
        if (A == 0) {
            if (B == 0) {
                return null;
            } else {
                roots[0] = -C / B;
            }
        } else {
            final double D = (B * B) - (4 * A * C);
            if (D >= 0) {
                roots[0] = (-B + Math.sqrt(D)) / (2 * A);
                roots[1] = (-B - Math.sqrt(D)) / (2 * A);
            } else {
                return null;
            }
        }
        return roots;
    }

    @Override
    public String toString() {
        return ((A == 0 ? "" : A + "x²") + (B == 0 ? "" : doubleToString(B) + "x") + (C == 0 ? "" : doubleToString(C))).trim();
    }

    private String doubleToString(double d) {
        return (d < 0 ? " - " : " + ") + Math.abs(d);
    }

    public static void main(String[] args) {
        Polynomial p1 = new Polynomial("2.0x^2 + 0.0x + 0.0");
        System.out.println("p1: " + p1);

        Polynomial p2 = new Polynomial("0.0x^2 -4.0x +1.0");
        System.out.println("p2: " + p2);

        Polynomial p3 = p1.add(p2);
        System.out.println("p3: " + p3);

        p3 = p3.multiply(2);

        System.out.println("p3: " + p3);

        System.out.println("roots of p3: " + Arrays.toString(p3.roots()));
    }
}
