package lt.compiler;

import java.io.*;
import java.util.Random;
import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import lt.macchina.*;
import static lt.macchina.Macchina.*;

public class Compiler {

	public static boolean checkResult(Symbol result){

		if (result.value instanceof Program){
			return true;
		}else{
			return false;
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("usage: java Compiler <source-name> <output-name>");
			return;
		}

		System.out.println("[+] Starting compiling " + args[0]);
		BufferedReader in;

		try{
			in = new BufferedReader(new FileReader(args[0]));
		}catch (FileNotFoundException e) {
			System.err.println("[-] Error during reading " + args[0]);
			return;
		}
		
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(in, sf);
		Parser parser = new Parser(scanner, sf);
		Symbol result;

		try{
			result = parser.parse();
		}catch(Exception e){
			return;
		}
		
		if (checkResult(result)){
			System.out.println("[+] Parsing ends without error");
			
			Program program = (Program) result.value;
			InstrSeq instructions = program.getInstructions();
			SymbolTable symbolTable = program.getSymbolTable();

			System.out.print("[+] Generating code");
			Codice code = new Codice(args[1]);

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

			try{
				code.fineCodice();
			}catch (IOException e) {
				System.err.println("...ERROR");
				return;
			}

			System.out.println("...DONE");
			System.out.println("[+] End of compilation, everything goes well.");
		}else{
			System.err.println("[-] Error during compilation...");
		}
	}
}

