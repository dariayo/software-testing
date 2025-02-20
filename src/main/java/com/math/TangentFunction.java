package com.math;

public class TangentFunction {
    private TangentFunction() {
    }

    public static double tan(double x, int count) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            throw new IllegalArgumentException("Input must be a finite number");
        }
        if (count <= 0) {
            throw new IllegalArgumentException("Terms must be a positive integer");
        }
        if (Math.abs(x) > Math.PI / 2) {
            throw new IllegalArgumentException("x must be smaller then PI/2");
        }
        double result = 0;
        for (int n = 1; n <= count; n++) {
            double bernoulli = Bernoulli.getBernoulli(2 * n);
            double numerator = Math.pow(-1, n - 1) * Math.pow(2, 2 * n) * (Math.pow(2, 2 * n) - 1) * bernoulli;
            double denominator = factorial(2 * n);
            result += numerator / denominator * Math.pow(x, 2 * n - 1);
        }
        return result;
    }

    public static long factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
