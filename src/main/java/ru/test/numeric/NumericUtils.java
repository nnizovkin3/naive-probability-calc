package ru.test.numeric;

import java.math.BigInteger;

public class NumericUtils {
    public static final int BOUND = 10;

    //todo add threshold and use fft after fact(3000000), if need
    public static BigInteger fact(int n) {
        if(n < 0) {
            throw new IllegalArgumentException();
        }

        return mult(1, n);
    }

    private static BigInteger mult(int start, int end) {
        if(end - start >= BOUND) {
            int mid = (end + start) / 2;
            return mult(start, mid).multiply(mult(mid + 1, end));
        }

        BigInteger res = new BigInteger("1");
        for(int i = start; i <= end; i++) {
            res = res.multiply(BigInteger.valueOf(i));
        }

        return res;
    }
}
