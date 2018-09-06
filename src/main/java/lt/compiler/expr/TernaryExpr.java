package lt.compiler.expr;

import lt.macchina.Codice;
import static lt.macchina.Macchina.*;

public class TernaryExpr implements Expr {
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

