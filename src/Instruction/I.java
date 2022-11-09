package Instruction;

import Instruction.abstact.RD;

public class I extends RD {
    public int rs1;
    public int imm;
    public int funct6;
    public int funct3;
    public int imm110;
    public int imm12;

    public I(int instr) {
        super(instr);
        rd = (instr >> 7) & 0x1F;
        funct3 = (instr >> 12) & 0x7 ;
        rs1 = (instr >> 15) & 0x1F;
        imm = (instr >> 20) & 0xFFF | (instr & 0x80000000) >> 20;
        funct6 = (instr >> 26) & 0x3F;
        imm110 = (instr >> 20) & 0xFFF;
        imm12 = (instr >> 31) & 0x01;
    }
}

