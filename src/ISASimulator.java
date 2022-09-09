import Instruction.R;
import Instruction.I;
import jdk.jshell.spi.ExecutionControl;

public class ISASimulator {

    private static int pc;
    private static int reg[] = new int[32];
    private static int memory[] = new int[10000];
    private static InstructionDecoder decoder = new InstructionDecoder();
    private  static TUIColors c = new TUIColors();

    public int getPc() {
        return pc;
    }

    public int[] getReg() {
        return reg;
    }

    public int[] getMemory() {
        return memory;
    }

    public void runProgram(int[] progr) {
        System.out.println(c.colorText("🛠 RISC-V Simulator started. 🚀", TUIColors.PURPLE_BACKGROUND));

        pc = 0;

        for (; ; ) {
            // get new instruction
            int instr = progr[pc >> 2];

            // process the instruction
            try{
                ProcessInstr(instr);
            }catch (Exception e){
                System.err.println(e.getStackTrace());
            }

            pc += 4; // One instruction is four bytes -> move program counter to next instruction 🛠

            // program have hit the end.
            if ((pc >> 2) >= progr.length) {
                break;
            }

            printRegState();
        }

        System.out.println(c.colorText("Program exit ✅", TUIColors.CYAN));
    }

    private void printRegState(){
        for (int x = 0; x < reg.length; ++x) {
            System.out.print(reg[x] + " ");
        }
        System.out.println();
    }

    private void ProcessInstr(int instr) throws ExecutionControl.NotImplementedException {
        // extract opcode
        int opcode = instr & 0x7f;

        // map the opcode to the right action's
        switch (opcode) {
            // type R
            case 0x33:
                processR(decoder.process(instr));
                break;
            // type I
            case 0x13:
                processI(decoder.process(instr));
                break;
            case 0x3:
                processI(decoder.process(instr));
                break;
            default:
                System.out.println(c.colorText("Opcode " + opcode + " not yet implemented 🛠😤", TUIColors.RED));
        }
    }

