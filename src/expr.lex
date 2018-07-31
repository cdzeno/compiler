
%%

%unicode

LETTERA = [:letter:]
DECIMALE = [:digit:]
HEX = [[0-9 a-f]]
FINERIGA = \r | \n | \r\n
SPAZIATURA = [ \t\f] | {FINERIGA}

%class Scanner
%function getNext
%type Token

%char
%line
%column

%{
  private int nToken;

  public int nCaratteri() {
    return yychar;
  }

  public int nRighe() {
    return yyline;
  }

  public int nToken() {
    return nToken;
  }

%}

%init{
  nToken = 0;
%init}

%%

{LETTERA}+   {nToken++;
              return new Token(TipoToken.PAROLA, yytext());}
{DECIMALE}+  {nToken++;
              return new Token(TipoToken.DECIMALE, new Integer(yytext()));}
"0x"{DECIMALE}+  {nToken++;
              return new Token(TipoToken.HEX, new Integer(yytext()));}
"."          {nToken++;
              return new Token(TipoToken.PUNTO);}
","          {nToken++;
              return new Token(TipoToken.VIRGOLA);}
":"          {nToken++;
              return new Token(TipoToken.DUE_PUNTI);}
";"          {nToken++;
              return new Token(TipoToken.PUNTO_E_VIRGOLA);}
"!"          {nToken++;
              return new Token(TipoToken.PUNTO_ESCLAMATIVO);}
"?"          {nToken++;
              return new Token(TipoToken.PUNTO_INTERROGATIVO);}
{SPAZIATURA} {}
.            {nToken++;
              return new Token(TipoToken.ALTRO, yytext());}
<<EOF>>      {nToken++;
              return new Token(TipoToken.EOF);}
