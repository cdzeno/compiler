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

"+"                  { return new Token(ADD); }
"-"                  { return new Token(SUB); }
"*"                  { return new Token(MUL); }
"/"                  { return new Token(DIV); }
"%"                  { return new Token(MOD); }
"="                  { return new Token(EQU); }
"("                  { return new Token(OPEN_PAR); }
")"                  { return new Token(CLOSED_PAR); }
"?"                  { return new Token(QUESTION); }
":"                  { return new Token(COLON); }

"input"              { return new Token(INPUT); }
"output"             { return new Token(OUTPUT); }
"loop"               { return new Token(LOOP); }
"endLoop"            { return new Token(ENDLOOP); }
"newLine"            { return new Token(NEWLINE); }

{LETTER}({LETTER}|{DEC_DIGIT})* { return new Token(IDENT, yytext()); }
{DEC_DIGIT}+         { return new Token(INTEGER, new Integer(yytext())); }
"0x"{HEX_DIGIT}+     { return new Token(INTEGER, Integer.parseInt(yytext().substring(2), 16)); }

{ENDL}               { return new Token(ENDL); }
"\"" .* "\""         { return new Token(STRING, yytext().substring(1, yylength()-1)); }

"&"                  { yybegin(MULTILINE); }
<MULTILINE> {ENDL}   { yybegin(YYINITIAL); }

"//" .*              { }
<MULTILINE> "//" .*  { }
<YYINITIAL, MULTILINE> {SPACE} { }

.                    { return new Token(ERROR, yytext()); }

<<EOF>>              { return new Token(EOF); }
