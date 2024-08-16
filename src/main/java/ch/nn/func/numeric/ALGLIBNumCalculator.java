package ch.nn.func.numeric;

import alglib.alglib;
import lombok.SneakyThrows;

import java.util.function.Function;

import static alglib.alglib.*;

public class ALGLIBNumCalculator implements NumCalculator {
    @SneakyThrows
    @Override
    public double integrate(Function<Double, Double> f, double a, double b) {
        alglib.autogkstate s = autogksmooth(a, b);
        autogkintegrate (s, (x, xa, xb, obj) -> f.apply(x), null);
        var res = autogkresults(s);
        return res.v;
    }
}
