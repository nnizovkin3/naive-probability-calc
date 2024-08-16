package ru.test.probability.distribution.continuous;

import org.hipparchus.distribution.continuous.NormalDistribution;
import org.junit.jupiter.api.Test;
import ru.test.func.numeric.ALGLIBNumCalculator;
import ru.test.func.numeric.NumCalculator;
import ru.test.func.sym.SymCalculator;
import ru.test.func.sym.SymJaCalculator;

import java.util.List;
import java.util.Map;

import static java.lang.Integer.MAX_VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//https://www.matburo.ru/ex_tv.php?p1=tvnepr
public class ContinuousDistributionTest {
    public static final double THREASHOLD = 0.00001;
    private ContinuousDistributionFactory factory;

    public ContinuousDistributionTest() {
        SymCalculator symCalculator = new SymJaCalculator();
        NumCalculator numCalculator = new ALGLIBNumCalculator();
        factory = new ContinuousDistributionFactory(symCalculator, numCalculator);
    }

    @Test
    void testCase1() {
        ContinuousDistribution distr = factory.createDistrForSymPiecewise(Map.of(Math.PI, "0",
                Math.PI * 3 / 2, "-cos(x)"), "0");
        assertWithPrecision(1/Math.sqrt(2), distr.probability(Math.PI, Math.PI * 5 / 4));
        assertWithPrecision(3*Math.PI/2-1, distr.mean());
        assertWithPrecision(Math.PI-3, distr.variance());
    }

    @Test
    void testCase2() {
        ContinuousDistribution distr = factory.createDistrForSymPiecewise(Map.of(1.0, "0",
                3.0, "(x-1)/2"), "0");
        System.out.println(distr.getCumulativeFunction());
        assertWithPrecision(1, distr.probability(100.));
        assertWithPrecision(7.0/3, distr.mean());
        assertWithPrecision(2.0/9, distr.variance());
    }

    @Test
    void testCase4() {
        ContinuousDistribution distr = factory
                .createDistrForSymPiecewiseByCumulativeFunc(Map.of(0.0, "0"), "1-E^(-2*x)");
        System.out.println(distr.getDensityFunction());
        checkExponentialDistr(distr);
    }

    @Test
    void testCase5() {
        ContinuousDistribution distr = factory.createDistrForSymPiecewise(Map.of(0.0, "0"), "2*E^(-2*x)");
        System.out.println(distr.getDensityFunction());
        checkExponentialDistr(distr);
    }

    @Test
    void testCase6() {
        ContinuousDistribution distr = factory.createDistrForSymPiecewiseByCumulativeFunc(Map.of(-1.0, "0",
                1.0, "1.0/2 + arcsin(x)/Pi"), "0");
        System.out.println(distr.getDensityFunction());
        System.out.println(distr.getCumulativeFunction());

        assertWithPrecision(0, distr.mean());
        //todo another lib for this integral calc
//        System.out.println(distr.variance());
        ContinuousDistribution distr2 = factory.createDistrForNum(x -> -1 <= x && x <= 1 ? 1/(Math.PI * Math.sqrt(1-x*x)) : 0,
                "", -10, 10);

        //todo add piecewise, use infity boundary handling
//        assertWithPrecision(distr.probability(-0.5), distr2.probability(-0.5));
    }

    @Test
    void testCase7() {
        ContinuousDistribution distr = factory.createDistrForSymByCumulativeFunc("0.5 + arctan(x/2)/Pi");
        System.out.println(distr.getDensityFunction());
        System.out.println(distr.getCumulativeFunction());
        assertWithPrecision(0.2951672, distr.probability(-1.0, 1.0));

        ContinuousDistribution numDistr = factory.createDistrForNum(x -> 1/(2*Math.PI*(1 + x*x/4)), "", -1000000, 1000000);
        assertWithPrecision(0.2951672, numDistr.probability(-1.0, 1.0));
        compareTwoDistr(distr, numDistr, List.of(-100.0, -10.0, -1.0, -0.5, 0.0, 0.5, 1.0, 10.0, 100.0));
    }

    @Test
    void testCase8() {
        ContinuousDistribution distr = factory.createDistrForSymPiecewise(Map.of(23.0, "0"), "4/23*(23/x)^5");
        System.out.println(distr.getCumulativeFunction());
        assertWithPrecision(251600/531441.0, distr.probability(23.0, 27.0));
        assertWithPrecision(92/3.0, distr.mean());
    }

    @Test
    void testCase9() {
        ContinuousDistribution distr = factory.createDistrForSymPiecewiseByCumulativeFunc(Map.of(1.0, "0",
                Math.E, "ln(x)"), "1");
        System.out.println(distr.getDensityFunction());
        assertWithPrecision(1 - Math.log(2), distr.probability(2.0, Math.E));
        assertWithPrecision(Math.E - 1, distr.mean());
        assertWithPrecision(-Math.E * Math.E/2 + 2 * Math.E - 3.0/2, distr.variance());
    }

    @Test
    void testCase10() {
        ContinuousDistribution distr = factory.createDistrForSymPiecewise(Map.of(0.0, "1/2*E^x"),
                "1/2*E^(-x)");
        assertWithPrecision(0, distr.mean());
        assertWithPrecision(2, distr.variance());
    }

