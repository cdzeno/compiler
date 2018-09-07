package lt.compiler.expr;

import lt.macchina.Codice;

public interface Expr {
    void generateCode(Codice c);
}

