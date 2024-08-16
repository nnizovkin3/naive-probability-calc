package ch.nn.probability.distribution.discrete;

import ch.nn.probability.distribution.Distribution;

import java.util.stream.Stream;

public interface DiscreteDistribution<Variable> extends Distribution<Variable> {
    Stream<DistributionPoint<Variable>> distribution();
}
