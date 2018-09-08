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
        // Save address of loop begin:
        int begin = c.indirizzoProssimaIstruzione();

        // Generate code for the loop-condition:
        cond.generateCode(c);

        // Save address of the end of code-codition:
        int jumpEnd = c.generaParziale(JZERO);

        // Generate code for the body of loop instruction:
        body.generateCode(c);

        // Unconditional jump to the end of body loop:
        c.genera(JUMP, begin);

        // Now that I know the address of the next istruction of the body
        // I can complete the initial jump instruction for false loop condition:
        c.completaIstruzione(jumpEnd, c.indirizzoProssimaIstruzione());
    }

    public String toString() {
        return "loop " + cond.toString() + "\n" + body + "\nendLoop";
    }
}
