package de.dhbwka.exercise.arrays;

import de.dhbwka.exercise.utility.ScannerUtility;

import java.util.Arrays;

/**
 * @author Leonhard Gahr
 */
public class Fibonacci {
    public static void main(String[] args) {
        Arrays.stream(getFibonacci()).forEach(System.out::println);
    }

    private static int[] getFibonacci() {
        return getFibonacci(new int[]{1, 1}, ScannerUtility.getInt("Please enter depth: "));
    }

    private static int[] getFibonacci(int[] nums, final int LIMIT) {
        if (nums.length >= LIMIT) return nums;
        int[] newNums = append(nums, nums[nums.length - 2] + nums[nums.length - 1]);
        return getFibonacci(newNums, LIMIT);
    }

    private static int[] append(int[] arr, int element) {
        int[] arr2 = Arrays.copyOf(arr, arr.length + 1);
        arr2[arr.length] = element;
        return arr2;
    }
}
