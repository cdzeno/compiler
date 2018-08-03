import java.io.*;

class Lexer {
    public static void main(String args[]) throws IOException {
      Scanner scanner = new Scanner(new InputStreamReader(System.in));
      Token t;

      while ((t = scanner.getNext()).toString() != "EOF")
         System.out.println(t.toString());
    }
}