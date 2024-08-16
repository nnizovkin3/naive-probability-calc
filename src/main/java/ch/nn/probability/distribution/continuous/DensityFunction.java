package ch.nn.probability.distribution.continuous;

import ch.nn.func.Func;

public interface DensityFunction extends Func {
    double mean();
    double variance();
}
