package Instruction;

import Instruction.abstact.Instruction;

public class S extends Instruction {
    public int rs1;
    public int rs2;
    public int imm;
    public int funct3;

    public S(int instr) {
        super(instr);
        imm =  (instr  >> 7) & 0x1F;
        funct3 = (instr >> 12) & 0x7;
        rs1 = (instr >> 15) & 0x1F;
        rs2 = (instr >> 20) & 0x1F;
        imm |= (instr >> 20) & 0x7E0;

    }
}


