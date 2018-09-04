package lt.compiler;

import lt.macchina.Codice;
import static lt.macchina.Macchina.*;

class WriteInstr implements Instr {

	private String message;

	public WriteInstr(String message) {
		this.message = message;
	}

	public void generateCode(Codice c) {
		for(char c: message.toCharArray()) {
			c.genera(PUSHIMM, c);
			c.genera(OUTPUTCH);
		}
	}
}

