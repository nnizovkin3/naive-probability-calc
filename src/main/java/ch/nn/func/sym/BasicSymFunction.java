package ch.nn.func.sym;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//without singularity at the boundary
@RequiredArgsConstructor
@Getter
public class BasicSymFunction implements SymFunction {
    private final String expression;
    private final SymCalculator symCalculator;

    @Override
    public String getDescription() {
        return expression;
    }

    @Override
    public double calc(double x) {
        return symCalculator.calc(expression, x);
    }
    @Override
    public SymFunction integrate() {
        return new BasicSymFunction(symCalculator.integral(expression), symCalculator);
    }
    @Override
    public SymFunction derivative() {
        return new BasicSymFunction(symCalculator.derivative(expression), symCalculator);
    }
    @Override
    public SymFunction add(String exp) {
        return new BasicSymFunction(symCalculator.add(exp, expression), symCalculator);
    }
    @Override
    public SymFunction subtract(String exp) {
        return new BasicSymFunction(symCalculator.subtract(exp, expression), symCalculator);
    }
    @Override
    public SymFunction multiply(String exp) {
        return new BasicSymFunction(symCalculator.multiply(exp, expression), symCalculator);
    }

    @Override
    public String toString() {
        return expression;
    }
}
