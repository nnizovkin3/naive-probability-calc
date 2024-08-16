package ru.test.func.sym;

import ru.test.func.Func;

public interface SymFunction extends Func {
    SymFunction integrate();
    SymFunction derivative();
    SymFunction add(String exp);
    SymFunction subtract(String exp);
    SymFunction multiply(String exp);
}
