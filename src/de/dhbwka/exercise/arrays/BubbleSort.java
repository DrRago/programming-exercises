package de.dhbwka.exercise.arrays;

import static de.dhbwka.exercise.utility.ScannerUtility.getInt;

/**
 * @author Leonhard Gahr
 */
public class BubbleSort {
    public static void main(String[] args) {
        final int N = getInt("Please enter the number n of elements: ");
        String template = "Enter value %d: ";

        int[] values = new int[N];
        for (int i = 0; i < N; i++) {
            values[i] = getInt(String.format(template, i));
        }

        for (int i : bubbleSort(values, N)) {
            System.out.print(i + " ");
        }
    }

    private static int[] bubbleSort(int[] values, int n) {
        for (int i = 0; i < n - 1; i++) {
            if (values[i] > values[i + 1]) {
                int temp = values[i];
                values[i] = values[i + 1];
                values[i + 1] = temp;
            }
        }
        if (n - 1 > 1) {
            bubbleSort(values, n - 1);
        }

        return values;
    }
}
