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
        // First, evaluate the expression (if given) and leave
        // the result onto the stack
        if (expr != null) {
            expr.generateCode(c);
        }

        // Print the string (char-by-char)
        for (char ch : output.toCharArray()) {
            c.genera(PUSHIMM, ch);
            c.genera(OUTPUTCH);
        }

        // Finally, print out the result of the expression (if given)
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

