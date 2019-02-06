package de.dhbwka.exercise.classes;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Leonhard Gahr
 */

public class PointTest {
    private double[][] points = {{1, 3}, {3, 9}, {4, 2}, {-12, 22}, {123, 43}, {0, 0}, {12, 0}, {0, 21}};
    private double[][] pointsMirrorX = {{-1, 3}, {-3, 9}, {-4, 2}, {12, 22}, {-123, 43}, {0, 0}, {-12, 0}, {0, 21}};
    private double[][] pointsMirrorY = {{1, -3}, {3, -9}, {4, -2}, {-12, -22}, {123, -43}, {0, 0}, {12, 0}, {0, -21}};
    private double[][] pointsMirrorOrigin = {{-1, -3}, {-3, -9}, {-4, -2}, {12, -22}, {-123, -43}, {0, 0}, {-12, 0}, {0, -21}};

    private double getDistance(double[] p1, double[] p2) {
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }

    @Test
    void testNullConstructor() {
        Point origin = new Point();
        assertEquals(0, origin.getX());
        assertEquals(0, origin.getY());
    }

    @Test
    void testArrayConstructor() {
        for (double[] point : points) {
            Point arrPoint = new Point(point);

            assertEquals(point[0], arrPoint.getX());
            assertEquals(point[1], arrPoint.getY());
        }
    }

    @Test
    void testValueConstructor() {
        for (double[] point : points) {
            Point arrPoint = new Point(point[0], point[1]);

            assertEquals(point[0], arrPoint.getX());
            assertEquals(point[1], arrPoint.getY());
        }
    }

    @Test
    void testEquals() {
        Point point1 = new Point();
        Point point2 = new Point();
        assertEquals(point1, point2);

        point1 = new Point(1, 3);
        point2 = new Point(1, 3);
        assertEquals(point1, point2);

        point1 = new Point(1, 3);
        point2 = new Point(3, 1);
        assertNotEquals(point1, point2);

        point1 = new Point(3, 3);
        point2 = new Point(3, 1);
        assertNotEquals(point1, point2);

        point1 = new Point(3, 1);
        point2 = new Point(-3, 1);
        assertNotEquals(point1, point2);

        point1 = new Point(3, -1);
        point2 = new Point(3, 1);
        assertNotEquals(point1, point2);
    }

    @Test
    void testMirrors() {
        for (int i = 0; i < points.length; i++) {
            Point currPoint = new Point(points[i]);
            assertEquals(new Point(pointsMirrorX[i]), currPoint.mirrorXAxis());
            assertEquals(new Point(pointsMirrorY[i]), currPoint.mirrorYAxis());
            assertEquals(new Point(pointsMirrorOrigin[i]), currPoint.mirrorOrigin());
        }
    }

    @Test
    void testDistances() {
        for (int i = 0; i < points.length / 2; i++) {
            double distance = getDistance(points[i], points[points.length - i - 1]);
            Point point1 = new Point(points[i]);
            Point point2 = new Point(points[points.length - i - 1]);

            assertEquals(distance, point1.distToPoint(point2));
            assertEquals(distance, point2.distToPoint(point1));
        }

        for (double[] point : points) {
            double distance = getDistance(point, new double[]{0, 0});

            assertEquals(distance, new Point(point).distToOrigin());
        }
    }

    @Test
    void testToString() {
        for (double[] point : points) {
            String expect = String.format("(%.3f / %.3f)", point[0], point[1]);
            assertEquals(expect, new Point(point).toString());
        }
    }

    @Test
    void randomTests() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 1000; i++) {
            double[] point1Arr = new double[]{random.nextDouble(), random.nextDouble()};
            double[] point2Arr = new double[]{random.nextDouble(), random.nextDouble()};

            Point point1 = new Point(point1Arr);
            Point point2 = new Point(point2Arr);

            // mirrors
            assertEquals(new Point(new double[]{-point1Arr[0], point1Arr[1]}), point1.mirrorXAxis());
            assertEquals(new Point(new double[]{point1Arr[0], -point1Arr[1]}), point1.mirrorYAxis());
            assertEquals(new Point(new double[]{-point1Arr[0], -point1Arr[1]}), point1.mirrorOrigin());

            // distances
            assertEquals(getDistance(point1Arr, point2Arr), point1.distToPoint(point2));
            assertEquals(getDistance(point1Arr, point2Arr), point2.distToPoint(point1));

            assertEquals(getDistance(point1Arr, new double[]{0, 0}), point1.distToOrigin());
            assertEquals(getDistance(point2Arr, new double[]{0, 0}), point2.distToOrigin());

            String expect = String.format("(%.3f / %.3f)", point1Arr[0], point1Arr[1]);
            assertEquals(expect, point1.toString());
        }
    }
}
