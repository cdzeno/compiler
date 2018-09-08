package lt.compiler.expr;

import lt.macchina.Codice;
import static lt.macchina.Macchina.*;

public class ModExpr implements Expr {
    private Expr leftExpr;
    private Expr rightExpr;

    public ModExpr(Expr left, Expr right) {
        leftExpr = left;
        rightExpr = right;
    }

    public void generateCode(Codice c) {
        leftExpr.generateCode(c);
        rightExpr.generateCode(c);

        // Insert into special MOD register the result
        // of the left and right expr
        c.genera(POP, 1);
        c.genera(POP, 0);

        // Code used to compute MOD making: a - b * (a / b)
        // where a = 0 register
        // and   b = 1 register
        c.genera(PUSH, 0);
        c.genera(PUSH, 1);
        c.genera(PUSH, 0);
        c.genera(PUSH, 1);

        c.genera(DIV);
        c.genera(MUL);
        c.genera(SUB);
    }

    public String toString() {
        return leftExpr.toString() + " % " + rightExpr.toString();
    }
}

