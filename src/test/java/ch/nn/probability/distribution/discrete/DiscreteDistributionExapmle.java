package ch.nn.probability.distribution.discrete;

import java.util.Arrays;

public class DiscreteDistributionExapmle {
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new BinomialDistribution(4, 0.2).simpleDistribution()));
//        BinomialDistribution distribution = new BinomialDistribution(3, 0.25);
//        System.out.println(Arrays.toString(distribution.simpleDistribution()));
//        System.out.println(distribution.expectedValue());
//        System.out.println(distribution.variance());
//        System.out.println(Arrays.toString(new BinomialDistribution(8, 0.35).simpleDistribution()));
//        BinomialDistribution binomialDistribution2 = new BinomialDistribution(3, 0.2);
//        System.out.println(Arrays.toString(binomialDistribution2.simpleDistribution()));
//        System.out.println(binomialDistribution2.expectedValue());
//        System.out.println(binomialDistribution2.variance());
//        System.out.println(Arrays.toString(new BinomialDistribution(3, 3/8.0).simpleDistribution()));
//        BinomialDistribution binomialDistribution3 = new BinomialDistribution(20, 0.7);
//        System.out.println(Arrays.toString(binomialDistribution3.simpleDistribution()));
//        System.out.println(binomialDistribution3.expectedValue());
//        System.out.println(binomialDistribution3.variance());
//        System.out.println(Arrays.toString(new BinomialDistribution(4, 0.5154).simpleDistribution()));
//        System.out.println(Arrays.toString(new BinomialDistribution(3, 0.6).simpleDistribution()));
        System.out.println(Arrays.toString(new HypergeometricDistribution(15, 7, 3).simpleDistribution()));
        HypergeometricDistribution hypergeometricDistribution = new HypergeometricDistribution(36, 9, 4);
        System.out.println(Arrays.toString(hypergeometricDistribution.simpleDistribution()));
        System.out.println(hypergeometricDistribution.mean());
        System.out.println(hypergeometricDistribution.variance());
        System.out.println(Arrays.toString(new HypergeometricDistribution(9, 6, 4).simpleDistribution()));
        HypergeometricDistribution hypergeometricDistribution2 = new HypergeometricDistribution(10, 4, 4);
        System.out.println(Arrays.toString(hypergeometricDistribution2.simpleDistribution()));
        System.out.println(hypergeometricDistribution2.mod());
        System.out.println(hypergeometricDistribution2.mean());
        GeometricDistribution geometricDistribution = new GeometricDistribution(0.44);
        System.out.println(geometricDistribution.distribution().map(DistributionPoint::probability)
                .limit(10).toList());
        System.out.println(geometricDistribution.mean());
        System.out.println(new GeometricDistribution(0.2).mean());
        System.out.println(new GeometricDistribution(0.2).variance());
        System.out.println(new GeometricDistribution(0.4).distribution().map(DistributionPoint::probability)
                .limit(10).toList());
        System.out.println(new PoissonDistribution(2.5).distribution().map(DistributionPoint::probability)
                .limit(10).toList());
        System.out.println(new PoissonDistribution(5).distribution().map(DistributionPoint::probability)
                .limit(10).toList());
        System.out.println(new PoissonDistribution(0.1).distribution().map(DistributionPoint::probability)
                .limit(10).toList());
    }
}
