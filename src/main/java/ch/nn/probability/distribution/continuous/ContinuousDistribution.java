package ch.nn.probability.distribution.continuous;

import lombok.Getter;
import ch.nn.probability.distribution.Distribution;

@Getter
public class ContinuousDistribution implements Distribution<Double> {
    private final DensityFunction densityFunction;
    private final CumulativeFunction cumulativeFunction;
    public ContinuousDistribution(DensityFunction densityFunction, CumulativeFunction cumulativeFunction) {
        this.densityFunction = densityFunction;
        this.cumulativeFunction = cumulativeFunction;
    }

    @Override
    public double probability(Double x) {
        return cumulativeFunction.calc(x);
    }

    public double probability(double x) {
        return cumulativeFunction.calc(x);
    }

    public double probability(double a, double b) {
        if(a>b) {
            throw new IllegalArgumentException();
        }
        return cumulativeFunction.calc(a, b);
    }

    @Override
    public double mean() {
        return densityFunction.mean();
    }

    @Override
    public double variance() {
        return densityFunction.variance();
    }

    //todo
    @Override
    public Double mod() {
        throw new UnsupportedOperationException();
    }
}
