package ch.nn.func.sym;

import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.stream.Collectors;


@Getter
//for bounded on the start and end functions
public class SymPiecewiseFunction implements SymFunction {
    private final TreeMap<Double, SymFunction> func;
    private final SymCalculator symCalculator;

    public SymPiecewiseFunction(Map<Double, String> func, String lastPiece, SymCalculator symCalculator) {
        this.symCalculator = symCalculator;
        this.func = new TreeMap<>();
        for(var e: func.entrySet()) {
            this.func.put(e.getKey(), new BasicSymFunction(e.getValue(), symCalculator));
        }

        this.func.put(Double.MAX_VALUE, new BasicSymFunction(lastPiece, symCalculator));
    }

    private SymPiecewiseFunction(Map<Double, String> func, SymCalculator symCalculator) {
        this.func = new TreeMap<>(func.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                e -> new BasicSymFunction(e.getValue(), symCalculator))));
        this.symCalculator = symCalculator;
    }
    //todo to format 'Piecewise({{x^2, x < 0}, {x, x >= 0&&x<1},{Cos(x-1), x >= 1}}'
    @Override
    public String getDescription() {
        return func.toString();
    }

    @Override
    public double calc(double x) {
        var entry = func.higherEntry(x);
        return symCalculator.calc(entry.getValue().getDescription(), x);
    }

    @Override
    public SymFunction integrate() {
        double diff = 0d;
        double prev = Integer.MIN_VALUE;
        Map<Double, String> res = new TreeMap<>();
        for(var e: func.entrySet()) {
            String exp = symCalculator.integral(e.getValue().getDescription());
            diff -= symCalculator.calc(exp, prev);
            String pieceF = symCalculator.add(exp, Double.toString(diff));
            diff += symCalculator.calc(exp, e.getKey());
            prev = e.getKey();
            res.put(e.getKey(), pieceF);
        }

        return new SymPiecewiseFunction(res, symCalculator);
    }

    public SymPiecewiseFunction derivative() {
        return new SymPiecewiseFunction(func.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> symCalculator.derivative(e.getValue().getDescription()))), symCalculator);
    }

    @Override
    public SymFunction add(String exp) {
        return biFunc(exp, symCalculator::add);
    }

    @Override
    public SymFunction subtract(String exp) {
        return biFunc(exp, symCalculator::subtract);
    }

    @Override
    public SymFunction multiply(String exp) {
        return biFunc(exp, symCalculator::multiply);
    }

    private SymPiecewiseFunction biFunc(String expression, BiFunction<String, String, String> biFunc) {
        return new SymPiecewiseFunction(func.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> biFunc.apply(e.getValue().getDescription(), expression))), symCalculator);
    }

    @Override
    public String toString() {
        return func.toString();
    }
}
