package Instruction.abstact;

public abstract class RD extends Instruction {
    public int rd;

    public RD(int instr) {
        super(instr);
        rd = (instr >> 7) & 0x1F;
    }
}
