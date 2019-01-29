package de.dhbwka.exercise.arrays;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Leonhard Gahr
 */
public class StandardDeviation {
    public static void main(String[] args) {
        int[] numbers = getRandArray(100);
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

    private static int[] getRandArray(int n) {
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(0, 11);
        }
        return array;
    }
}
