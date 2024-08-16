public class NormalDistributionExample {
    public static void main(String[] args) {
//        ContinuousDistributionFactory factory = new ContinuousDistributionFactory(new SymJaCalculator(),
//                new ALGLIBNumCalculator());
//        NormalDistribution distribution = new NormalDistribution(2, 0.005);
//        System.out.println(1 - distribution.probability(1.99, 2.01));
//        System.out.println(1 - new org.apache.commons.math3.distribution
//                .NormalDistribution(2, 0.005).probability(1.99, 2.01));
//        double sigma = 0.005;
//        Function<Double, Double> f = x -> 1 / (sigma * Math.sqrt(2 * Math.PI))
//                * Math.pow(Math.E, -(x - 2) * (x - 2) / (2 * sigma * sigma));
////        for(double i = 1.99; i < 2.01; i+=0.01) {
////            System.out.println(i + " " + f.apply(i));
////        }
//
//        ContinuousDistribution distribution3 = factory.createDistrForNum(f, "", -10, 10);
//        System.out.println(distribution3.probability(3));
//        System.out.println(distribution3.mean());
//        System.out.println(distribution3.standardDeviation());
//        System.out.println(2 * distribution3.probability(1.99));
//
//        System.out.println(new org.apache.commons.math3.distribution
//                .NormalDistribution(2.02, 0.005).probability(1.99, 2.01));
//
//        distribution = new NormalDistribution(161, 4);
//        System.out.println(distribution.probability(152, 158));
//        System.out.println(distribution.probability(161 - 4*3, 161 + 4*3));


//        double min = 2;
//        double minD = 0;
//        for (double d = 0.0364774099147061; d < 0.0364774099147062; d += 0.000000000000000005) {
//            double diff = Math.abs(new NormalDistribution(1.06, d).cumulativeProbability(1) - 0.05);
//            if(min > diff) {
//                min = diff;
//                minD = d;
//            }
//        }
//
//        double d = 0.03647740991470615;
//        System.out.println(1 - new NormalDistribution(1.06, d).cumulativeProbability(0.94));
//
//        UnivariateFunction f = x -> new NormalDistribution(0, 1).cumulativeProbability(x) - 0.15;
//        UnivariateFunction f2 = x -> new NormalDistribution(0, 1).cumulativeProbability(x) - 0.4;
//        final double relativeAccuracy = 1.0e-15;
//        final double absoluteAccuracy = 1.0e-12;
//        UnivariateSolver solver   = new RiddersSolver(relativeAccuracy, absoluteAccuracy);
//        double c = solver.solve(100, f, -10, 0.);
//        double c2 = solver.solve(100, f2, -10, 0.);
//        double sigma = 4.2/(Math.abs(c+c2));
//        double mean = 1.036 * sigma + 12;
//        System.out.println(new NormalDistribution(mean, sigma).cumulativeProbability(12));
//        System.out.println(mean + " " + sigma);

//        NormalDistribution distribution = new NormalDistribution(870, 90);
//        System.out.println(1 - distribution.cumulativeProbability(900));
//        System.out.println(distribution.probability(860, 940));
//        System.out.println(distribution.cumulativeProbability(750));
//
//        distribution = new NormalDistribution(-1, 2);
//        System.out.println(distribution.probability(-6, 1));
//        double p = distribution.probability(-1, 3);
//        System.out.println(10 * p * p * p * (1-p) * (1-p));
//        System.out.println(1 - new NormalDistribution(1.06, c).cumulativeProbability(0.94));
//        System.out.println(new NormalDistribution(1.06, c).cumulativeProbability(1));
//        System.out.println(Erf.erfInv(0.3));
//        System.out.println(Erf.erfInv(0.8));
//        NormalDistribution distribution = new NormalDistribution(9, 2);
//        System.out.println(1-distribution.cumulativeProbability(10));
//        System.out.println(distribution.probability(2, 5));
//        NormalDistribution distribution = new NormalDistribution(3, 1/Math.sqrt(2));
//        System.out.println(distribution.probability(3, 4));
//        System.out.println(distribution.probability(2.8, 3.2));
        System.out.println(-5/Math.log(0.4));
    }
}
