package ch.nn.func.sym;

import ch.nn.func.Func;

public interface SymFunction extends Func {
    SymFunction integrate();
    SymFunction derivative();
    SymFunction add(String exp);
    SymFunction subtract(String exp);
    SymFunction multiply(String exp);
}
