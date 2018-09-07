package lt.compiler.expr;

import lt.macchina.Codice;
import static lt.macchina.Macchina.*;

public class SubExpr implements Expr {
    private Expr leftExpr;
    private Expr rightExpr;

    public SubExpr(Expr left, Expr right) {
        leftExpr = left;
        rightExpr = right;
    }

    public void generateCode(Codice c) {
        leftExpr.generateCode(c);
        rightExpr.generateCode(c);
        c.genera(SUB);
    }

    public String toString() {
        return leftExpr.toString() + " - " + rightExpr.toString();
    }
}

