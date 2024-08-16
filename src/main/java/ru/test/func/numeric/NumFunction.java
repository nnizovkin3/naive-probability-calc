package ru.test.func.numeric;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.test.func.Func;
import ru.test.func.sym.BasicSymFunction;
import ru.test.func.sym.SymFunction;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public class NumFunction implements Func {
    private final Function<Double, Double> f;
    private final String description;

    private final NumCalculator calculator;

    @Override
    public String getDescription() {
        return description;
    }

    public double calc(double x) {
        return f.apply(x);
    }

    public double integrate(double a, double b) {
        return calculator.integrate(f, a, b);
    }

    public NumFunction add(Function<Double, Double> f2, String d2) {
        return new NumFunction(calculator.add(f, f2), "(%s)+(%s)".formatted(description, d2), calculator);
    }

    public NumFunction subtract(Function<Double, Double> f2, String d2) {
        return new NumFunction(calculator.subtract(f, f2), "(%s)-(%s)".formatted(description, d2), calculator);
    }

    public NumFunction multiply(Function<Double, Double> f2, String d2) {
        return new NumFunction(calculator.multiply(f, f2), "(%s)*(%s)".formatted(description, d2), calculator);
    }

    @Override
    public String toString() {
        return description;
    }
}

