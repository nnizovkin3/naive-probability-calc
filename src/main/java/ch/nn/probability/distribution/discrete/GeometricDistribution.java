package ch.nn.probability.distribution.discrete;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GeometricDistribution implements DiscreteDistribution<Integer> {
    private final double p;

    public GeometricDistribution(double p) {
        this.p = p;
    }

    @Override
    public double probability(Integer k) {
        return BigDecimal.valueOf(1-p).pow(k-1).multiply(BigDecimal.valueOf(p)).doubleValue();
    }

    @Override
    public double mean() {
        return 1/p;
    }

    @Override
    public double variance() {
        return BigDecimal.valueOf(1-p)
                .divide(BigDecimal.valueOf(p).pow(2), 16, RoundingMode.HALF_DOWN)
                .doubleValue();
    }

    @Override
    public Integer mod() {
        return 1;
    }

    @Override
    public Stream<DistributionPoint<Integer>> distribution() {
        var ref = new Object() {
            BigDecimal mult = BigDecimal.ONE;
        };
        BigDecimal q = BigDecimal.valueOf(1-p);
        BigDecimal pbd = BigDecimal.valueOf(p);
        return IntStream.iterate(1, i -> i + 1).boxed().map(i -> {
            var res = new DistributionPoint<>(i, ref.mult.multiply(pbd).doubleValue());
            ref.mult = ref.mult.multiply(q);
            return res;
        });
    }
}
