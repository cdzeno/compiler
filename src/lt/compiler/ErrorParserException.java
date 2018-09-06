package lt.compiler;

import java_cup.runtime.Symbol;

public class ErrorParserException extends RuntimeException {
	
	private Symbol token;
	private int line;
	private int column;

	public ErrorParserException(Symbol token, int line, int column){
		super();
		this.token = token;
		this.line = line;
		this.column = column;
	}

	public String toString(){
		return "Syntax error (unexpected " + this.token.toString().substring(8) +") at line " + this.line + ", column " + this.column;
	}

	public int getLine(){
		return this.line;
	}

	public int getColumn(){
		return this.column;
	}
	
}