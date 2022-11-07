package Instruction;

public class SB extends S {

    public SB(int instr) {
        super(instr);
        imm = (instr & 0xF00) >> 7;  //imm[4:1]
        imm |= (instr & 0x80) << 4 ; //imm[11]
        imm |= (instr & 0x7E000080) >> 20; //imm[10:5]
        imm |= (instr & 0x80000000) >> 19; //imm[12]
    }
}
