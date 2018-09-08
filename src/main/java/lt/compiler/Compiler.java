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
        // Function used to parse 'SyntaxErrorException' and pretty-print the exception:
        BufferedReader reader;
        String line = null;

        System.err.println("[-] " + e.getMessage());

        if (e.getToken().sym != ParserSym.EOF) {
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

            } catch (IOException io) {
                System.err.println("[-] Error while parsing file \"" + file + "\"");
            }
        }

        System.err.println("[-] Expected token classes are " + e.getExpectedTokens());
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("usage: java lt.compiler.Compiler <source-name> <output-name>");
            System.exit(1);
        }

        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(args[0]));
        } catch (FileNotFoundException e) {
            System.err.println("[-] Error while reading \"" + args[0] + "\"");
            System.exit(1);
        }
        
        // Initialize object used during lexical analysis and parsing phase:
        ComplexSymbolFactory sf = new ComplexSymbolFactory();
        Scanner scanner = new Scanner(in, sf);
        Parser parser = new Parser(scanner, sf);
        Symbol result = null;

        try {
            result = parser.parse();
        } catch (SyntaxErrorException e) {
            // Catch Syntax error and pretty-print it:
            printErrorLine(args[0], e);
            System.exit(1);
        } catch (Exception e) {
            System.err.println("[-] Unknown error from parsing");
            e.printStackTrace();
            System.exit(1);
        }
        
        if (result.value instanceof Program) {
            // Parse final symbol of the parsing:
            Program program = (Program) result.value;

            // Get AST and Symbol Table:
            InstrSeq instructions = program.getInstructions();
            SymbolTable symbolTable = program.getSymbolTable();

            // Initialize object for object generation:
            Codice code = new Codice(args[1]);

            // Reserve space for MOD registers (0-1) and variables (2...)
            Random rand = new Random();
            int nextFreeAddr = 2;
            code.genera(PUSHIMM, rand.nextInt());
            code.genera(PUSHIMM, rand.nextInt());

            // For-each symbol into the Symbol table reserve space for the variable:
            for (Descriptor d : symbolTable) {
                nextFreeAddr = d.assignAddress(nextFreeAddr);
                code.genera(PUSHIMM, rand.nextInt());
            }

            // Generating the code of the program:
            instructions.generateCode(code);
            code.genera(HALT);

            try {
                code.fineCodice();
            } catch (IOException e) {
                System.err.println(e.toString());
                System.exit(1);
            }
        }
    }
}

