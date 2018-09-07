package lt.compiler.instr;

import lt.macchina.Codice;

public class InstrSeq {
    private InstrSeq nextInstructions;
    private Instr instruction;

    public InstrSeq(Instr i) {
        instruction = i;
    }

    public InstrSeq(InstrSeq s, Instr i) {
        nextInstructions = s;
        instruction = i;
    }

    public void generateCode(Codice c) {
        if (nextInstructions != null)
            nextInstructions.generateCode(c);
        instruction.generateCode(c);
    }

    public String toString() {
        String s = "";
        if (nextInstructions != null)
            s = nextInstructions.toString();
        return s + instruction.toString();
    }
}
