package lt.compiler;

import java.io.*;
import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;

public class LexerTest {
    public static void main(String args[]) throws IOException {
        ComplexSymbolFactory sf = new ComplexSymbolFactory();
        Scanner scanner = new Scanner(new InputStreamReader(System.in), sf);
        Symbol token;

        while ((token = scanner.next_token()).sym != ParserSym.EOF) {
            System.out.print(token.toString().substring(8) + " ");
            if (token.sym == ParserSym.ENDL)
                System.out.println();
        }
    }
}
