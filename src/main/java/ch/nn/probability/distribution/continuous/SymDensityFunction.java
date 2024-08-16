package ch.nn.probability.distribution.continuous;

import ch.nn.func.sym.SymFunction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SymDensityFunction implements DensityFunction {
    private final SymFunction func;
    private final double negInf;
    private final double posInf;

    public SymDensityFunction(SymFunction func) {
        this(func, Integer.MIN_VALUE, Integer.MAX_VALUE);
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
        return calcInproperIntegral(func.multiply("x"));
    }

    @Override
    public double variance() {
        double mean = mean();
        return calcInproperIntegral(func.multiply("x*x")) - mean * mean;
    }

    private double calcInproperIntegral(SymFunction f) {
        var integral = f.integrate();
        return integral.calc(posInf) - integral.calc(negInf);
    }

    @Override
    public String toString() {
        return func.toString();
    }
}
