package ru.test.probability.distribution.continuous;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.test.func.sym.SymFunction;

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
