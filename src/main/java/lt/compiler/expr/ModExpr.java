package lt.compiler.expr;

import lt.macchina.Codice;
import static lt.macchina.Macchina.*;

public class ModExpr implements Expr {
	private Expr leftExpr;
	private Expr rightExpr;

	public ModExpr(Expr left, Expr right) {
		leftExpr = left;
		rightExpr = right;
	}

	public void generateCode(Codice c) {
		leftExpr.generateCode(c);
		rightExpr.generateCode(c);
		c.genera(POP, 1);
		c.genera(POP, 0);

		c.genera(PUSH, 0);
		c.genera(PUSH, 1);
		c.genera(PUSH, 0);
		c.genera(PUSH, 1);

		c.genera(DIV);
		c.genera(MUL);
		c.genera(SUB);
	}

	public String toString() {
		return leftExpr.toString() + " % " + rightExpr.toString();
	}
}

