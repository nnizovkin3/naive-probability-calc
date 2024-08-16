package ch.nn.func.sym;

import java.util.Arrays;

public class SymJaCalculatorExample {

    public static void main(String[] args) {
        SymJaCalculator c = new SymJaCalculator();
//        Map<Object, Object> map = new HashMap<>();
//        for(int i = 0; i < 100; i++) {
//            map.put(new Object(), new Object());
//        }
//        System.out.println(c.calc(c.integral("log(x)"), 0.05605170185988091));
//        System.out.println(c.integral("sin(x)"));
//        String exp = c.integral("Piecewise({{0, x < 0}, {x, x >= 0&&x<1},{0, x >= 1}})");
//        System.out.println(exp);
//        System.out.println(c.calc(exp, -10));
//        System.out.println(c.calc(exp, -0.05605170185988091));
//        System.out.println(c.calc(exp, 1.5));
//        evaluator.get().eval("x=10");
//        double evalf = evaluator.get().eval("cos(x)").evalf();
//        System.out.println(c.derivative("1/2 - arctan(x/2)/Pi"));
//
        System.out.println(c.integral("cos(x)*x"));
        System.out.println(c.integral("E^(t*x)*sin(x)/2"));
        System.out.println(Arrays.toString(new int[1]));
        Arrays.stream(new int[1]).max().getAsInt();
    }
}