    private void processI(I i) throws ExecutionControl.NotImplementedException {
        if(i.opcode == 0x3) {
            //  LB
            if (i.funct3 == 0x0) {
                if ((memory[ISASimulator.reg[i.rs1] + i.imm]) >> 7 == 1)
                    reg[i.rd] = (memory[reg[i.rs1] + i.imm]) | 0xFFFFFF00;
                else
                    reg[i.rd] = (memory[reg[i.rs1] + i.imm]);
                return;
            }
            //  LH
            if (i.funct3 == 0x1) {
                if ((memory[reg[i.rs1] + i.imm + 1]) >> 7 == 1)
                    reg[i.rd] = (memory[reg[i.rs1] + i.imm]) | ((memory[reg[i.rs1] + i.imm + 1]) << 8) | 0xFFFF0000;
                else
                    reg[i.rd] = (memory[reg[i.rs1] + i.imm]) | ((memory[reg[i.rs1] + i.imm + 1]) << 8);
                return;
            }
            //  LW
            if (i.funct3 == 0x2) {
                reg[i.rd] = (memory[reg[i.rs1] + i.imm]) | ((memory[reg[i.rs1] + i.imm + 1]) << 8) | ((memory[reg[i.rs1] + i.imm + 2]) << 16) | ((memory[reg[i.rs1] + i.imm + 3]) << 24);
                return;
            }
            //  LD
            if (i.funct3 == 0x3) {
                reg[i.rd] = memory[reg[i.rs1] + i.imm];
                return;
            }
            // LBU
            if (i.funct3 == 0x4) {
                reg[i.rd] = memory[reg[i.rs1] + i.imm];
                return;
            }
            // LHU
            if (i.funct3 == 0x5) {
                reg[i.rd] = (memory[reg[i.rs1] + i.imm]) | ((memory[reg[i.rs1] + i.imm + 1]) << 8);
                return;
            }
            // LWU
            if (i.funct3 == 0x6) {
                reg[i.rd] = (memory[reg[i.rs1] + i.imm]) | ((memory[reg[i.rs1] + i.imm + 1]) << 8) | ((memory[reg[i.rs1] + i.imm + 2]) << 16) | ((memory[reg[i.rs1] + i.imm + 3]) << 24);
                return;
            }
        }
        else if(i.opcode == 0x13){
        // ADDI
        if(i.funct3 == 0x0){
//            if((i.instr >> 31) == 1)
//                reg[i.rd] = reg[i.rs1] + (i.imm | 0xFFFFF000);
//            else
                reg[i.rd] = reg[i.rs1] + i.imm;
            return;
        }
        // SLLI
        if(i.funct3 == 0x1 && i.funct6 == 0x00){
            reg[i.rd] = reg[i.rs1] << (i.imm & 0x1F);
            return;
        }
        // slti
        if(i.funct3 == 0x2){
            reg[i.rd] = reg[i.rs1] < i.imm ? 1 : 0;
            return;
        }
        // sltiu
        if(i.funct3 == 0x3){
            reg[i.rd] = (reg[i.rs1] < i.imm) ? 1:0;
            return;
        }
        // xori
        if(i.funct3 == 0x4){
            reg[i.rd] = reg[i.rs1] ^ i.imm;
            return;
        }
        //srli
        if(i.funct3 == 0x5 && i.funct6 == 0x00){
            reg[i.rd] =  reg[i.rs1] >> i.imm;
            return;
        }
        //srai
        if(i.funct3 == 0x5 && i.funct6 == 0x10){
            reg[i.rd] = reg[i.rs1] >> i.imm;
            return;
        }
        // ori
        if(i.funct3 == 0x6){
            reg[i.rd] = reg[i.rs1] | i.imm;
            return;
        }
        // ADDI
        if(i.funct3 == 0x7){
            reg[i.rd] = reg[i.rs1] & i.imm;
            return;
        }
        if(i.opcode == 0x67) {
            // Jalr instruction
            if (i.funct3 == 0x0) {
                if (i.rd != 0)
                    reg[i.rd] = pc + 4;
                // move pc
                pc = reg[i.rs1] + i.imm;
            }
        }

        throw new ExecutionControl.NotImplementedException(c.colorText("I-type instruction not implemented 🛠😤", TUIColors.RED));
        }
    }

    private void processR(R i) throws ExecutionControl.NotImplementedException {
        // ADD
        if(i.funct3 == 0x00 && i.funct7 == 0x00){
            reg[i.rd] = reg[i.rs1] + reg[i.rs2];
            return;
        }
        // SUB
        if(i.funct3 == 0x0 && i.funct7 == 0x20){
            reg[i.rd] = reg[i.rs1] - reg[i.rs2];
            return;
        }
        // SLL
        if(i.funct3 == 0x1 && i.funct7 == 0x00){
            reg[i.rd] = reg[i.rs1] << reg[i.rs2];
            return;
        }
        // XOR
        if(i.funct3 == 0x4 && i.funct7 == 0x0){
            reg[i.rd] = reg[i.rs1] ^ reg[i.rs2];
            return;
        }
        // SRL
        if(i.funct3 == 0x5 && i.funct7 == 0x00){
            reg[i.rd] = reg[i.rs1] >> reg[i.rs2];
            return;
        }
        // SRA
        if(i.funct3 == 0x5 && i.funct7 == 0x20){
            reg[i.rd] = reg[i.rs1] >> reg[i.rs2];
            return;
        }
        //  OR
        if(i.funct3 == 0x6 && i.funct7 == 0x00){
            reg[i.rd] = reg[i.rs1] | reg[i.rs2];
            return;
        }
        // AND
        if(i.funct3 == 0x7 && i.funct7 == 0x00){
            reg[i.rd] = reg[i.rs1] & reg[i.rs2];
            return;
        }

        throw new ExecutionControl.NotImplementedException(c.colorText("R-type instruction not implemented 🛠😤", TUIColors.RED));
    }
}