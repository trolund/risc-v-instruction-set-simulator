package Instruction;

import Instruction.abstact.RD;

public class U extends RD {
    public int imm;

    public U(int instr) {
        super(instr);
        imm = instr & 0x7FFFF000;
    }
}


