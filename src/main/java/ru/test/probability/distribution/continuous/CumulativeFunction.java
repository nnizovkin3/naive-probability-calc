package ru.test.probability.distribution.continuous;

import ru.test.func.Func;

public interface CumulativeFunction extends Func {
    default double calc(double a, double b) {
        return calc(b) - calc(a);
    }
}
