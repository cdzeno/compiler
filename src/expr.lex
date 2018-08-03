
%%

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
%function getNext
%type Token

%char
%line
%column

%%
"+"                   {return new Token(TipoToken.SUM);}
"-"                   {return new Token(TipoToken.MINUS);}
"*"                   {return new Token(TipoToken.MUL);}
"/"                   {return new Token(TipoToken.DIV);}
"%"                   {return new Token(TipoToken.MOD);}
"="                   {return new Token(TipoToken.EQU);}
"("                   {return new Token(TipoToken.TONDA_A);}
")"                   {return new Token(TipoToken.TONDA_C);}
"?"                   {return new Token(TipoToken.PUNTO_INT);}
":"                   {return new Token(TipoToken.DUE_PUNTI);}

"input"               {return new Token(TipoToken.INPUT);}
"output"              {return new Token(TipoToken.OUTPUT);}
"loop"                {return new Token(TipoToken.LOOP);}
"endLoop"             {return new Token(TipoToken.ENDLOOP);}
"newLine"             {return new Token(TipoToken.NEWLINE);}

{IDENT}               {return new Token(TipoToken.IDENT, yytext());}
{DEC_LETT}            {return new Token(TipoToken.DEC_LETT, Integer.parseInt(yytext()));}
{HEX_LETT}            {return new Token(TipoToken.HEX_LETT, Integer.parseInt(yytext(), 16));}

{CR}                  {return new Token(TipoToken.CR);}
\"{STRING_CHARS}*\"   {return new Token(TipoToken.STRINGA, yytext());}
{COMMENT}             { }
{SPACE}               { }
{MULTILINE_ISTR}      { }

<<EOF>>               {return new Token(TipoToken.EOF);}
.                     {return new Token(TipoToken.ERROR, yytext());}
