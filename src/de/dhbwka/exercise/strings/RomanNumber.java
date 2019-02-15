package de.dhbwka.exercise.strings;

import java.util.HashMap;

/**
 * @author Leonhard Gahr
 */
public class RomanNumber {
    static HashMap<Character, Integer> ref = new HashMap<>();

    static {
        ref.put('I', 1);
        ref.put('V', 5);
        ref.put('X', 10);
        ref.put('L', 50);
        ref.put('C', 100);
        ref.put('D', 500);
        ref.put('M', 1000);
    }

    public static int convertRoman(String roman) {
        int number = 0;
        int prev = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int temp = ref.get(roman.charAt(i));

            if (temp < prev) {
                number -= temp;
            } else {
                number += temp;
            }
            prev = temp;
        }

        return number;
    }

    public static void main(String[] args) {
        System.out.println(convertRoman("MCMLXXXIV"));
    }
}
