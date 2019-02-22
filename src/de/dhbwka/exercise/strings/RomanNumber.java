package de.dhbwka.exercise.strings;

/**
 * @author Leonhard Gahr
 */
public class RomanNumber {
    /**
     * get the numeric value for a single roman number
     *
     * @param LETTER the roman LETTER
     * @return the integer value
     */
    private static int decode(final int LETTER) {
        switch (LETTER) {
            case 'M':
                return 1000;
            case 'D':
                return 500;
            case 'C':
                return 100;
            case 'L':
                return 50;
            case 'X':
                return 10;
            case 'V':
                return 5;
            case 'I':
                return 1;
            default:
                return 0;
        }
    }

    /**
     * Convert roman number string to an integer
     *
     * @param ROMAN the roman number
     * @return the calculated integer
     */
    private static int convertRoman(final String ROMAN) {
        final int[] prev = {0};

        // reverse string, iterate over the chars, map to the actual integer values and return the sum
        return new StringBuilder(ROMAN).reverse().chars().map(RomanNumber::decode).map(n -> {
            int tmp = n < prev[0] ? -n : n;
            prev[0] = n;
            return tmp;
        }).sum();
    }

    public static void main(String[] args) {
        System.out.println(convertRoman("MCMLXXXIV"));
    }
}
