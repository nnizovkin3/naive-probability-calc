package ch.nn.probability.distribution.continuous;

import ch.nn.func.Func;

public interface CumulativeFunction extends Func {
    default double calc(double a, double b) {
        return calc(b) - calc(a);
    }
}
