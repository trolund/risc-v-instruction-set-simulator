package Instruction;

import Instruction.abstact.Instruction;
import Instruction.abstact.RD;

public class UJ extends RD {

    public int imm;

    public UJ(int instr) {
        super(instr);
        imm = instr & 0xFF000; //imm[19:12]
        imm |= (instr >> 9) & 0x800; //imm[11]
        imm |= (instr >> 20) & 0x7FE; //imm[10:1]
        imm |= (instr & 0x80000000) >> 11; //imm[20]
    }
}
