package de.dhbwka.exercise.utility;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Leonhard Gahr
 */
class SortUtility {
    static BigDecimal faculty(int num) {
        return num == 1 ? BigDecimal.ONE : BigDecimal.valueOf(num).multiply(faculty(num - 1));
    }

    static <T> void shuffle(T[] arr) {
        for (int x = 0; x < arr.length; ++x) {
            int index1 = (int) (Math.random() * arr.length),
                    index2 = (int) (Math.random() * arr.length);
            T temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;
        }
    }

    static void shuffle(int[] arr) {
        for (int x = 0; x < arr.length; ++x) {
            int index1 = (int) (Math.random() * arr.length),
                    index2 = (int) (Math.random() * arr.length);
            int temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;
        }
    }

    static <T extends Comparable<T>> boolean isSorted(T[] i) {
        for (int x = 0; x < i.length - 1; ++x) {
            if (i[x].compareTo(i[x + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    static boolean isSorted(int[] arr) {
        for (int x = 0; x < arr.length - 1; ++x) {
            if (arr[x] > arr[x + 1]) {
                return false;
            }
        }
        return true;
    }

    static <T extends Comparable<T>> int partition(T[] arr, int begin, int end) {
        T pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;

                swap(arr, i, j);
            }
        }

        swap(arr, end, i + 1);
        return i + 1;
    }

    static <T> void swap(T[] arr, int i1, int i2) {
        T temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    static int randIndex(int length) {
        return ThreadLocalRandom.current().nextInt(0, length);
    }

    /**
     * Recursive bogobogosort implementation
     *
     * @param arr the array
     * @param <T> the generic type
     * @return whether the array is sorted
     */
    static <T extends Comparable<T>> boolean bogoBogoSortRecursive(T[] arr) {
        swap(arr, randIndex(arr.length), randIndex(arr.length));

        T[] arrayToCheck = Arrays.copyOf(arr, arr.length + 1);

        if (isSorted(arr)) {
            return true;
        } else if (isSorted(arrayToCheck)) {
            return bogoBogoSortRecursive(arr);
        } else {
            return false;
        }
    }
}
