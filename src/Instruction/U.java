package Instruction;

import Instruction.abstact.RD;

public class U extends RD {
    public int imm;

    public U(int instr) {
        super(instr);
        rd = (instr >> 7) & 0x1F;
        imm = instr & 0x7FFFF000 | (instr & 0x80000000);
    }
}


