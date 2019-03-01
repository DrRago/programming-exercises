package de.dhbwka.exercise.methods;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Leonhard Gahr
 */
public class Exponentiation {
    static double xPowerN(double x, int n) {
        return n-- <= 0 ? 1 : x * xPowerN(x, n);
    }
}

class ExponentiationTest {
    @Test
    void testZeros() {
        assertEquals(Math.pow(0, 0), Exponentiation.xPowerN(0, 0));
        assertEquals(Math.pow(1, 0), Exponentiation.xPowerN(1, 0));
        assertEquals(Math.pow(0, 1), Exponentiation.xPowerN(0, 1));
    }

    @Test
    void testStatic() {
        assertEquals(Math.pow(5, 3), Exponentiation.xPowerN(5, 3));
        assertEquals(Math.pow(8, 1), Exponentiation.xPowerN(8, 1));
        assertEquals(Math.pow(2.1, 5), Exponentiation.xPowerN(2.1, 5));
        assertEquals(Math.pow(1282.223, 3), Exponentiation.xPowerN(1282.223, 3));
    }

    @Test
    void testRandom() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        IntStream.range(0, 1000).forEach(value -> {
            double x = random.nextDouble();
            int n = random.nextInt(0, 8000);
            assertEquals(Math.pow(x, n), Exponentiation.xPowerN(x, n), 0.00001);
        });
    }
}