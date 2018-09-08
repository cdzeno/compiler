package lt.compiler.expr;

import lt.macchina.Codice;
import static lt.macchina.Macchina.*;

public class NumberExpr implements Expr {
    private Integer num;

    public NumberExpr(Integer num) {
        this.num = num;
    }

    public void generateCode(Codice c) {
        c.genera(PUSHIMM, num.intValue());
    }

    public String toString() {
        return num.toString();
    }
}

