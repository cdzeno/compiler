package lt.compiler.instr;

import lt.compiler.Descriptor;
import lt.compiler.expr.Expr;
import lt.macchina.Codice;
import static lt.macchina.Macchina.*;

public class AssignInstr implements Instr {
	private Descriptor descriptor;
	private Expr expr;

	public AssignInstr(Descriptor d, Expr e) {
		descriptor = d;
		expr = e;
	}

	public void generateCode(Codice c) {
		expr.generateCode(c);
		c.genera(POP, descriptor.getAddress());
	}

	public String toString() {
		return descriptor.getIdentifier() + " = " + expr.toString();
	}
}

