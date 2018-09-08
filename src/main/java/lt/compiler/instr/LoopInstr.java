package lt.compiler.instr;

import lt.compiler.expr.Expr;
import lt.macchina.Codice;
import static lt.macchina.Macchina.*;

public class LoopInstr implements Instr {
    private Expr cond;
    private InstrSeq body;

    public LoopInstr(Expr c, InstrSeq s) {
        cond = c;
        body = s;
    }

    public void generateCode(Codice c) {
        // Save address of loop begin
        int begin = c.indirizzoProssimaIstruzione();

        // Generate code for the loop condition
        cond.generateCode(c);

        // Jump out of the loop if condition evaluates to false
        int jumpEnd = c.generaParziale(JZERO);

        // Generate code for the body of the loop
        body.generateCode(c);

        // Unconditional jump to the beginning of the loop (repeat)
        c.genera(JUMP, begin);

        // Complete the jump out of the loop (false condition),
        // now that the end of the loop code is known
        c.completaIstruzione(jumpEnd, c.indirizzoProssimaIstruzione());
    }

    public String toString() {
        return "loop " + cond.toString() + "\n" + body + "\nendLoop";
    }
}

