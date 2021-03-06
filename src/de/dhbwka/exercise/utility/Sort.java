package de.dhbwka.exercise.utility;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import static de.dhbwka.exercise.utility.SortUtility.*;


/**
 * Multiple sort algorithm implementations
 *
 * @author Leonhard Gahr
 */
public class Sort {

    /**
     * Perform the bogosort for generic array with statistical outputs
     *
     * @param arr the array
     * @param <T> the generic type
     */
    public static <T extends Comparable<T>> void bogoSortStats(T[] arr) {
        System.out.printf("Average case %s%n", NumberFormat.getInstance().format(faculty(arr.length)));

        BigDecimal coun = BigDecimal.ZERO;
        long startTime = System.currentTimeMillis();

        while (!isSorted(arr)) {
            coun = coun.add(BigDecimal.ONE);
            if (coun.remainder(new BigDecimal(100000)).compareTo(BigDecimal.ZERO) == 0) {
                System.out.print("\r" + NumberFormat.getInstance().format(coun));
            }
            shuffle(arr);
        }
        System.out.printf("Sorted %d elements in %s iterations and %s seconds%n", arr.length, NumberFormat.getInstance().format(coun), (System.currentTimeMillis() - startTime) / 1000);
        System.out.printf("Saved %s iterations%n", NumberFormat.getInstance().format(faculty(arr.length).subtract(coun)));
    }

    /**
     * perform the bogosort on a generic array
     *
     * @param arr the array
     * @param <T> the generic type
     */
    public static <T extends Comparable<T>> void bogoSort(T[] arr) {
        while (!isSorted(arr)) {
            shuffle(arr);
        }
    }

    /**
     * perform the bogosort on a int array
     *
     * @param arr the int array
     */
    public static void bogoSort(int[] arr) {
        while (!isSorted(arr)) {
            shuffle(arr);
        }
    }

    /**
     * perform the bozosort on a generic array
     *
     * @param arr the array
     * @param <T> the generic type
     */
    public static <T extends Comparable<T>> void bozoSort(T[] arr) {
        while (!isSorted(arr)) {
            swap(arr, randIndex(arr.length), randIndex(arr.length));
        }
    }

    /**
     * perform the recursive bogobogosort on a generic array
     *
     * @param <T> the generic type
     * @param arr the array
     */
    public static <T extends Comparable<T>> void bogoBogoSort(T[] arr) {
        while (true) {
            if (bogoBogoSortRecursive(arr)) {
                break;
            }
        }
    }

    /**
     * The solar bitflip sort for generic arrays
     * <p>
     * check if the array is sorted, if not, wait a random amount of time, pray for having bit flips caused
     * by solar radiation, put the bits just in the correct order and repeat the check <b>Doesn???t work if you have EEC memory.</b>
     *
     * @param arr the array
     * @param <T> the generic type
     */
    public static <T extends Comparable<T>> void solarBitflipSort(T[] arr) {
        while (!isSorted(arr)) {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(0, 3000));
            } catch (InterruptedException ignored) {
            }
        }
    }

    /**
     * Dictator sort for generic types
     * <p>
     * check if the array is sorted, the dictator out of the blue just claims that its sorted & anyone disagreeing
     * with it is killed
     *
     * @param arr the array
     * @param <T> the generic type
     */
    public static <T extends Comparable<T>> void dictatorSort(T[] arr) {
        if (isSorted(arr)) {
            return;
        } else {
            return;
        }
    }

    /**
     * the lucky sort algorithm
     * <p>
     * It is so lucky that the input is already sorted, and it need do nothing!
     *
     * @param arr the array
     * @param <T> the generic type
     */
    public static <T extends Comparable<T>> void luckySort(T[] arr) {
    }

    /**
     * perform the quicksort on a generic array
     *
     * @param arr the array
     * @param <T> the generic type
     */
    public static <T extends Comparable<T>> void quickSort(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }


    /**
     * perform the quicksort on a generic array
     *
     * @param arr   the array
     * @param begin the begin index (init 0)
     * @param end   the end index (init length - 1)
     * @param <T>   the generic type
     */
    private static <T extends Comparable<T>> void quickSort(T[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }


    /**
     * sleep sort for integer nums
     *
     * @param arr the array
     */
    public static void sleepSortPrint(int[] arr) {
        final CountDownLatch doneSignal = new CountDownLatch(arr.length);
        for (final int num : arr) {
            new Thread(() -> {
                doneSignal.countDown();
                try {
                    doneSignal.await();
                    Thread.sleep(num);
                    System.out.println(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    /**
     * stoogeSort implementation for generic types
     * <p>
     * swaps the top and bottom items if needed, then (recursively) sorts the bottom two-thirds, then the top two-thirds, then the bottom two-thirds again
     *
     * @param arr the array
     * @param <T> the generic type
     */
    public static <T extends Comparable<T>> void stoogeSort(T[] arr) {
        stoogeSort(arr, 0, arr.length - 1);
    }


    /**
     * stoogeSort implementation for generic types
     * <p>
     * swaps the top and bottom items if needed, then (recursively) sorts the bottom two-thirds, then the top two-thirds, then the bottom two-thirds again
     *
     * @param arr   the array
     * @param begin the begin index
     * @param end   the end index
     * @param <T>   the generic type
     */
    public static <T extends Comparable<T>> void stoogeSort(T[] arr, int begin, int end) {
        System.out.printf("%d - %d%n", begin, end);
        if (begin >= end) {
            return;
        }
        if (arr[begin].compareTo(arr[end]) > 0) {
            swap(arr, begin, end);
        }

        if (end - begin + 1 > 2) {
            double tmp = (end - begin + 1) / 3;

            stoogeSort(arr, begin, (int) (end - tmp));
            stoogeSort(arr, (int) (begin + tmp), (end));
            stoogeSort(arr, begin, (int) (end - tmp));
        }
    }
}
