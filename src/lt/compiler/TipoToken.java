// Tipo enumerativo per i possibili token

package lt.compiler;

public enum TipoToken {  
    //tipo enumerativo per le costanti per la rappresentazione dei token
    IDENT, INTEGER, STRING,
    ADD, SUB, MUL, DIV, MOD, EQU,
    CLOSED_PAR, OPEN_PAR,
    QUESTION, COLON,
    INPUT, OUTPUT,
    LOOP, ENDLOOP,
    COMMENT, NEWLINE, ENDL,
    ERROR, EOF
}
