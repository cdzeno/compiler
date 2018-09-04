package lt.compiler;

import java.io.*;
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
			/*int nextFreeAddr = 2; // first two registers reserved for MOD
			for(Descriptor d : symbolTable)
				nextFreeAddr = d.setAddress(nextFreeAddr);*/

			Codice code = new Codice("eseguibile");

			instructions.generateCode(code);
			
			code.genera(HALT);
			code.fineCodice();
		} catch (Exception e)	{
			e.printStackTrace();
		}
	}
}

