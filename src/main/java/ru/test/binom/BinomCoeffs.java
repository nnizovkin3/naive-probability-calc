package ru.test.binom;

import java.math.BigInteger;

public class BinomCoeffs {
    private final BigInteger[] coeffs;
    private final int n;

    //todo write for negative values. Concrete math
    public BinomCoeffs(int n) {
        if(n < 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        coeffs = Binom.calcCoeffs(n);
    }

    public BigInteger cnk(int k) {
        if(k < 0) {
            throw new IllegalArgumentException();
        }

        if(k > n) {
            return BigInteger.ZERO;
        }

        return coeffs[k];
    }
}
