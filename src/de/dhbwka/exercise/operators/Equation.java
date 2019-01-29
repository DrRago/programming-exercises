package de.dhbwka.exercise.operators;

import java.util.Arrays;
import java.util.InvalidPropertiesFormatException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Leonhard Gahr
 */
public class Equation {
    public static void main(String[] args) throws InvalidPropertiesFormatException {
        String equation = "3.5x² + -5x + -12 = 0";
        double a = parseParams(equation)[0];
        double b = parseParams(equation)[1];
        double c = parseParams(equation)[2];

        System.out.println(Arrays.toString(parseParams(equation)));

        if (a == 0) {
            if (b == 0) {
                System.out.println("r u stupid?");
            } else {
                System.out.printf("x = %.2f", -c / b);
            }
        } else {
            final double D = (b * b) - (4 * a * c);
            if (D >= 0) {
                System.out.printf("x_1 = %.2f%n", (-b + Math.sqrt(D))/(2*a));
                System.out.printf("x_2 = %.2f%n", (-b - Math.sqrt(D))/(2*a));
            } else {
                System.out.println("it's complex");
            }
        }


    }

    private static double[] parseParams(String equation) throws InvalidPropertiesFormatException {
        double[] params = new double[3];
        Pattern regex1 = Pattern.compile("[-+]?([0-9]*\\.[0-9]+|[0-9]+)x²");
        Pattern regex2 = Pattern.compile("[-+]?([0-9]*\\.[0-9]+|[0-9]+)x");
        Pattern regex3 = Pattern.compile("[-+]?([0-9]*\\.[0-9]+|[0-9]+)(?!.*x)");

        if (equation.trim().charAt(equation.length() - 1) != '0') {
            throw new InvalidPropertiesFormatException("Unsolved equation! Has to be = 0");
        }

        equation = equation.split("=")[0];

        Matcher matcher1 = regex1.matcher(equation);
        while (matcher1.find()) {
            params[0] = Double.parseDouble(matcher1.group().replaceAll("x²", ""));
        }

        Matcher matcher2 = regex2.matcher(equation);
        while (matcher2.find()) {
            params[1] = Double.parseDouble(matcher2.group().replaceAll("x", ""));
        }

        Matcher matcher3 = regex3.matcher(equation);
        while (matcher3.find()) {
            params[2] = Double.parseDouble(matcher3.group());
        }

        return params;
    }
}
