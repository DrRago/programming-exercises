package de.dhbwka.exercise.control;

/**
 * @author Leonhard Gahr
 */
public class TemperatureTable {
    public static void main(String[] args) {
        String format = "%-10d | %-4f %n";

        System.out.format("Fahrenheit | Celsius%n");
        System.out.format("-----------+--------%n");
        for (int i = 0; i <= 300; i += 20) {
            System.out.format(format, i, fahrenheitToCelsius(i));
        }
    }

    private static float fahrenheitToCelsius(int fahrenheit){
        return (5f/9f) * (fahrenheit - 32f);
    }
}
