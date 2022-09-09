package Instruction;

import Instruction.abstact.RD;

public class R extends RD {

    public int funct3;
    public int rs1;
    public int rs2;
    public int funct7;

    public R(int instr) {
        super(instr);
        rd = (instr >> 7) & 0x1F;
        funct3 = (instr >> 12) & 0x7;
        rs1 = (instr >> 15) & 0x1F;
        rs2 = (instr >> 20) & 0x1F;
        funct7 = (instr>> 25) & 0x7F;
    }

}
