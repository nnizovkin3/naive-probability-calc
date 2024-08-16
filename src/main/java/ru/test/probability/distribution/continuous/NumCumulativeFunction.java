package ru.test.probability.distribution.continuous;

import lombok.RequiredArgsConstructor;
import ru.test.func.numeric.NumFunction;

@RequiredArgsConstructor
public class NumCumulativeFunction implements CumulativeFunction {
    public static final double ERROR_THRESHOLD = 0.0001;
    private final NumFunction denF;
    private final double negInf;

    @Override
    public String getDescription() {
        return denF.getDescription();
    }

    @Override
    public double calc(double x) {
        return trim(denF.integrate(negInf, x));
    }

    @Override
    public double calc(double a, double b) {
        return trim(denF.integrate(a, b));
    }

    private double trim(double pr) {
        double v = pr - 1;
        if(v > ERROR_THRESHOLD) {
            throw new RuntimeException("pr=" + pr);
        }
        if(v > 0) {
            return 1;
        }

        return pr;
    }

    @Override
    public String toString() {
        return "NumCumulativeFunction{" +
                "denF=" + denF +
                ", negInf=" + negInf +
                '}';
    }
}
