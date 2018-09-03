JAVAC = javac
JAVA = java
PCKG = lt/compiler
SRC = src/$(PCKG)
OUT = bin
JFLEX = jflex -d $(SRC)
# CUP = lib/java_cup.jar
# CUPFLAGS = -Xlint:deprecation
CLASSPATH = -cp ".:src/:$(OUT)/:$(CUP)"
SCANNER = Scanner
LEXER = Lexer
PARSER = Parser
MAIN = Compiler
EXAMPLES = tests/test_*.s
	
lexer: src/compiler.lex
	@[ -f $(SRC)/$(SCANNER).java ] && $(RM) $(SRC)/$(SCANNER).java
	$(JFLEX) $<

testLexer: src/$(LEXER).java $(SRC)/$(SCANNER).java
	@echo "[*] Testing lexer:"
	@[ -d $(OUT) ] || mkdir $(OUT)
	$(JAVAC) $(CLASSPATH) -d $(OUT) $<
	@for f in $(EXAMPLES) ; do \
		echo $$f ; \
		$(JAVA) $(CLASSPATH) $(LEXER) < $$f ; \
		echo "\n" ; \
	done

clean:
	@echo "[+] Cleaning..."
	$(RM) $(SCANNER).java $(SCANNER).java~
	$(RM) $(SRC)/$(PARSER).java
	$(RM) $(SRC)/$(PARSER)Sym.java
	$(RM) -r $(OUT)
	@echo "[+] Cleaning done"
