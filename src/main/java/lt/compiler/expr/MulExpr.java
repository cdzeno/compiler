package lt.compiler.expr;

import lt.macchina.Codice;
import static lt.macchina.Macchina.*;

public class MulExpr implements Expr {
    private Expr leftExpr;
    private Expr rightExpr;

    public MulExpr(Expr left, Expr right) {
        leftExpr = left;
        rightExpr = right;
    }

    public void generateCode(Codice c) {
        leftExpr.generateCode(c);
        rightExpr.generateCode(c);
        c.genera(MUL);
    }

    public String toString() {
        return leftExpr.toString() + " * " + rightExpr.toString();
    }
}

