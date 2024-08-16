package ru.test.probability.distribution.discrete;

import ru.test.probability.distribution.Distribution;

import java.util.stream.Stream;

public interface DiscreteDistribution<Variable> extends Distribution<Variable> {
    Stream<DistributionPoint<Variable>> distribution();
}
