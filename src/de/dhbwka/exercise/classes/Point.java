package de.dhbwka.exercise.classes;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Leonhard Gahr
 */
@Getter
@Setter
public class Point {
    private double x;
    private double y;

    public Point() {
        this(0, 0);
    }

    public Point(double[] points) {
        this(points[0], points[1]);
    }

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double distToPoint(Point point) {
        return Math.sqrt(Math.pow(point.x - x, 2) + Math.pow(point.y - y, 2));
    }

    double distToOrigin() {
        return distToPoint(new Point());
    }

    Point mirrorXAxis() {
        return new Point(-this.x, this.y);
    }

    Point mirrorYAxis() {
        return new Point(this.x, -this.y);
    }

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
}
