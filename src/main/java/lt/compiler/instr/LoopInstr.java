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
		int begin = c.indirizzoProssimaIstruzione();
		cond.generateCode(c);
		int jumpEnd = c.generaParziale(JZERO);

		body.generateCode(c);
		c.genera(JUMP, begin);

		c.completaIstruzione(jumpEnd, c.indirizzoProssimaIstruzione());
	}

	public String toString() {
		return "loop " + cond.toString() + "\n" + body + "\nendLoop";
	}
}
