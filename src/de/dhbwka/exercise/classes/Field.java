package de.dhbwka.exercise.classes;

import lombok.Getter;

import java.util.Arrays;
import java.util.Random;
import java.util.StringJoiner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static de.dhbwka.exercise.utility.ConsoleColors.*;

/**
 * @author Leonhard Gahr
 * <p>
 * Rectangle field containing n-colors (0 to (n - 1))
 */
@SuppressWarnings("WeakerAccess")
public class Field {
    @Getter
    private int[][] field;

    private final int X;
    private final int Y;
    private final int COLORS;

    @Getter
    private int score = 0;

    /**
     * Initialize the field with default values (9x9 and 7 colors)
     */
    public Field() {
        this(9, 9, 7);
    }

    /**
     * Initialize a cuboid field with default color amount (7)
     *
     * @param n the dimension
     */
    public Field(int n) {
        this(n, n, 7);
    }

    /**
     * Initialize a cuboid field with n colors
     *
     * @param n      the dimension of the field
     * @param colors the amount of colors
     */
    public Field(int n, int colors) {
        this(n, n, colors);
    }

    /**
     * Initialize the field with the size of XxY and with n random colors
     *
     * @param x      the x-dimension of the field
     * @param y      the y-dimension of the field
     * @param colors the colors a field can have
     */
    public Field(int x, int y, int colors) {
        this.X = x;
        this.Y = y;
        this.COLORS = colors;
        field = new int[y][x];
        randomize();
    }

    /**
     * Set the field with random color values between 0 and this.COLORS
     * condition: the field may not contain a row of 3 or more same colors (horizontally or vertically)
     */
    private void randomize() {
        int[] randoms = new Random().ints(Y * X, 0, COLORS).toArray();
        for (int i = 0; i < randoms.length; i++) {
            field[i / Y][i % X] = randoms[i];
        }

        int[] found;
        // find possible lines of 3+ and replace the center point of that line by a random color until nothing is left
        while ((found = findScore()) != null) {
            field[average(found[2], found[3])][average(found[0], found[1])] = ThreadLocalRandom.current().nextInt(0, COLORS);
        }
    }

    /**
     * get the average of n input integers
     *
     * @param arr the integers
     * @return the average as floored integer
     */
    private int average(int... arr) {
        return (int) Arrays.stream(arr).summaryStatistics().getAverage();
    }

    /**
     * perform a swap of two points, if it is valid
     * <p>
     * a swap is possible, if the points are next to each other
     * and if, after the swap, a row of 3 or more same colors exist
     * <p>
     * The order of the points is irrelevant
     *
     * @param p1 the first points
     * @param p2 the second point
     * @return whether the move was possible or not
     */
    public boolean makeMove(Point2D p1, Point2D p2) {
        if (p1.equals(p2) | !p1.isNextTo(p2)) return false;

        swap(p1, p2);

        // check if the move was valid
        if (concludeMove() != 0) {
            return true;
        }

        // move is invalid
        swap(p1, p2);
        return false;
    }

    /**
     * Get te amount of same colors in a row (horizontally) from a start point (self included)
     *
     * @param x the x coordinate of the starting point
     * @param y the y coordinate of the starting point
     * @return the amount of repetitions (min 1)
     */
    private int rowNum(int x, int y) {
        return getRepetitions(x, y, field[y][x], 1, 0) + getRepetitions(x, y, field[y][x], -1, 0) - 1;
    }

    /**
     * Get te amount of same colors in a column (vertically) from a start point (self included)
     *
     * @param x the x coordinate of the starting point
     * @param y the y coordinate of the starting point
     * @return the amount of repetitions (min 1)
     */
    private int colNum(int x, int y) {
        return getRepetitions(x, y, field[y][x], 0, 1) + getRepetitions(x, y, field[y][x], 0, -1) - 1;
    }

