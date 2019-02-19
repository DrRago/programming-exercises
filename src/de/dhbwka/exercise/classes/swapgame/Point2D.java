package de.dhbwka.exercise.classes.swapgame;

import lombok.Getter;

/**
 * a 2-Dimensional point with some functions
 *
 * @author Leonhard Gahr
 */
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