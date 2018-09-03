JAVAC = javac
JAVA = java
PCKG = lt/compiler
SRC = src/$(PCKG)
OUT = bin
JFLEX = jflex -d $(SRC)
CUP = java -jar lib/java-cup-11b.jar -expect 1 -destdir $(SRC)
# CUPFLAGS = -Xlint:deprecation
CLASSPATH = -cp ".:src/:$(OUT)/:lib/java-cup-11b-runtime.jar"
LEXER = Scanner
LEXERTEST = LexerTest
PARSER = Parser
EXAMPLES = tests/test_*.s

lexer: src/*.lex
	@echo "[*] Compiling lexer:"
	@[ -f $(SRC)/$(LEXER).java ] && $(RM) $(SRC)/$(LEXER).java
	$(JFLEX) $<

testLexer: src/$(LEXERTEST).java $(SRC)/$(LEXER).java
	@echo "[*] Testing lexer:"
	@[ -d $(OUT) ] || mkdir $(OUT)
	$(JAVAC) $(CLASSPATH) -d $(OUT) $<
	@for f in $(EXAMPLES) ; do \
		echo $$f ; \
		$(JAVA) $(CLASSPATH) $(LEXERTEST) < $$f ; \
		echo "\n" ; \
	done
	@echo "[*] Test finished"

parser: src/*.cup
	@echo "[*] Compiling parser:"
	$(CUP) $<

clean:
	@echo "[+] Cleaning..."
	$(RM) $(LEXER).java $(LEXER).java~
	$(RM) $(SRC)/$(PARSER).java $(SRC)/$(PARSER)Sym.java
	$(RM) -r $(OUT)
	@echo "[+] Cleaning done"
