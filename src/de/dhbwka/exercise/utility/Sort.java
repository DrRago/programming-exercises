package de.dhbwka.exercise.utility;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author Leonhard Gahr
 */


public class Sort {
    private static <T> void shuffle(T[] arr) {
        for (int x = 0; x < arr.length; ++x) {
            int index1 = (int) (Math.random() * arr.length),
                    index2 = (int) (Math.random() * arr.length);
            T temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;
        }
    }

    private static void shuffle(int[] arr) {
        for (int x = 0; x < arr.length; ++x) {
            int index1 = (int) (Math.random() * arr.length),
                    index2 = (int) (Math.random() * arr.length);
            int temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;
        }
    }

    private static <T extends Comparable<T>> boolean isSorted(T[] i) {
        for (int x = 0; x < i.length - 1; ++x) {
            if (i[x].compareTo(i[x + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSorted(int[] arr) {
        for (int x = 0; x < arr.length - 1; ++x) {
            if (arr[x] > arr[x + 1]) {
                return false;
            }
        }
        return true;
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int begin, int end) {
        T pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;

                T swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        T swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }

    public static <T extends Comparable<T>> void bogoSort(T[] arr) {
        while (!isSorted(arr)) {
            shuffle(arr);
        }
    }

    public static void bogoSort(int[] arr) {
        while (!isSorted(arr)) {
            shuffle(arr);
        }
    }

    public static void bogoSort(int[] arr, BigInteger counter) {
        while (!isSorted(arr)) {
            counter = counter.add(BigInteger.ONE);
            shuffle(arr);
        }
        System.out.print(counter);
    }

    public static <T extends Comparable<T>> void quickSort(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }


    private static <T extends Comparable<T>> void quickSort(T[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{213, 213, 21, 2, 5235, 46, 54, 634, 6, 32, 623, 523, 325, 18};
        System.out.printf("I've sorted %s with only ", Arrays.toString(arr));
        bogoSort(arr, BigInteger.ZERO);
        System.out.println(" iterations!");
    }
}
