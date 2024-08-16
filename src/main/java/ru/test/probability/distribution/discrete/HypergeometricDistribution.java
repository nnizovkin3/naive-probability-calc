package ru.test.probability.distribution.discrete;

import ru.test.binom.Binom;
import ru.test.binom.BinomCoeffs;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class HypergeometricDistribution implements FiniteDiscreteDistribution<Integer> {
    private final int N;
    private final int n;
    private final int K;
    private final BigInteger coeffN;
    private final BinomCoeffs coeffsK;
    private final BinomCoeffs coeffsNK;
    private final DistributionPoint<Integer>[] distribution;
    private final double[] simpleDistr;

    public HypergeometricDistribution(int N, int K, int n) {
        this.N = N;
        this.n = n;
        this.K = K;
        coeffN = Binom.calcCoeff(N, n);
        coeffsK = new BinomCoeffs(K);
        coeffsNK = new BinomCoeffs(N-K);
        int bound = Math.min(K, n);
        distribution = new DistributionPoint[bound+1];
        simpleDistr = new double[bound+1];
        for(int i=0; i<=bound; i++) {
            double probability = new BigDecimal(coeffsK.cnk(i).multiply(coeffsNK.cnk(n-i)))
                    .divide(new BigDecimal(coeffN), 10, RoundingMode.HALF_DOWN)
                    .doubleValue();
            distribution[i] = new DistributionPoint<>(i, probability);
            simpleDistr[i] = probability;
        }
    }

    @Override
    public double probability(Integer k) {
        return simpleDistr[k];
    }

    @Override
    public double mean() {
        return (double)K*n/N;
    }

    @Override
    public double variance() {
        return (double)K*n/N*(N-n)/N*(N-K)/(N-1);
    }

    @Override
    public DistributionPoint<Integer>[] finiteDistribution() {

        return distribution;
    }

    public double[] simpleDistribution() {
        return simpleDistr;
    }
}
