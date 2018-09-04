package lt.compiler;

import lt.macchina.Codice;
import static lt.macchina.Macchina.*;

interface Instr {
	void generateCode(Codice c);
}

class AssignInstr implements Instr {
	private Descriptor descriptor;
	private Expr expr;

	public AssignInstr(Descriptor d, Expr e) {
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

class OutputInstr implements Instr {
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
		for (char ch : output.toCharArray()) {
			c.genera(PUSHIMM, ch);
			c.genera(OUTPUTCH);
		}
		
		if (expr != null) {
			expr.generateCode(c);
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

class LoopInstr implements Instr {
	private Expr cond;
	private InstrSeq body;

	public LoopInstr(Expr c, InstrSeq s) {
		cond = c;
		body = s;
	}

	public void generateCode(Codice c) {
		//TODO
	}

	public String toString() {
		return "loop " + cond.toString() + "\n" + body + "\nendLoop";
	}
}
