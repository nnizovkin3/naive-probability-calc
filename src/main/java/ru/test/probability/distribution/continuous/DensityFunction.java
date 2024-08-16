package ru.test.probability.distribution.continuous;

import ru.test.func.Func;

public interface DensityFunction extends Func {
    double mean();
    double variance();
}
