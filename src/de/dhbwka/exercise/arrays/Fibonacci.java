package de.dhbwka.exercise.arrays;

import java.util.Arrays;

/**
 * @author Leonhard Gahr
 */
public class Fibonacci {
    public static void main(String[] args) {
        for (int num : getFibonacci()) {
            System.out.println(num);
        }
    }

    private static int[] getFibonacci() {
        return getFibonacci(new int[] {0, 1}, 50);
    }

    private static int[] getFibonacci(int[] nums, final int LIMIT) {
        final int N = nums.length;
        if (N >= LIMIT) {
            return nums;
        }
        int[] newNums = append(nums, nums[N - 2] + nums[N - 1]);
        return getFibonacci(newNums, LIMIT);
    }

    private static int[] append(int[] arr, int element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }
}
