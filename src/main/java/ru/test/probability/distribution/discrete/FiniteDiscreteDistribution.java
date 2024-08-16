package ru.test.probability.distribution.discrete;

import java.util.Arrays;
import java.util.stream.Stream;

public interface FiniteDiscreteDistribution<Variable> extends DiscreteDistribution<Variable> {
    DistributionPoint<Variable>[] finiteDistribution();
    default Stream<DistributionPoint<Variable>> distribution() {
        return Arrays.stream(finiteDistribution());
    }
    default Variable mod() {
        Variable res = null;
        double max = Double.MIN_VALUE;

        for(DistributionPoint<Variable> p: finiteDistribution()) {
            if(p.probability() > max) {
                max = p.probability();
                res = p.variable();
            }
        }

        return res;

    }
}
