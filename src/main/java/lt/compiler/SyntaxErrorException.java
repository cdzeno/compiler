package lt.compiler;

import java.util.List;
import java_cup.runtime.Symbol;

public class SyntaxErrorException extends RuntimeException {

	private Symbol token;
	private int line;
	private int column;
	private List<String> expectedTokens;

	public SyntaxErrorException(Symbol token, int line, int column, List<String> expectedTokens) {
		super("Syntax error at line " + line + ", column " + column + ": unexpected " + token.toString().substring(8) + " token");
		this.token = token;
		this.line = line;
		this.column = column;
		this.expectedTokens = expectedTokens;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public List<String> getExpectedTokens() {
		return expectedTokens;
	}
}
