package ch.nn.probability.distribution.discrete;

import ch.nn.binom.BinomCoeffs;

import java.math.BigDecimal;

public class BinomialDistribution implements FiniteDiscreteDistribution<Integer> {
    private final int n;
    private final double p;
    private final double q;
    private final BinomCoeffs coeffs;
    private final DistributionPoint<Integer>[] distribution;
    private final double[] simpleDistr;

    public BinomialDistribution(int n, double p) {
        this.n = n;
        this.p = p;
        this.q = 1 - p;
        coeffs = new BinomCoeffs(n);
        distribution = new DistributionPoint[n+1];
        simpleDistr = new double[n+1];
        BigDecimal[] pPow = new BigDecimal[n+1];
        BigDecimal[] qPow = new BigDecimal[n+1];
        pPow[0] = BigDecimal.ONE;
        qPow[0] = BigDecimal.ONE;
        for(int i=1; i<n+1; i++) {
            pPow[i] = pPow[i-1].multiply(BigDecimal.valueOf(p));
            qPow[i] = qPow[i-1].multiply(BigDecimal.valueOf(q));
        }

        for(int i=0; i<n+1; i++) {
            double probability = new BigDecimal(coeffs.cnk(i))
                    .multiply(pPow[i])
                    .multiply(qPow[n - i]).doubleValue();
            distribution[i] = new DistributionPoint<>(i, probability);
            simpleDistr[i] = probability;
        }
    }

    @Override
    public double probability(Integer i) {
        return simpleDistr[i];
    }

    @Override
    public double mean() {
        return n * p;
    }

    @Override
    public double variance() {
        return n * p * q;
    }

    @Override
    public DistributionPoint<Integer>[] finiteDistribution() {
        return distribution;
    }

    public double[] simpleDistribution() {
        return simpleDistr;
    }
}
