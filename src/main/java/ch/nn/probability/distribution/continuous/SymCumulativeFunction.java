package ch.nn.probability.distribution.continuous;

import ch.nn.func.sym.SymFunction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SymCumulativeFunction implements CumulativeFunction {
    private final SymFunction symFunction;

    @Override
    public String getDescription() {
        return symFunction.getDescription();
    }

    @Override
    public double calc(double x) {
        return symFunction.calc(x);
    }

    @Override
    public String toString() {
        return symFunction.toString();
    }
}
