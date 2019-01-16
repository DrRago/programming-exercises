package de.dhbwka.exercise.datatypes;

/**
 * @author Leonhard Gahr
 */
public class Round {
    private static final int FOUND = 1;
    private static final int NOTFOUND = 0;
    private static char[] chars;

    public static void main(String[] args) {
        double pi = -3.1515926;
        double e = -2.7182818;
        int piInt = retardedRound(pi);
        int eInt = retardedRound(e);

        System.out.println(piInt);
        System.out.println(eInt);
    }

    private static int retardedRound(double value) {
        Round.chars = Double.toString(value).toCharArray();

        return (int) value + retardedRound(Round.chars.clone(), NOTFOUND);
    }

    private static int retardedRound(char[] chars, int state) {
        int n=chars.length-1;

        char[] temp=new char[n];
        System.arraycopy(chars,1,temp,0,n);
        char currentChar = chars[0];

        if (currentChar == 46 && state == NOTFOUND) {
            return retardedRound(temp, FOUND);
        } else if (state == FOUND && Character.getNumericValue(currentChar) >= 5) {
            return Round.chars[0] == 45 ? -1 : 1;
        } else if (state == FOUND  && Character.getNumericValue(currentChar) < 5) {
            return 0;
        }
        return retardedRound(temp, NOTFOUND);
    }

    private static int round(double value) {
        String doubleString = Double.toString(value);

        // the offset after casting to an integer
        int offset = 0;

        if (Character.getNumericValue(doubleString.charAt(doubleString.indexOf('.') + 1)) >= 5) {
            // offset can't stay 0 here
            // if the value is negative, subtract 1, else add
            offset = value < 0 ? -1 : 1;
        }
        return (int) value + offset;
    }
}