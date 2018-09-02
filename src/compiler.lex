// import java_cup.runtime.*;

package lt.compiler;

import static lt.compiler.TipoToken.*;

%%

// %cup
%unicode
%class Scanner
%function getNext
%type Token
%public

%char
%line
%column

LETTER               = [:letter:]
DEC_DIGIT            = [:digit:]
HEX_DIGIT            = [a-fA-F] | {DEC_DIGIT}
ENDL                 = \r | \n | \r\n
SPACE                = [\ \t\f] | {ENDL}

%xstate MULTILINE

%%

"+"                  { return new Token(ADD, "PIU"); }
"-"                  { return new Token(SUB, "MENO"); }
"*"                  { return new Token(MUL, "PER"); }
"/"                  { return new Token(DIV, "DIV"); }
"%"                  { return new Token(MOD, "MOD"); }
"="                  { return new Token(EQU, "EQU"); }
"("                  { return new Token(OPEN_PAR, "PAR_A"); }
")"                  { return new Token(CLOSED_PAR, "PAR_C"); }
"?"                  { return new Token(QUESTION, "DOMANDA"); }
":"                  { return new Token(TWO_DOTS, "DUE_PUNTI"); }

"input"              { return new Token(INPUT, "IN"); }
"output"             { return new Token(OUTPUT, "OUT"); }
"loop"               { return new Token(LOOP, "LOOP"); }
"endLoop"            { return new Token(ENDLOOP, "ENDLOOP"); }
"newLine"            { return new Token(NEWLINE, "NEWLINE"); }

{LETTER}({LETTER}|{DEC_DIGIT})* { return new Token(IDENT, "IDENT"); }
{DEC_DIGIT}+         { return new Token(DEC_NUM, "DEC_NUM"); }
"0"[xX]{HEX_DIGIT}+     { return new Token(HEX_NUM, "HEX_NUM"); }

{ENDL}               { return new Token(ENDL, "ENDL"); }
"\"" .* "\""         { return new Token(STRING, "STRING"); }

"&"                  { yybegin(MULTILINE); }
<MULTILINE> {ENDL}   { yybegin(YYINITIAL); }

"//" .*              { return new Token(COMMENT, "COMMENT"); }
<MULTILINE> "//" .*  { }
<YYINITIAL, MULTILINE> {SPACE} { }

.                    { return new Token(ERROR, "ERR"); }

<<EOF>>              { return new Token(EOF, "EOF"); }
