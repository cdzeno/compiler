package lt.compiler;

import java_cup.runtime.*;

%%

%unicode

%cup
%class Scanner
%public

%char
%line

%{
    ComplexSymbolFactory sf;
    public Scanner(java.io.Reader in, ComplexSymbolFactory sf) {
        this(in);
        this.sf = sf;
    }
%}

%{
	public int currentLineNumber() {
		return yyline + 1;
	}
%}

LETTER              = [:letter:]
DEC_DIGIT           = [:digit:]
HEX_DIGIT           = [a-fA-F] | {DEC_DIGIT}
ENDL                = \r | \n | \r\n
SPACE				= [\ \t\f] | {ENDL}
STRING_LIT			= "\"" ("\\\""|[^\n\r\"])* "\""
COMMENT				= "//" .*

%%

"+"                  { return sf.newSymbol("ADD", ParserSym.ADD); }
"-"                  { return sf.newSymbol("SUB", ParserSym.SUB); }
"*"                  { return sf.newSymbol("MUL", ParserSym.MUL); }
"/"                  { return sf.newSymbol("DIV", ParserSym.DIV); }
"%"                  { return sf.newSymbol("MOD", ParserSym.MOD); }
"="                  { return sf.newSymbol("EQU", ParserSym.EQU); }
"("                  { return sf.newSymbol("OPEN_PAR", ParserSym.OPEN_PAR); }
")"                  { return sf.newSymbol("CLOSED_PAR", ParserSym.CLOSED_PAR); }
"?"                  { return sf.newSymbol("QUESTION", ParserSym.QUESTION); }
":"                  { return sf.newSymbol("COLON", ParserSym.COLON); }

"input"              { return sf.newSymbol("INPUT", ParserSym.INPUT); }
"output"             { return sf.newSymbol("OUTPUT", ParserSym.OUTPUT); }
"loop"               { return sf.newSymbol("LOOP", ParserSym.LOOP); }
"endLoop"            { return sf.newSymbol("ENDLOOP", ParserSym.ENDLOOP); }
"newLine"            { return sf.newSymbol("NEWLINE", ParserSym.NEWLINE); }

{LETTER}({LETTER}|{DEC_DIGIT})* { return sf.newSymbol("IDENT", ParserSym.IDENT, yytext()); }
{DEC_DIGIT}+         { return sf.newSymbol("NUMBER", ParserSym.NUMBER, new Integer(yytext())); }
"0x"{HEX_DIGIT}+     { return sf.newSymbol("NUMBER", ParserSym.NUMBER, Integer.parseInt(yytext().substring(2), 16)); }

{STRING_LIT}         { return sf.newSymbol("STRING", ParserSym.STRING, yytext().substring(1, yylength()-1)); }

{COMMENT}            { }
"&" ({COMMENT}|{SPACE})* {ENDL} { }

^{COMMENT}{ENDL}     { }
^{ENDL}              { }
{ENDL}               { return sf.newSymbol("ENDL", ParserSym.ENDL); }

{SPACE} { }
.                    { return sf.newSymbol("ERROR", ParserSym.error); }
<<EOF>>              { return sf.newSymbol("EOF", ParserSym.EOF); }
