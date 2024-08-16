package ch.nn.binom;

import java.util.Arrays;

import static ch.nn.binom.Binom.*;

public class BinomExample {

    public static void main(String[] args) {
        BinomCoeffs bc = new BinomCoeffs(4);
        System.out.println(bc.cnk(0));
        System.out.println(bc.cnk(1));
        System.out.println(bc.cnk(2));
        System.out.println(bc.cnk(3));
        System.out.println(bc.cnk(4));
        System.out.println(bc.cnk(5));

        System.out.println(Arrays.toString(calcCoeffs(1)));
        System.out.println(Arrays.toString(calcCoeffs(2)));
        System.out.println(Arrays.toString(calcCoeffs(3)));
        System.out.println(Arrays.toString(calcCoeffs(4)));
        System.out.println(Arrays.deepToString(calcCoeffsPascalTriangle(4)));
        System.out.println(Arrays.toString(calcCoeffs(5)));
        System.out.println(calcCoeff(4, 0));
        System.out.println(calcCoeff(4, 1));
        System.out.println(calcCoeff(4, 2));
        System.out.println(calcCoeff(4, 3));
        System.out.println(calcCoeff(4, 4));
        long start = System.nanoTime();
        System.out.println(calcCoeffs(100000).length);
        System.out.println((System.nanoTime() - start) / 1000000000.0);
        start = System.nanoTime();
        System.out.println(calcCoeffsPascalTriangle(1000).length);
        System.out.println((System.nanoTime() - start) / 1000000000.0);
    }
}
