package lt.compiler;

import lt.macchina.*;
import static lt.macchina.Macchina.*;

interface Expr {
	void generateCode(Codice c);
}

class NumberExpr implements Expr {
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

class IdExpr implements Expr {
	private Descriptor descriptor;

	public IdExpr(Descriptor d) {
		descriptor = d;
	}

	public void generateCode(Codice c) {
		//TODO
	}

	public String toString() {
		return descriptor.getIdentifier();
	}
}

class AddExpr implements Expr {
	private Expr leftExpr;
	private Expr rightExpr;

	public AddExpr(Expr left, Expr right) {
		leftExpr = left;
		rightExpr = right;
	}

	public void generateCode(Codice c) {
		leftExpr.generateCode(c);
		rightExpr.generateCode(c);
		c.genera(ADD);
	}

	public String toString() {
		return leftExpr.toString() + " + " + rightExpr.toString();
	}
}

class SubExpr implements Expr {
	private Expr leftExpr;
	private Expr rightExpr;

	public SubExpr(Expr left, Expr right) {
		leftExpr = left;
		rightExpr = right;
	}

	public void generateCode(Codice c) {
		leftExpr.generateCode(c);
		rightExpr.generateCode(c);
		c.genera(SUB);
	}

	public String toString() {
		return leftExpr.toString() + " - " + rightExpr.toString();
	}
}

class MulExpr implements Expr {
	private Expr leftExpr;
	private Expr rightExpr;

	public MulExpr(Expr left, Expr right) {
		leftExpr = left;
		rightExpr = right;
	}

	public void generateCode(Codice c) {
		leftExpr.generateCode(c);
		rightExpr.generateCode(c);
		c.genera(MUL);
	}

	public String toString() {
		return leftExpr.toString() + " * " + rightExpr.toString();
	}
}

class DivExpr implements Expr {
	private Expr leftExpr;
	private Expr rightExpr;

	public DivExpr(Expr left, Expr right) {
		leftExpr = left;
		rightExpr = right;
	}

	public void generateCode(Codice c) {
		leftExpr.generateCode(c);
		rightExpr.generateCode(c);
		c.genera(DIV);
	}

	public String toString() {
		return leftExpr.toString() + " / " + rightExpr.toString();
	}
}

class ModExpr implements Expr {
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

class UnaryMinusExpr implements Expr {
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

class AssignExpr implements Expr {
	private Descriptor descriptor;
	private Expr expr;

	public AssignExpr(Descriptor d, Expr e) {
		descriptor = d;
		expr = e;
	}

	public void generateCode(Codice c) {
		//TODO
	}

	public String toString() {
		return descriptor.getIdentifier() + " = " + expr.toString();
	}
}

class TernaryExpr implements Expr {
	private Expr condition;
	private Expr trueExpr;
	private Expr falseExpr;

	public TernaryExpr(Expr c, Expr t, Expr f) {
		condition = c;
		trueExpr = t;
		falseExpr = f;
	}

	public void generateCode(Codice c) {
		condition.generateCode(c);
		int jumpFalse = c.generaParziale(JZERO);

		trueExpr.generateCode(c);
		int jumpEnd = c.generaParziale(JUMP);

		c.completaIstruzione(jumpFalse, c.indirizzoProssimaIstruzione());
		falseExpr.generateCode(c);

		c.completaIstruzione(jumpEnd, c.indirizzoProssimaIstruzione());
	}

	public String toString() {
		return condition.toString() + " ? " + trueExpr.toString() + " : " + falseExpr.toString();
	}
}

class InputExpr implements Expr {
	private String prompt;

	public InputExpr(String p) {
		prompt = p;
	}

	public InputExpr() {
		this("");
	}

	public void generateCode(Codice c) {
		c.genera(INPUT);
	}

	public String toString() {
		return "input \"" + prompt + "\"";
	}
}

