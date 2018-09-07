package lt.compiler;

import java.io.*;
import java.util.Random;
import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import lt.compiler.instr.InstrSeq;
import lt.macchina.*;
import static lt.macchina.Macchina.*;

public class Compiler {

    private static void printErrorLine(String file, SyntaxErrorException e) {
        BufferedReader reader;
        String line = null;

        System.err.println("[-] " + e.getMessage());
        try {
            reader = new BufferedReader(new FileReader(file));
            for (int i = 0; i < e.getLine(); ++i)
                line = reader.readLine();

            System.err.println("      " + line);

            System.err.print("      ");
            for (int i = 1; i < e.getColumn(); ++i) {
                if (line.charAt(i-1) == '\t')
                    System.err.print("\t");
                else
                    System.err.print(" ");
            }
            System.err.println("^");

            System.err.println("[-] Expected token classes are " + e.getExpectedTokens());

        } catch (IOException io) {
            System.err.println("[-] Error while parsing file \"" + file + "\"");
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("usage: java lt.compiler.Compiler <source-name> <output-name>");
            return;
        }

        BufferedReader in;

        try {
            in = new BufferedReader(new FileReader(args[0]));
        } catch (FileNotFoundException e) {
            System.err.println("[-] Error while reading \"" + args[0] + "\"");
            return;
        }
        
        ComplexSymbolFactory sf = new ComplexSymbolFactory();
        Scanner scanner = new Scanner(in, sf);
        Parser parser = new Parser(scanner, sf);
        Symbol result;

        try {
            result = parser.parse();
        } catch (SyntaxErrorException e) {
            printErrorLine(args[0], e);
            return;
        } catch (Exception e) {
            System.err.println("[-] Unknown error from parsing");
            e.printStackTrace();
            return;
        }
        
        if (result.value instanceof Program) {
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

            instructions.generateCode(code);
            code.genera(HALT);

            try {
                code.fineCodice();
            } catch (IOException e) {
                System.err.println(e.toString());
            }
        }
    }
}

