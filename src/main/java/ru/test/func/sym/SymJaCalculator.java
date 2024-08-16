package ru.test.func.sym;

import org.matheclipse.core.eval.ExprEvaluator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SymJaCalculator implements SymCalculator {
    private static ThreadLocal<ExprEvaluator> evaluator = ThreadLocal.withInitial(()
            -> new ExprEvaluator(false, (short) 100));
    static {
        Locale.setDefault(Locale.CHINESE);
    }

    @Override
    public double calc(String expression, double x) {
        evaluator.get().eval("x=%.15f".formatted(x));
        double evalf = evaluator.get().eval(expression).evalf();
        //fixme. done for reusing
        evaluator.set(new ExprEvaluator(false, (short) 100));
        return evalf;
    }

    @Override
    public String integral(String expression) {
        String exp = "integrate(%s,x)".formatted(expression);
        return evaluator.get().eval(exp).toString();
    }

    @Override
    public String derivative(String expression) {
        String exp = "diff(%s,x)".formatted(expression);
        return evaluator.get().eval(exp).toString();
    }
}
