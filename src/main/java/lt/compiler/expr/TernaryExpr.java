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
        // Generate code for the codition of the ternary operator:
        condition.generateCode(c);

        // Generate code for the jump in case of false condition:
        int jumpFalse = c.generaParziale(JZERO);

        // Generate code in case of true condition:
        trueExpr.generateCode(c);

        // Generate code for the jump in case of true condition:
        int jumpEnd = c.generaParziale(JUMP);

        // Complete the false-jump instruction now that I know at which
        // address is the false-case code:
        c.completaIstruzione(jumpFalse, c.indirizzoProssimaIstruzione());

        // Generate the false-case code:
        falseExpr.generateCode(c);

        // Now that I know where the ternary operator ends, complete the
        // unconditional jump used after the true-case code:
        c.completaIstruzione(jumpEnd, c.indirizzoProssimaIstruzione());
    }

    public String toString() {
        return condition.toString() + " ? " + trueExpr.toString() + " : " + falseExpr.toString();
    }
}

