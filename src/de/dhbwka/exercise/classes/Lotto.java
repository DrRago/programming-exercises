package de.dhbwka.exercise.classes;

import de.dhbwka.exercise.utility.ScannerUtility;
import de.dhbwka.exercise.utility.Sort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author Leonhard Gahr
 */
public class Lotto {
    private final int M;
    private final int N;

    private int[] tip;
    private int[] drawn;

    public Lotto(int m, int n) {
        M = m;
        N = n;
    }

    public void tip() {
        tip = new int[M];
        // anonymous object for lambda modification
        var reference = new Object() {
            int[] allowed = IntStream.range(M, N + 1).toArray();
        };
        IntStream.range(0, M).forEach(i -> {
            tip[i] = ScannerUtility.getInt(String.format("Tip #%d: ", i + 1), reference.allowed);
            reference.allowed = Arrays.stream(reference.allowed).filter(x -> x != tip[i]).toArray();
        });

        Sort.bogoSort(tip);
    }

    private void tip(int[] tip) {
        Sort.bogoSort(tip);
        this.tip = tip;
    }

    /**
     * Draw random numbers with no duplicates at the size of this.M
     */
    public void draw() {
        drawn = new Random().ints(M, N + 1).distinct().limit(M).toArray();
        Sort.bogoSort(drawn);
    }

    /**
     * Determine the amount correctly guessed numbers in n log n time
     *
     * @return the number of corrects (max this.M)
     */
    public int rights() {
        int rights = 0;
        int i = 0;
        for (int t : tip) {
            for (; i < drawn.length; i++) {
                if (t == drawn[i]) {
                    rights++;
                } else if (t < drawn[i]) {
                    break;
                }
            }
        }
        return rights;
    }

    @Override
    public String toString() {
        return tip == null ? "" : ("Tip: " + String.join(" ", Arrays.stream(tip).mapToObj(String::valueOf).toArray(String[]::new))) +
                (drawn == null ? "" : ("\nDrawn: " + String.join(" ", Arrays.stream(drawn).mapToObj(String::valueOf).toArray(String[]::new))));
    }

    public static void main(String[] args) {
        Lotto lotto = new Lotto(6, 49);
        lotto.tip();
        System.out.println(lotto);
        lotto.draw();
        System.out.println(lotto);
        System.out.println(lotto.rights());
    }
}
