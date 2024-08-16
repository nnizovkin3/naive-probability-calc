package ru.test.func.numeric;

import alglib.alglib;

import java.lang.reflect.InvocationTargetException;

import static alglib.alglib.*;

public class AlgLibExample {
    public static void main(String[] args) throws alglib.exception, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println(Math.log(3)/Math.log(2));
        alglib.autogkstate s = autogksmooth(0, 1);
        autogkintegrate (s, (x, xa, xb, obj) -> x, null);
        var res = autogkresults(s);

        System.out.println(res.v);
    }
}
