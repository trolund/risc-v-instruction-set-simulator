package Instruction.abstact;

/**
 *  RD is an abstract base class that extracts the rd field used by types R, I, U and UJ
 */
public abstract class RD extends Instruction {
    public int rd;

    public RD(int instr) {
        super(instr);
        rd = (instr >> 7) & 0x1F;
    }
}
