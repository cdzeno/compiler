// Tipo enumerativo per i possibili token

package lt.compiler;

public enum TipoToken {  
    //tipo enumerativo per le costanti per la rappresentazione dei token
    IDENT, DEC_NUM, HEX_NUM,
    ADD, SUB, MUL, DIV, MOD, EQU, 
    CLOSED_PAR, OPEN_PAR,
    QUESTION, TWO_DOTS,
    INPUT, OUTPUT, STRING,
    LOOP, ENDLOOP,
    COMMENT, NEWLINE, ENDL,
    ERROR, EOF;
}
