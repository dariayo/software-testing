package com.math;

import static com.math.TangentFunction.factorial;

public class Bernoulli {
    private Bernoulli() {
    }

    public static double getBernoulli(int n) {
        if (n == 0) {
            return 1.0;
        }
        double result = 0;
        for (int k = 0; k < n; k++) {
            result += binomialCoefficient(n + 1, k) * getBernoulli(k);
        }
        return -result / (n + 1);
    }

    private static long binomialCoefficient(int n, int k) {
        if (k == 0) {
            return 1;
        }
        long numerator = factorial(n);
        long denominator = factorial(k) * factorial(n - k);

        return numerator / denominator;
    }
}
