package ch.nn.probability.distribution.continuous;

import ch.nn.func.numeric.NumFunction;

public class NumDensityFunction implements DensityFunction {
    private final NumFunction func;
    private final double negInf;
    private final double posInf;

    public NumDensityFunction(NumFunction func, double negInf, double posInf) {
        this.func = func;
        this.negInf = negInf;
        this.posInf = posInf;
    }

    @Override
    public String getDescription() {
        return func.getDescription();
    }

    @Override
    public double calc(double x) {
        return func.calc(x);
    }

    @Override
    public double mean() {
        NumFunction meanFunc = func.multiply(x -> x, "x");
        return calcInproperIntegral(meanFunc);
    }

    @Override
    public double variance() {
        double mean = mean();
        NumFunction varianceFunc = func.multiply(x -> x*x, "x*x");
        return calcInproperIntegral(varianceFunc) - mean * mean;
    }

    private double calcInproperIntegral(NumFunction f) {
        return f.integrate(negInf, posInf);
    }

    @Override
    public String toString() {
        return "NumDensityFunction{" +
                "func=" + func +
                ", negInf=" + negInf +
                ", posInf=" + posInf +
                '}';
    }
}
