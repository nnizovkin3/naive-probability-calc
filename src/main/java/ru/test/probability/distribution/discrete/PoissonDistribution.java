package ru.test.probability.distribution.discrete;

import ru.test.numeric.NumericUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PoissonDistribution implements DiscreteDistribution<Integer> {
    private final double lambda;

    public PoissonDistribution(double lambda) {
        if(lambda < 0) {
            throw new IllegalArgumentException();
        }
        this.lambda = lambda;
    }

    @Override
    public double probability(Integer k) {
        if(k < 0) {
            throw new IllegalArgumentException();
        }

        return BigDecimal.valueOf(lambda).pow(k)
                .divide(new BigDecimal(NumericUtils.fact(k)).multiply(BigDecimal.valueOf(Math.pow(Math.E, lambda))),
                15, RoundingMode.FLOOR).doubleValue();
    }

    @Override
    public double mean() {
        return lambda;
    }

    @Override
    public double variance() {
        return lambda;
    }

    @Override
    public Integer mod() {
        return (int)lambda;
    }

    @Override
    public Stream<DistributionPoint<Integer>> distribution() {
        var ref = new Object() {
            BigDecimal mult = BigDecimal.valueOf(1/Math.pow(Math.E, lambda));
        };
        BigDecimal l = BigDecimal.valueOf(lambda);
        return IntStream.iterate(0, i -> i + 1).boxed().map(i -> {
            var res = new DistributionPoint<>(i, ref.mult.doubleValue());
            ref.mult = ref.mult.multiply(l).multiply(BigDecimal.valueOf(1.0/(i+1)));
            return res;
        });
    }
}
