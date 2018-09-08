package lt.compiler.expr;

import lt.macchina.Codice;
import static lt.macchina.Macchina.*;

public class TernaryExpr implements Expr {
    private Expr condition;
    private Expr trueExpr;
    private Expr falseExpr;

    public TernaryExpr(Expr c, Expr t, Expr f) {
        condition = c;
        trueExpr = t;
        falseExpr = f;
    }

    public void generateCode(Codice c) {
        // Generate code for the codition of the ternary operator
        condition.generateCode(c);

        // Generate code for the jump in case of false condition
        int jumpFalse = c.generaParziale(JZERO);

        // Generate code for the expression in case of true condition
        trueExpr.generateCode(c);
        // Jump to avoid evaluating false-case expression
        int jumpEnd = c.generaParziale(JUMP);

        // Complete the false-jump instruction now that the jump
        // address is known
        c.completaIstruzione(jumpFalse, c.indirizzoProssimaIstruzione());

        // Generate the false-case expression code
        falseExpr.generateCode(c);

        // Complete the unconditional jump from the end of the true-case
        // expression code, now that the jump address is known
        c.completaIstruzione(jumpEnd, c.indirizzoProssimaIstruzione());
    }

    public String toString() {
        return condition.toString() + " ? " + trueExpr.toString() + " : " + falseExpr.toString();
    }
}

