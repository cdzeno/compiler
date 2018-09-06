package lt.compiler.expr;

import lt.compiler.instr.OutputInstr;
import lt.macchina.Codice;
import static lt.macchina.Macchina.*;

public class InputExpr implements Expr {
	private String prompt;

	public InputExpr(String p) {
		prompt = p;
	}

	public InputExpr() {
		this("");
	}

	public void generateCode(Codice c) {
		new OutputInstr(prompt).generateCode(c);
		c.genera(INPUT);
	}

	public String toString() {
		return "input \"" + prompt + "\"";
	}
}

