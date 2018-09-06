package lt.compiler.expr;

import lt.compiler.Descriptor;
import lt.macchina.Codice;
import static lt.macchina.Macchina.*;

public class IdExpr implements Expr {
	private Descriptor descriptor;

	public IdExpr(Descriptor d) {
		descriptor = d;
	}

	public void generateCode(Codice c) {
		c.genera(PUSH, descriptor.getAddress());
	}

	public String toString() {
		return descriptor.getIdentifier();
	}
}

