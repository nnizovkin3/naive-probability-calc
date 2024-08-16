package ch.nn.probability.distribution.discrete;

import java.util.Arrays;

public class DiscreteProbabilityUtilsExample {
    public static void main(String[] args) {
//        0 1 2 3 4 5
//        0,1 0,2 0,4 0,1 0,1 0,1
//        1 2 3 4 5 6 7
//        0,05 0,15 0,3 0,2 0,1 0,04 0,16
//        double[][] var = {{0, 0.1}, {1, 0.2}, {2, 0.4}, {3, 0.1}, {4, 0.1}, {5, 0.1}};
//        System.out.println(exp(var));
//        System.out.println(var(var));
//
//        double[][] var2 = {{1, 0.05}, {2, 0.15}, {3, 0.3}, {4, 0.2}, {5, 0.1}, {6, 0.04}, {7, 0.16}};
//        System.out.println(exp(var2));
//        System.out.println(var(var2));
//        System.out.println(standardDev(var2));
//        System.out.println(varCoef(var2));
//        System.out.println(skewness(var2));
//        System.out.println(kurtosis(var2));
//        System.out.println(mod(var2));
//        System.out.println(median(var2));

//        10 20 30 40 50
//        0,1 0,2 0,1 0,2 0,4
//        double[][] var3 = {{10, 0.1}, {20, 0.2}, {30, 0.1}, {40, 0.2}, {50, 0.4}};
//        System.out.println(expVal(var3));
//        System.out.println(var(var3));

//
//        System.out.println(exp(new double[][] {{0, 0.7}, {1, 0.3 * 7 / 9}, {2, 0.3 * 2 / 9 * 7 / 8}, {3, 0.3 * 2 / 9 / 8}}));
//        double var = var(new double[][]{{0, 0.7}, {1, 0.3 * 7 / 9}, {2, 0.3 * 2 / 9 * 7 / 8}, {3, 0.3 * 2 / 9 / 8}});
//        System.out.println(var);
//        System.out.println(Math.sqrt(var));
//        System.out.println(77.0/192);
//
//        double[] genF = genF(new double[]{0.2, 0.4, 0.6, 0.7});
//        System.out.println(Arrays.toString(genF));
//        double[][] vars = varForGenF(genF);
//        System.out.println(Arrays.deepToString(vars));
//        System.out.println(6 * 0.18 * 0.28 * 0.54 + Math.pow(0.54, 3));

//        X 0 0,3 0,6 0,9 1,2
//        P 0,2 0,4 0,2 0,1 0,1
        double[][] var4 = {{0, 0.2}, {0.3, 0.4}, {0.6, 0.2}, {0.9, 0.1}, {1.2, 0.1}};
        for(int i = 1; i <= 4; i++) {
            System.out.println(DiscreteProbabilityUtils.nRawMoment(var4, i));
            System.out.println(DiscreteProbabilityUtils.nCentralMoment(var4, i));
        }
        double[] varX = {0, 1, 2};
        double[] varY = {6, 7, 14, 8, 15, 22};
        double[][] prob = {
                {1.0 / 11, 8.0 / 33, 0, 1.0 / 11, 0, 0},
                {0, 0, 8.0 / 33, 0, 8.0 / 33, 0},
                {0, 0, 0, 0, 0, 1.0 / 11}
        };
        System.out.println(DiscreteProbabilityUtils.сovariance(varX, varY, prob));
        System.out.println(DiscreteProbabilityUtils.correlation(varX, varY, prob));

        double[] varX2 = {0, 1};
        double[][] prob2 = {
                {0.8, 0.05},
                {0.1, 0.05}
        };

        System.out.println(DiscreteProbabilityUtils.сovariance(varX2, varX2, prob2));
        System.out.println(DiscreteProbabilityUtils.correlation(varX2, varX2, prob2));

        double[][] prob3 = {
                {0, 0.1, 0.1},
                {0.1, 0.4, 0.1},
                {0.1, 0.1, 0}
        };
        System.out.println(DiscreteProbabilityUtils.сovariance(varX, varX, prob3));
        System.out.println(DiscreteProbabilityUtils.correlation(varX, varX, prob3));

        double[][] prob4 = {
                {0.01, 0.01, 0.02, 0.02, 0.01},
                {0.04, 0.3, 0.06, 0.03, 0.01},
                {0.02, 0.03, 0.06, 0.07, 0.05},
                {0.05, 0.03, 0.04, 0.02, 0.03},
                {0.03, 0.02, 0.01, 0.01, 0.02}
        };
        double[] varX4 = {3, 4, 5, 9, 10};
        double[] varY4 = {20, 30, 40, 50, 70};
        System.out.println(Arrays.toString(DiscreteProbabilityUtils.regression(varX4, varY4, prob4)));
        System.out.println(Arrays.toString(DiscreteProbabilityUtils.regression(varY4, varX4, DiscreteProbabilityUtils.transpose(prob4))));
        System.out.println(DiscreteProbabilityUtils.сovariance(varX4, varY4, prob4));
        System.out.println(DiscreteProbabilityUtils.correlation(varX4, varY4, prob4));

        double[][] prob5 = {
                {0, 0.1, 0.15},
                {0.05, 0.3, 0.05},
                {0.15, 0.05, 0.15}
        };

        System.out.println(Arrays.toString(DiscreteProbabilityUtils.buildHorizontalProbability(prob5)));
        System.out.println(Arrays.toString(DiscreteProbabilityUtils.buildVerticalProbability(prob5)));

        System.out.println(Arrays.deepToString(DiscreteProbabilityUtils.buildVariable(new double[]{-3, 0, 2}, DiscreteProbabilityUtils.conditionalProbability(prob5)[1])));
        System.out.println(DiscreteProbabilityUtils.mean(DiscreteProbabilityUtils.buildVariable(new double[]{-1, 1, 2}, DiscreteProbabilityUtils.conditionalProbability(DiscreteProbabilityUtils.transpose(prob5))[2])));
    }
}