    /**
     * Get the repetitions of a value at a given index of the field
     * <p>
     * xInc and yInc are the increments for the specific axis, so this method returns the number of repetitions in one direction (including self)
     * (can be diagonally too)
     * <p>
     * xInc and yInc should never be both equal to 0, the method would not terminate then
     *
     * @param x    the x coordinate of the current point
     * @param y    the y coordinate of the current point
     * @param prev the value of the previous point (should be the initial when method is called)
     * @param xInc the increment on x coordinate
     * @param yInc the increment of y coordinate
     * @return the number of repetitions including self
     */
    private int getRepetitions(int x, int y, int prev, int xInc, int yInc) {
        if (x == -1 || y == -1 || x == X || y == Y) return 0;
        if (field[y][x] != prev) return 0;
        return 1 + getRepetitions(x + xInc, y + yInc, field[y][x], xInc, yInc);
    }

    /**
     * swap two values of two points
     * <p>
     * the order of the points is irrelevant
     *
     * @param p1 the first point
     * @param p2 the second point
     */
    private void swap(Point2D p1, Point2D p2) {
        int temp = field[p1.getY()][p1.getX()];
        field[p1.getY()][p1.getX()] = field[p2.getY()][p2.getX()];
        field[p2.getY()][p2.getX()] = temp;
    }

    /**
     * Conclude a move:
     * <p>
     * check the board and score all possible points, fill up randomly until no more points are scorable
     *
     * @return the amount of lines scored
     */
    private int concludeMove() {
        int count = 0;
        int[] scoreLine;
        while ((scoreLine = this.findScore()) != null) {
            this.score += getScoredVal(scoreLine);
            clearField(scoreLine);
            gravity();
            fillUp();
            count++;
        }
        return count;
    }

