package ch.nn.func.sym;

public interface SymCalculator {
    double calc(String expression, double x);
    String integral(String expression);
    String derivative(String expression);
    default String add(String expression1, String expression2) {
        return "(%s)+(%s)".formatted(expression1, expression2);
    }
    default String subtract(String expression1, String expression2) {
        return "(%s)-(%s)".formatted(expression1, expression2);
    }
    default String multiply(String expression1, String expression2) {
        return "(%s)*(%s)".formatted(expression1, expression2);
    }
}
