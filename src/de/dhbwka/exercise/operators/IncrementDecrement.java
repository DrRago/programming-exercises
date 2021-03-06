package de.dhbwka.exercise.operators;

/**
 * @author Leonhard Gahr
 */
public class IncrementDecrement {
    public static void main(String[] args) {
        int i = 0;
        int j = ++i;
        int k = j++ + ++i;

        System.out.println("k: " + k);
        System.out.println("*: " + j++ + ++i);
        System.out.println(j++ + ++i);

        int m = j++ * ++i;

        System.out.println("m: " + m);

        int n = --j * --i;

        System.out.println("n: " + n);
        System.out.println("i: " + i);
        System.out.println("j: " + j);
    }
}

