package ru.test.func.numeric;

import java.util.function.Function;

//todo add processing of singularity at the boundary
public interface NumCalculator {
    double integrate(Function<Double, Double> f, double a, double b);
    default Function<Double, Double> add(Function<Double, Double> f1, Function<Double, Double> f2) {
        return x -> f1.apply(x) + f2.apply(x);
    }
    default Function<Double, Double> subtract(Function<Double, Double> f1, Function<Double, Double> f2) {
        return x -> f1.apply(x) - f2.apply(x);
    }
    default Function<Double, Double> multiply(Function<Double, Double> f1,
                                              Function<Double, Double> f2) {
        return x -> f1.apply(x) * f2.apply(x);
    }
}
