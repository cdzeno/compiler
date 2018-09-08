package lt.compiler;

import lt.compiler.instr.InstrSeq;

public class Program {
    private InstrSeq instructions;
    private SymbolTable symbolTable;

    public Program(InstrSeq s, SymbolTable table) {
        instructions = s;
        symbolTable = table;
    }

    public InstrSeq getInstructions() {
        return instructions;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }
}

