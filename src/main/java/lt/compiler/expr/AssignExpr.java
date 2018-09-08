package lt.compiler.expr;

import lt.compiler.Descriptor;
import lt.compiler.instr.AssignInstr;
import lt.macchina.Codice;
import static lt.macchina.Macchina.*;

public class AssignExpr implements Expr {
    private Descriptor descriptor;
    private Expr expr;

    public AssignExpr(Descriptor d, Expr e) {
        descriptor = d;
        expr = e;
    }

    public void generateCode(Codice c) {
        // Generate code as if it were an assignment instruction
        new AssignInstr(descriptor, expr).generateCode(c);

        // but leave the assigned value on the top of the stack
        new IdExpr(descriptor).generateCode(c);
    }

    public String toString() {
        return descriptor.getIdentifier() + " = " + expr.toString();
    }
}

