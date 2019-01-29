package de.dhbwka.exercise.arrays;

import java.util.ArrayList;
import java.util.List;

import static de.dhbwka.exercise.utility.ScannerUtility.getInt;

/**
 * @author Leonhard Gahr
 */
public class Eratostenes {
    public static void main(String[] args) {
        List<Integer> sieve = getNaturalNums(getInt("Enter N: "));
        List<Integer> primes = new ArrayList<>();
        long start = System.currentTimeMillis();

        while (!sieve.isEmpty()) {
            int curr = sieve.get(0);
            primes.add(curr);
            sieve.removeIf(element -> element % curr == 0);
        }
        System.out.printf("%ds", System.currentTimeMillis() - start);

        System.out.println(primes);

    }

    private static List<Integer> getNaturalNums(final int N) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            nums.add(i);
        }
        return nums;
    }
}
