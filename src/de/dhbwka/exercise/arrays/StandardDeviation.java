package de.dhbwka.exercise.arrays;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Leonhard Gahr
 */
public class StandardDeviation {
    public static void main(String[] args) {
        int[] numbers = ThreadLocalRandom.current().ints(100).toArray();
        System.out.println("Mean: " + getMean(numbers));
        System.out.println("Standard deviation: " + Math.sqrt(getVariance(numbers)));
    }

    private static double getVariance(int[] numbers) {
        double mean = getMean(numbers);
        double sum = 0;
        for (int number : numbers) {
            sum += Math.pow(number - mean, 2);
        }
        return sum / (double) (numbers.length - 1);
    }

    private static double getMean(int[] numbers) {
        double sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }
}
