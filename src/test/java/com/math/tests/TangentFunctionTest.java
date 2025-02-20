package com.math.tests;

import com.math.TangentFunction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TangentFunctionTest {
    @Test
    public void testZeroTan() {
        double result = TangentFunction.tan(0, 10);
        assertEquals(0.0, result, 1e-5);
    }

    @Test
    public void testSmallAngle() {
        double x = Math.PI / 6;
        double result = TangentFunction.tan(x, 10);
        double expected = Math.tan(x);
        assertEquals(expected, result, 0.01);
    }

    @Test
    public void testMediumAngle() {
        double x = Math.PI / 4;
        double result = TangentFunction.tan(x, 10);
        double expected = Math.tan(x);
        assertEquals(expected, result, 0.01);
    }

    @Test
    public void testBigAngle() {
        double x = Math.PI;
        assertThrows(IllegalArgumentException.class, () -> TangentFunction.tan(x, 10));
    }

    @Test
    public void testLargeAngle() {
        double x = Math.PI / 3;
        double result = TangentFunction.tan(x, 10);
        double expected = Math.tan(x);
        assertEquals(expected, result, 0.01);
    }

    @Test
    public void testNegativeAngle() {
        double x = -Math.PI / 3;
        double result = TangentFunction.tan(x, 10);
        double expected = Math.tan(x);
        assertEquals(expected, result, 0.01);
    }

    @ParameterizedTest(name = "tan({0}) should be {1}")
    @MethodSource("providedTableValues")
    public void checkValues(double x, double expected) {
        double result = TangentFunction.tan(x, 10);
        assertEquals(expected, result, 0.01, "tan(" + x + "should be " + expected);
    }

    private static Stream<Arguments> providedTableValues() {
        return Stream.of(
                Arguments.of(-Math.PI / 3, Math.tan(-Math.PI / 3)),
                Arguments.of(-Math.PI / 4, Math.tan(-Math.PI / 4)),
                Arguments.of(-Math.PI / 6, Math.tan(-Math.PI / 6)),
                Arguments.of(0.0, Math.tan(0.0)),
                Arguments.of(Math.PI / 6, Math.tan(Math.PI / 6)),
                Arguments.of(Math.PI / 4, Math.tan(Math.PI / 4)),
                Arguments.of(Math.PI / 3, Math.tan(Math.PI / 3))
        );
    }

    @ParameterizedTest(name = "tan({0}) should throw exception")
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    public void testInvalidInput(double x) {
        assertThrows(IllegalArgumentException.class, () -> {
            TangentFunction.tan(x, 10);
        }, "tan(" + x + ") should throw exception");
    }

    @ParameterizedTest(name = "tan of {0} should throw exception")
    @ValueSource(ints = {0, -1})
    public void testInvalidCounts(int count) {
        assertThrows(IllegalArgumentException.class, () -> {
            TangentFunction.tan(1, count);
        }, "tan of" + count + " should throw exception");
    }


}
