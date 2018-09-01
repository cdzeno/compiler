import java_cup.runtime.*;

%%

%cup
%unicode

IDENT                = [A-Za-z] [A-Za-z0-9]*
DEC_LETT             = [0-9]+
HEX_LETT             = 0[xX][0-9a-fA-F]+
STRING_CHARS         = [^\"\\\n\r] 
ALL_CHARS            = [^\n\r]
CR                   = \r | \n | \r\n
SPACE                = [\ \t\f]
COMMENT              = "//"{ALL_CHARS}*
MULTILINE_ISTR       = {SPACE}? "&" {SPACE}? {COMMENT}? {CR}

%class Scanner
%char
%line
%column

%{  //codice per associare la Symbol Factory
    ComplexSymbolFactory sf;
    
    public Scanner(java.io.Reader in, ComplexSymbolFactory sf) {
      this(in);
      this.sf = sf;
    }
%}

%%
"+"                   {return sf.newSymbol("SUM", ParserSym.SUM);}
"-"                   {return sf.newSymbol("SUB", ParserSym.SUB);}
"*"                   {return sf.newSymbol("MUL", ParserSym.MUL);}
"/"                   {return sf.newSymbol("DIV", ParserSym.DIV);}
"%"                   {return sf.newSymbol("MOD", ParserSym.MOD);}
"="                   {return sf.newSymbol("EQU", ParserSym.EQU);}
"("                   {return sf.newSymbol("TONDA_A", ParserSym.TONDA_A);}
")"                   {return sf.newSymbol("TONDA_C", ParserSym.TONDA_C);}
"?"                   {return sf.newSymbol("PUNTO_INT", ParserSym.PUNTO_INT);}
":"                   {return sf.newSymbol("DUE_PUNTI", ParserSym.DUE_PUNTI);}

"input"               {return sf.newSymbol("INPUT", ParserSym.INPUT);}
"output"              {return sf.newSymbol("OUTPUT", ParserSym.OUTPUT);}
"loop"                {return sf.newSymbol("LOOP", ParserSym.LOOP);}
"endLoop"             {return sf.newSymbol("ENDLOOP", ParserSym.ENDLOOP);}
"newLine"             {return sf.newSymbol("NEWLINE", ParserSym.NEWLINE);}

{IDENT}               {return sf.newSymbol("IDENT", ParserSym.IDENT, yytext());}
{DEC_LETT}            {return sf.newSymbol("LETT", ParserSym.LETT, new Integer(yytext()));}
{HEX_LETT}            {return sf.newSymbol("LETT", ParserSym.LETT, new Integer(Integer.parseInt(yytext(), 16)));}

{CR}                  {return sf.newSymbol("CR", ParserSym.CR);}
\"{STRING_CHARS}*\"   {return sf.newSymbol("STRING", ParserSym.STRING, yytext());}
{COMMENT}             { }
{SPACE}               { }
{MULTILINE_ISTR}      { }

<<EOF>>               {return sf.newSymbol("EOF", ParserSym.EOF);}
.                     {return sf.newSymbol("ERROR", ParserSym.ERROR);}