    /**
     * Find a line on the field (horizontally or vertically) that would be scorable (3+ same colors)
     *
     * @return the startX, endX, startY, endY as an array (always length of 4) or null (always just one result, not all)
     */
    private int[] findScore() {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                boolean row = false;
                if (rowNum(x, y) >= 3) {
                    row = true;
                } else if (colNum(x, y) < 3) {
                    continue;
                }
                int startX = x - (row ? getRepetitions(x, y, field[y][x], -1, 0) - 1 : 0);
                int endX = x + (row ? getRepetitions(x, y, field[y][x], 1, 0) - 1 : 0);
                int startY = y - (row ? 0 : getRepetitions(x, y, field[y][x], 0, -1) - 1);
                int endY = y + (row ? 0 : getRepetitions(x, y, field[y][x], 0, 1) - 1);

                return new int[]{startX, endX, startY, endY};
            }
        }
        return null;
    }

    /**
     * fill the field with random values where it has -1
     */
    private void fillUp() {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == -1) {
                    field[y][x] = ThreadLocalRandom.current().nextInt(0, COLORS);
                }
            }
        }
    }

    /**
     * consider "-1" values as empty spaces, so move everything above it to the bottom
     */
    private void gravity() {
        // iterate the field backwards
        // ignore the first row, it is impossible to find any colored block above
        for (int y = field.length - 1; y >= 1; y--) {
            for (int x = field[y].length - 1; x >= 0; x--) {
                if (field[y][x] == -1) {
                    Point2D curr = new Point2D(x, y);
                    // find the next color field above that isn't -1 (can't just swap with the point above, because it may be a "-1" too)
                    int newY = y - getRepetitions(x, y, -1, 0, -1);

                    if (newY < 0) {
                        // reached the top of a vertical row if this happens
                        return;
                    }
                    Point2D above = new Point2D(x, newY);

                    // swap the above with the current to move the "-1" further to the top
                    swap(curr, above);
                }
            }
        }
    }

    /**
     * Clear the field in a given rectangle
     *
     * @param startX the x coordinate start
     * @param endX   the x coordinate end (inclusive)
     * @param startY the y coordinate start
     * @param endY   the y coordinate end (inclusive)
     */
    private void clearField(int startX, int endX, int startY, int endY) {
        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                field[y][x] = -1;
            }
        }
    }

    /**
     * Clear the field in a given rectangle
     *
     * @param coordinates a 4-long array that should build a rectangle
     */
    private void clearField(int[] coordinates) {
        clearField(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
    }

    /**
     * calculate the score based on the values given
     * <p>
     * if 3 fields are the same -> 30
     * <p>
     * if 4 -> 40
     * <p>
     * if 5 -> 50
     * <p>
     * more than 5 is not possible
     *
     * @param startX the start x coordinate of the row/col
     * @param endX   the end x coordinate of the row/col
     * @param startY the start y coordinate of the row/col
     * @param endY   the end y coordinate of the row/col
     * @return the actual score
     */
    private int getScoredVal(int startX, int endX, int startY, int endY) {
        return (startX == endX ? (endY - startY) + 1 : (endX - startX) + 1) * 10;
    }

    /**
     * calculate the score based on the values given
     * <p>
     * if 3 fields are the same -> 30
     * <p>
     * if 4 -> 40
     * <p>
     * if 5 -> 50
     * <p>
     * more than 5 is not possible
     *
     * @param coordinates a 4-long array that should build a line (either horizontally or vertically)
     * @return the actual score
     */
    private int getScoredVal(int[] coordinates) {
        return getScoredVal(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
    }

    /**
     * Custom toString to pretty-print the field
     *
     * @return the field's numeric color values
     */
    @Override
    public String toString() {
        var counter = new Object() {
            int count = 0;
        };
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("  " + IntStream.range(0, X).mapToObj(i -> " " + i).collect(Collectors.joining(" ")));
        Arrays.stream(field).forEach(x -> joiner.add(counter.count++ + " " + Arrays.stream(x).mapToObj(ColorHelper::getColorString).collect(Collectors.joining(""))));
        return joiner.toString();
    }
}

@Getter
class Point2D {
    private int x;
    private int y;

    /**
     * Initialize the point
     *
     * @param x the x
     * @param y the y
     */
    Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Initialize the point
     *
     * @param p the point
     */
    Point2D(int[] p) {
        this(p[0], p[1]);
    }

    /**
     * check if the point is a neighbour of this
     *
     * @param p the point
     * @return true or false
     */
    public boolean isNextTo(Point2D p) {
        return Math.abs(x - p.x) == 1 ^ Math.abs(y - p.y) == 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point2D)) return false;
        Point2D p = (Point2D) obj;
        return x == p.x && y == p.y;
    }
}

class ColorHelper {
    /**
     * get a string as specific color string based on an integer from 0 to n (14+ will return always white)
     *
     * @param i the color indicator
     * @return the color code string
     */
    private static String getColor(int i) {
        switch (i) {
            case 0:
                return BLACK_BACKGROUND_BRIGHT;
            case 1:
                return RED_BACKGROUND_BRIGHT;
            case 2:
                return GREEN_BACKGROUND_BRIGHT;
            case 3:
                return YELLOW_BACKGROUND_BRIGHT;
            case 4:
                return BLUE_BACKGROUND_BRIGHT;
            case 5:
                return PURPLE_BACKGROUND_BRIGHT;
            case 6:
                return CYAN_BACKGROUND_BRIGHT;
            case 7:
                return BLACK_BACKGROUND;
            case 8:
                return RED_BACKGROUND;
            case 9:
                return GREEN_BACKGROUND;
            case 10:
                return YELLOW_BACKGROUND;
            case 11:
                return BLUE_BACKGROUND;
            case 12:
                return PURPLE_BACKGROUND;
            case 13:
                return CYAN_BACKGROUND;
            default:
                return WHITE_BACKGROUND_BRIGHT;
        }
    }

    static String getColorString(int i) {
        return getColor(i) + String.format("%2d ", i) + RESET;
    }
}