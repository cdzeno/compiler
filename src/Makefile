JCC = javac
JFLEX = jflex
CUP = ../lib/java_cup.jar
CUPFLAGS = -Xlint:deprecation
CLASSPATH = -cp ".:$(CUP)"
SCANNER = Scanner
LEXER = Lexer
PARSER = Parser
MAIN = Compiler

all: lexer parser
	$(JCC) $(CLASSPATH) $(MAIN).java
	
lexer: expr.lex
	$(JFLEX) expr.lex

testLexer: $(SCANNER).java $(LEXER).java
	$(JCC) -cp . $(LEXER).java 

parser: expr.cup
	java -jar $(CUP) expr.cup

test: $(MAIN).class
	@echo "[*] Testing:"
	java $(CLASSPATH) $(MAIN)

clean:
	$(RM) Scanner.java
	$(RM) $(PARSER).java
	$(RM) $(PARSER)Sym.java
	$(RM) *.class
	@echo "[+] cleaning...done"