    @Test
    void testCase11() {
        ContinuousDistribution distr = factory.createDistrForSymPiecewiseByCumulativeFunc(Map.of(0.0, "0",
                1.0, "x*x"), "1");
        assertWithPrecision(2.0/3, distr.mean());
        assertWithPrecision(1/18.0, distr.variance());
    }

    private void compareTwoDistr(ContinuousDistribution distr, ContinuousDistribution numDistr, List<Double> points) {
        for(double p: points) {
            assertWithPrecision(distr.probability(p), numDistr.probability(p));
        }
    }

    private void compareTwoDistrMean(ContinuousDistribution distr, ContinuousDistribution numDistr, double mean) {
        assertWithPrecision(mean, distr.mean());
        assertWithPrecision(mean, numDistr.mean());
    }

    private void checkExponentialDistr(ContinuousDistribution distr) {
        assertWithPrecision(0, (distr.probability(-100.)));
        assertWithPrecision(1, (distr.probability(100.)));
        assertWithPrecision(1 - 1/Math.E, distr.probability(0.5));
        assertWithPrecision(1 - 1/Math.E, distr.probability(-100., 0.5));
        assertWithPrecision(0, distr.probability(-100., 0.0));
        assertWithPrecision(1/Math.E, distr.probability(0.5, 100.0));
        assertWithPrecision(1.0/2, distr.mean());
        assertWithPrecision(1.0/4, distr.variance());
    }

    @Test
    void testNumDistr() {
        ContinuousDistribution distr = factory.createDistrForNum(
                x -> 0 <= x && x <= 1 ? 1.0 : 0, "Piecewise({{0, x < 0}, {1, x >= 0&&x<=1},{0, x > 1}}",
                0, 1);
        assertWithPrecision(0, (distr.probability(-100.)));
        assertWithPrecision(1, (distr.probability(100.)));
        assertWithPrecision(0.5, distr.probability(-100., 0.5));
        assertWithPrecision(0, distr.probability(-100., 0.0));
        assertWithPrecision(0.5, distr.probability(0.5, 100.0));
        assertWithPrecision(1.0/2, distr.mean());
        assertWithPrecision(1.0/12, distr.variance());
    }

    @Test
    void testNumNormalDistr() {
        NormalDistribution normalDistribution = new NormalDistribution(0, 1);
        ContinuousDistribution distr = factory.createDistrForNum(
                x -> Math.pow(Math.E, -x*x/2) / (Math.sqrt(2* Math.PI)), "e^(-x^2/2)/sqrt(2Pi)",
                -11, 11);
        assertWithPrecision(normalDistribution.probability(-1, 1), distr.probability(-1, 1));
        assertWithPrecision(normalDistribution.probability(-2, 2), distr.probability(-2, 2));
        assertWithPrecision(normalDistribution.probability(-3, 3), distr.probability(-3, 3));
        assertWithPrecision(normalDistribution.probability(-10, 10), distr.probability(-10, 10));
        assertWithPrecision(normalDistribution.cumulativeProbability(-1), distr.probability(-1));
        assertWithPrecision(normalDistribution.cumulativeProbability(-0.3), distr.probability(-.3));
        assertWithPrecision(normalDistribution.cumulativeProbability(0), distr.probability(0));
        assertWithPrecision(normalDistribution.cumulativeProbability(0.3), distr.probability(.3));
        assertWithPrecision(normalDistribution.cumulativeProbability(1), distr.probability(1));
        for(double a=-10; a<10; a+=0.1) {
            for(double b=0; b<10; b+=0.1) {
                assertWithPrecision(normalDistribution.probability(a, a + b), distr.probability(a, a + b));
            }
        }
        assertWithPrecision(0, distr.probability(-100.));
        assertWithPrecision(1, distr.probability(100.0));
        assertWithPrecision(1, distr.probability(-1000.0, 1000.0));
        assertWithPrecision(0, distr.mean());
        assertWithPrecision(1, distr.variance());
        distr = factory.createDistrForNum(
                x -> Math.pow(Math.E, -(x-1)*(x-1)/2) / (Math.sqrt(2* Math.PI)), "",
                -50.0, 50);
        assertWithPrecision(1, distr.mean());
        assertWithPrecision(1, distr.variance());
        distr = factory.createDistrForNum(
                x -> Math.pow(Math.E, -x*x/(2*3)) / (Math.sqrt(2*Math.PI*3)), "",
                -50.0, 50);
        assertWithPrecision(0, distr.mean());
        assertWithPrecision(3, distr.variance());
        distr = factory.createDistrForNum(
                x -> Math.pow(Math.E, -(x-1)*(x-1)/(2*3)) / (Math.sqrt(2*Math.PI*3)), "",
                -50.0, 50);
        assertWithPrecision(1, distr.mean());
        assertWithPrecision(3, distr.variance());
    }

    private void assertWithPrecision(double expected, double actual) {
        String data = "%.7f %.7f".formatted(expected, actual);
        assertTrue(Math.abs(expected - actual) < THREASHOLD, data);
    }
}
