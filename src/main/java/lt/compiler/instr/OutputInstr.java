package lt.compiler.instr;

import lt.compiler.expr.Expr;
import lt.macchina.Codice;
import static lt.macchina.Macchina.*;

public class OutputInstr implements Instr {
    private String output;
    private Expr expr;

    public OutputInstr(String o, Expr e) {
        output = o;
        expr = e;
    }

    public OutputInstr(String o) {
        this(o, null);
    }

    public OutputInstr(Expr e) {
        this("", e);
    }

    public void generateCode(Codice c) {
        if (expr != null) {
            expr.generateCode(c);
        }

        for (char ch : output.toCharArray()) {
            c.genera(PUSHIMM, ch);
            c.genera(OUTPUTCH);
        }
        
        if (expr != null) {
            c.genera(OUTPUT);
        }
    }

    public String toString() {
        String s = "output \"" + output + "\"";
        if (expr != null)
            s += " " + expr.toString();
        return s;
    }
}

