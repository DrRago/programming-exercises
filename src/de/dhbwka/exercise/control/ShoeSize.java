package de.dhbwka.exercise.control;

/**
 * @author Leonhard Gahr
 */
public class ShoeSize {
    private static double shoeSizeToMaxCentimeter(int shoeSize) {
        return ((double) shoeSize) / 1.5;
    }

    private static double shoeSizeToMinCentimeter(int shoeSize) {
        return shoeSizeToMaxCentimeter(shoeSize - 1);
    }

    public static void main(String[] args) {
        String format = "%.2f - %.2f | %d%n";

        System.out.println("Zentimeter    | Größe");
        System.out.println("--------------+------");
        for (int i = 30; i < 50; ++i) {
            System.out.format(format, shoeSizeToMinCentimeter(i), shoeSizeToMaxCentimeter(i), i);
        }
    }
}