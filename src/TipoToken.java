// Tipo enumerativo per i possibili token

enum TipoToken {  
  //tipo enumerativo per le costanti per la rappresentazione dei token
  IDENT, DEC_LETT, HEX_LETT,
  SUM, MINUS, MUL, DIV, MOD, EQU, 
  TONDA_A, TONDA_C, 
  PUNTO_INT, DUE_PUNTI,
  INPUT, OUTPUT, STRINGA,
  LOOP, ENDLOOP,
  COMMENTO, NEWLINE, CR,
  ERROR,EOF;
}
