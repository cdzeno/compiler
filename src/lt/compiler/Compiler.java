package lt.compiler;

import java.io.*;
import java.util.Random;
import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import lt.macchina.*;
import static lt.macchina.Macchina.*;

public class Compiler {
	public static void main(String[] args) throws java.io.IOException {
		if (args.length < 1) {
			System.err.println("usage: Compiler <source-name>");
			return;
		}

		BufferedReader in = new BufferedReader(new FileReader(args[0]));

		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(in, sf);
		Parser parser = new Parser(scanner, sf);

		try {
			Symbol result = parser.parse();
			Program program = (Program) result.value;
			InstrSeq instructions = program.getInstructions();

			SymbolTable symbolTable = program.getSymbolTable();
			Codice code = new Codice("eseguibile");

			// Reserve space for MOD registers (0-1) and variables (2...)
			Random rand = new Random();
			int nextFreeAddr = 2;
			code.genera(PUSHIMM, rand.nextInt());
			code.genera(PUSHIMM, rand.nextInt());
			for (Descriptor d : symbolTable) {
				nextFreeAddr = d.assignAddress(nextFreeAddr);
				code.genera(PUSHIMM, rand.nextInt());
			}
			/*code.genera(PUSHIMM, nextFreeAddr);
			code.genera(MOVESP);*/

			instructions.generateCode(code);

			code.genera(HALT);
			code.fineCodice();
		} catch (Exception e)	{
			e.printStackTrace();
		}
	}
}

