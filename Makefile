CLASSPATH = -cp "src/:$(OUT)/:lib/java-cup-11b-runtime.jar:lib/ltMacchina.jar"
JAVAC = javac $(CLASSPATH)
JAVA = java $(CLASSPATH)
PCKG = lt/compiler
SRC = src/$(PCKG)
OUT = bin
JFLEX = jflex -d $(SRC)
CUP = java -jar lib/java-cup-11b.jar -destdir $(SRC)
# CUPFLAGS = -Xlint:deprecation
LEXER = Scanner
LEXERTEST = LexerTest
PARSER = Parser
COMPILER = Compiler
EXAMPLES = examples/test_*.mylang

lexer: src/*.lex
	@echo "[*] Compiling lexer:"
	$(JFLEX) $<

testLexer: src/$(LEXERTEST).java lexer parser $(SRC)/$(LEXER).java
	@echo "[*] Testing lexer:"
	@[ -d $(OUT) ] || mkdir $(OUT)
	$(JAVAC) -d $(OUT) $<
	@for f in $(EXAMPLES) ; do \
		echo $$f ; \
		$(JAVA) $(LEXERTEST) < $$f ; \
		echo "\n" ; \
	done
	@echo "[*] Test finished"

parser: src/*.cup
	@echo "[*] Compiling parser:"
	$(CUP) $<

compiler: $(SRC)/$(COMPILER).java lexer parser
	@echo "[*] Generating compiler bytecode:"
	@[ -d $(OUT) ] || mkdir $(OUT)
	$(JAVAC) -d $(OUT) $<

clean:
	@echo "[+] Cleaning..."
	$(RM) $(SRC)/$(LEXER).java $(SRC)/$(LEXER).java~
	$(RM) $(SRC)/$(PARSER).java $(SRC)/$(PARSER)Sym.java
	$(RM) -r $(OUT)
	@echo "[+] Cleaning done"
