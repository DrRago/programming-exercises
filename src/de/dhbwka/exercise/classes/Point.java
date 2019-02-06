package de.dhbwka.exercise.classes;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Point.
 *
 * @author Leonhard Gahr
 */
@Getter
@Setter
public class Point {
    private double x;
    private double y;

    /**
     * Instantiates a new Point at (0/0).
     */
    public Point() {
        this(0, 0);
    }

    /**
     * Instantiates a new Point.
     *
     * @param points the point coordinates
     */
    public Point(double[] points) {
        this(points[0], points[1]);
    }

    /**
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     */
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Dist to point double.
     * Calculate the distance to another point
     *
     * @param point the point to calculate the distance to
     * @return the distance
     */
    double distToPoint(Point point) {
        return Math.hypot(point.x - x, point.y - y);
    }

    /**
     * Dist to origin double.
     * Calculate the distance to the origin (0/0)
     *
     * @return the distance
     */
    double distToOrigin() {
        return distToPoint(new Point());
    }

    /**
     * Mirror point at x axis and create a new one
     *
     * @return the new point
     */
    Point mirrorXAxis() {
        return new Point(-this.x, this.y);
    }

    /**
     * Mirror point at y axis and create a new one
     *
     * @return the new point
     */
    Point mirrorYAxis() {
        return new Point(this.x, -this.y);
    }

    /**
     * Mirror point at origin and create a new one
     *
     * @return the new point
     */
    Point mirrorOrigin() {
        return new Point(-this.x, -this.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("(%.3f / %.3f)", x, y);
    }

    public static void main(String[] args) {
        Point pointA = new Point(4.0, 2.0);
        System.out.println("A: " + pointA);
        System.out.println("Betrag: " + pointA.distToOrigin());
        Point pointB = new Point(-1.0, -1.0);
        System.out.println("B: " + pointB);
        System.out.println("AbstandA-B:" + pointA.distToPoint(pointB));
        pointA = pointA.mirrorOrigin();
        System.out.println("A': " + pointA);
        System.out.println("AbstandA’-B: " + pointA.distToPoint(pointB));
    }
}