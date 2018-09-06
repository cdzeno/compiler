package lt.compiler;

import java.util.List;
import java_cup.runtime.Symbol;

public class SyntaxErrorException extends RuntimeException {

	private Symbol token;
	private int line;
	private int column;
	private List<String> expectedTokens;

	public static String createMessage(Symbol token, int line, int column, List<String> expectedTokens) {
		StringBuilder sb = new StringBuilder();
		sb.append("Syntax error at line ");
		sb.append(line);
		sb.append(", column ");
		sb.append(column);
		sb.append(": unexpected ");
		if (token.sym != ParserSym.error) {
			sb.append(token.toString().substring(8));
		} else {
			sb.append('"');
			sb.append(token.value);
			sb.append('"');
		}
		sb.append(" token");
		return sb.toString();
	}

	public SyntaxErrorException(Symbol token, int line, int column, List<String> expectedTokens) {
		super(createMessage(token, line, column, expectedTokens));

		this.token = token;
		this.line = line;
		this.column = column;
		this.expectedTokens = expectedTokens;
	}

	public Symbol getToken() {
		return token;
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
