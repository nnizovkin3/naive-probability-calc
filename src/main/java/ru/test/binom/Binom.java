package ru.test.binom;

import java.math.BigInteger;
import java.util.Arrays;

public class Binom {
    public static BigInteger[] calcCoeffs(int n) {
        BigInteger[] res = new BigInteger[n+1];
        res[0] = BigInteger.ONE;
        res[n] = BigInteger.ONE;

        for(int i = 1; i <= n / 2; i++) {
            res[i] = res[i-1].multiply(BigInteger.valueOf(n-i+1)).divide(BigInteger.valueOf(i));
            res[n-i] = res[i];
        }

        return res;
    }

    public static BigInteger calcCoeff(int n, int k) {
        BigInteger prev = BigInteger.ONE;
        k = k <= n / 2 ? k : n - k;

        for(int i = 1; i <= k; i++) {
            prev = prev.multiply(BigInteger.valueOf(n-i+1)).divide(BigInteger.valueOf(i));
        }

        return prev;
    }

    public static BigInteger[][] calcCoeffsPascalTriangle(int n) {
        BigInteger[][] res = new BigInteger[n+1][];
        res[0] = new BigInteger[]{BigInteger.ONE};

        for(int i = 1; i <= n; i++) {
            res[i] = new BigInteger[i+1];
            res[i][0] = BigInteger.ONE;
            res[i][i] = BigInteger.ONE;
            for(int j = 1; j < i; j++) {
                res[i][j] = res[i-1][j-1].add(res[i-1][j]);
            }
        }

        return res;
    }

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
