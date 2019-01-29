package de.dhbwka.exercise.control;

import java.util.Scanner;

/**
 * @author Leonhard Gahr
 */
public class Temperature {
    public static void main(String[] args) {
        System.out.print("Enter temperature: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String fahrenheitTemplate = "%.2f°F is %.2f°C";
        String celsiusTemplate = "%.2f°C is %.2f°F";

        final int N = input.length();
        double temperature = extractDouble(input);

        switch (input.charAt(N - 1)) {
            case 'F':
            case 'f':
                System.out.format(fahrenheitTemplate, temperature, fahrenheitToCelsius(temperature));
                break;
            case 'C':
            case 'c':
                System.out.format(celsiusTemplate, temperature, celsiusToFahrenheit(temperature));
        }
    }

    private static double extractDouble(String value) {
        return Double.parseDouble(value.replaceAll("[°fFCc]", ""));
    }

    private static double fahrenheitToCelsius(double fahrenheit) {
        return 5f/9f * (fahrenheit - 32);
    }

    private static double celsiusToFahrenheit(double celsius) {
        return celsius/(5f/9f) + 32f;
    }
}
