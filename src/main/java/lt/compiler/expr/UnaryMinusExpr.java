package lt.compiler.expr;

import lt.macchina.Codice;
import static lt.macchina.Macchina.*;

public class UnaryMinusExpr implements Expr {
    private Expr expr;

    public UnaryMinusExpr(Expr e) {
        expr = e;
    }

    public void generateCode(Codice c) {
        c.genera(PUSHIMM, 0);
        expr.generateCode(c);
        c.genera(SUB);
    }

    public String toString() {
        return  "-" + expr.toString();
    }
}

