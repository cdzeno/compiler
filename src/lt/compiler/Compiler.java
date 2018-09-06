package lt.compiler;

import java.io.*;
import java.util.Random;
import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import lt.macchina.*;
import static lt.macchina.Macchina.*;

public class Compiler {

	public static String parseLineError(String file, int line){
		// La colonna sarebbe superflua ma la uso per centrare la stringa
		// e non stampare caratteri troppo lontani
		BufferedReader reader;

		try{
			reader = new BufferedReader(new FileReader(file));
			String lineString = null;
			String parsedLine = null;
			int counter = 1;

			do {
				lineString = reader.readLine();

				if (counter == line){
					parsedLine = lineString;
				}

				counter++;

			}while(lineString != null);

			return parsedLine.trim();

		}catch(IOException io){
			System.err.println("[-] Error during parsing file...");
			return null;
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("usage: java Compiler <source-name> <output-name>");
			return;
		}

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
		}catch(ParserErrorException p){
			System.err.println("[-] " + p.toString());
			System.err.format("%10s%20s\n", "\t", parseLineError(args[0], p.getLine()) );
			return;
		}catch(Exception e){
			System.err.println("[-] Unknown error from parsing");
			return;
		}
		
		if (result.value instanceof Program){
			
			Program program = (Program) result.value;
			InstrSeq instructions = program.getInstructions();
			SymbolTable symbolTable = program.getSymbolTable();

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
				System.err.println(e.toString());
			}

		}
	}
}

