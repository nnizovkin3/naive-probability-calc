package ru.test.probability.distribution.discrete;

import java.util.Arrays;

public class DiscreteProbabilityUtils {
    public static double[][] conditionalProbability(double[][] var) {
        if(var.length == 0) {
            return var;
        }
        buildHorizontalProbability(var);
        double[] varY = buildHorizontalProbability(var);
        double[][] res = new double[var.length][var[0].length];
        for(int i = 0; i < var.length; i++) {
            for(int j = 0; j < var.length; j++) {
                res[i][j] = var[i][j] / varY[i];
            }
        }

        return res;
    }

    public static double mean(double[][] var) {
        double res = 0;
        for(double[] v: var) {
            res += v[0] * v[1];
        }

        return res;
    }

    public static double nRawMoment(double[][] var, int n) {
        double res = 0;
        for(double[] v: var) {
            res += Math.pow(v[0], n) * v[1];
        }

        return res;
    }

    public static double nCentralMoment(double[][] var, int n) {
        double exp = mean(var);

        double res = 0;
        for(double[] v: var) {
            res += Math.pow(v[0] - exp, n) * v[1];
        }

        return res;
    }

    public static double var(double[][] var) {
        double exp = mean(var);
        return nRawMoment(var, 2) - exp * exp;
    }

    public static double standardDev(double[][] var) {
        return Math.sqrt(var(var));
    }

    public static double varCoef(double[][] var) {
        return standardDev(var) / mean(var);
    }

    public static double skewness(double[][] variable) {
        double stDev = standardDev(variable);
        double exp = mean(variable);
        return (nRawMoment(variable, 3) - 3 * exp * stDev * stDev - exp * exp * exp) / (stDev * stDev * stDev);
    }

    public static double kurtosis(double[][] variable) {
        double var = var(variable);

        return nCentralMoment(variable, 4) / (var * var) - 3;
    }

    public static double mod(double[][] variable) {
        double res = Double.NEGATIVE_INFINITY;
        double max = Double.MIN_VALUE;

        for(double[] v: variable) {
            if(v[1] > max) {
                max = v[1];
                res = v[0];
            }
        }

        return res;
    }

    public static double[] regression(double[] x, double[] y, double[][] prob) {
        double[][] varX = buildHorizontalVariable(x, prob);
        double[][] varY = buildVerticalVariable(y, prob);

        double EX = mean(varX);
        double EY = mean(varY);
        double devX = standardDev(varX);
        double devY = standardDev(varY);

        double cor = correlation(x, y, prob);

        double coeff = devX/devY*cor;

        return new double[] {coeff, EX - EY * coeff};
    }

    public static double[][] transpose(double[][] matrix) {
        if(matrix.length == 0) {
            return matrix;
        }

        double[][] trMatrix = new double[matrix[0].length][matrix.length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                trMatrix[j][i] = matrix[i][j];
            }
        }

        return trMatrix;
    }

    public static double сovariance(double[] x, double[] y, double[][] prob) {
        double EXY = 0;
        for(int i = 0; i < prob.length; i++) {
            for(int j = 0; j < prob[0].length; j++) {
                EXY += x[i]*y[j]*prob[i][j];
            }
        }

        double[][] varX = buildHorizontalVariable(x, prob);
        double[][] varY = buildVerticalVariable(y, prob);

        double EX = mean(varX);
        double EY = mean(varY);

        return EXY - EX * EY;
    }

    public static double correlation(double[] x, double[] y, double[][] prob) {
        double covariance = сovariance(x, y, prob);
        double[][] variableX = buildHorizontalVariable(x, prob);
        double[][] variableY = buildVerticalVariable(y, prob);

        double varX = var(variableX);
        double varY = var(variableY);

        return covariance / Math.sqrt(varX*varY);
    }

    public static double[] buildHorizontalProbability(double[][] prob) {
        if(prob.length == 0) {
            return new double[0];
        }

        double[] res = new double[prob.length];

        for(int i = 0; i< res.length; i++) {
            res[i] = Arrays.stream(prob[i]).sum();
        }
        return res;
    }

    public static double[] buildVerticalProbability(double[][] prob) {
        if(prob.length == 0) {
            return new double[0];
        }

        double[] res = new double[prob[0].length];

        for(int i = 0; i < res.length; i++) {
            for (double[] row: prob) {
                res[i] += row[i];
            }
        }
        return res;
    }

    private static double[][] buildHorizontalVariable(double[] x, double[][] prob) {
        double[] pr = buildHorizontalProbability(prob);
        return buildVariable(x, pr);
    }

    private static double[][] buildVerticalVariable(double[] y, double[][] prob) {
        double[] pr = buildVerticalProbability(prob);
        return buildVariable(y, pr);
    }

    public static double[][] buildVariable(double[] var, double[] prob) {
        double[][] res = new double[var.length][2];

        for(int i = 0; i< var.length; i++) {
            res[i][0] = var[i];
            res[i][1] = prob[i];
        }

        return res;
    }

    public static double median(double[][] variable) {
        double sum = 0;

        for(double[] v: variable) {
            if(sum < 0.5) {
                sum += v[1];
            } else {
                return v[0];
            }
        }

        return -1;
    }

    public static double[][] varForGenF(double[] genF) {
        double[][] res = new double[genF.length][2];
        int i = 0;
        for(double c: genF) {
            res[i] = new double[]{genF.length - 1 - i, c};
            i++;
        }

        return res;
    }

    public static double[] genF(double[] prob) {
        double[] res = new double[prob.length + 1];
        int l = res.length - 1;
        res[l] = 1;
        int i = l;
        for(double pr: prob) {
            for(int j = i; j < res.length; j++) {
                res[j - 1] = res[j] * pr + res[j - 1] * (1 - pr);
            }
            res[l] = res[l] * (1 - pr);
            i--;
        }

        return res;
    }
}
