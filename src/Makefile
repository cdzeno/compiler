JCC = javac
JFLEX = jflex
CUP = ../lib/java_cup.jar
CUPFLAGS = -Xlint:deprecation
CLASSPATH = -cp ".:$(CUP)"
PARSER = Parser
MAIN = Calcolatrice

all: lexer parser
	$(JCC) $(CLASSPATH) $(MAIN).java
	
lexer: expr.lex
	$(JFLEX) expr.lex

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
