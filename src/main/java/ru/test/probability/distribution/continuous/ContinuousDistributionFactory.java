package ru.test.probability.distribution.continuous;

import lombok.RequiredArgsConstructor;
import ru.test.func.numeric.NumCalculator;
import ru.test.func.numeric.NumFunction;
import ru.test.func.sym.BasicSymFunction;
import ru.test.func.sym.SymCalculator;
import ru.test.func.sym.SymFunction;
import ru.test.func.sym.SymPiecewiseFunction;

import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
public class ContinuousDistributionFactory {
    private final SymCalculator symCalculator;
    private final NumCalculator numCalculator;
    public ContinuousDistribution createDistrForSymPiecewise(Map<Double, String> f, String d) {
        SymFunction func = new SymPiecewiseFunction(f, d, symCalculator);
        return createDistrByDensity(func);
    }

    public ContinuousDistribution createDistrForSym(String d) {
        SymFunction symFunction = new BasicSymFunction(d, symCalculator);
        return createDistrByDensity(symFunction);
    }

    public ContinuousDistribution createDistrForNum(Function<Double, Double> f, String d, double negInf, double posInf) {
        NumFunction func = new NumFunction(f, d, numCalculator);
        NumDensityFunction densityFunction = new NumDensityFunction(func, negInf, posInf);
        NumCumulativeFunction cumulativeFunction = new NumCumulativeFunction(func, negInf);
        return new ContinuousDistribution(densityFunction, cumulativeFunction);
    }

    public ContinuousDistribution createDistrForSymPiecewiseByCumulativeFunc(Map<Double, String> f, String d) {
        SymFunction func = new SymPiecewiseFunction(f, d, symCalculator);
        return createDistrByCumulative(func);
    }

    public ContinuousDistribution createDistrForSymByCumulativeFunc(String d) {
        SymFunction func = new BasicSymFunction(d, symCalculator);
        return createDistrByCumulative(func);
    }

    private ContinuousDistribution createDistrByDensity(SymFunction denFunc) {
        SymDensityFunction densityFunction = new SymDensityFunction(denFunc);
        CumulativeFunction cumulativeFunc = createCumulativeFunc(denFunc.integrate());
        return new ContinuousDistribution(densityFunction, cumulativeFunc);
    }

    private ContinuousDistribution createDistrByCumulative(SymFunction cumFunc) {
        SymFunction der = cumFunc.derivative();
        SymDensityFunction densityFunction = new SymDensityFunction(der);
        CumulativeFunction cumulativeFunction = new SymCumulativeFunction(cumFunc);
        return new ContinuousDistribution(densityFunction, cumulativeFunction);

    }

    private CumulativeFunction createCumulativeFunc(SymFunction func) {
        return new SymCumulativeFunction(func);
    }
}
