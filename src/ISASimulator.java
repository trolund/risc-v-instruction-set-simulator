import Instruction.R;
import Instruction.I;
import Instruction.U;
import Instruction.abstact.Instruction;
import jdk.jshell.spi.ExecutionControl;

import java.util.Arrays;

public class ISASimulator {

    private boolean printReg = true;

    private int pc;
    private int reg[];
    private int memory[];
    private int currInstr;
    private Instruction currInstrObj;
    private InstructionDecoder decoder;
    private TUIColors c;

    private boolean debug = true;

    public ISASimulator(boolean printReg) {
        this.printReg = printReg;
        resetSim();
    }

    public ISASimulator() {
        resetSim();
    }

    private void resetSim(){
        this.pc = 0;
        this.memory = new int[10000];
        this.reg = new int[32];

        this.decoder =  new InstructionDecoder();
        this.c = new TUIColors();
    }

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
        if(progr.length <= 0) {
            System.out.println(c.colorText("Empty program (∅ == 🪹)", TUIColors.YELLOW_BACKGROUND));
            exit(0);
            return;
        }

        pc = 0;

        for (; ; ) {
            // fetch new instruction
            currInstr = progr[pc >> 2];

            // process the instruction
            try{
                decodeInstr(currInstr);
                exeInstr(currInstrObj);
            }catch (Exception e){
                // System.err.println(Arrays.toString(e.getStackTrace()));
            }

            pc += 4; // One instruction is four bytes (32 bit) -> move program counter to next instruction 🛠

            // program have hit the end.
            if ((pc >> 2) >= progr.length) {
                break;
            }

            printRegState();
        }

        exit(0);
    }

    private void exit(int exitCode){
        System.out.println(c.colorText("Program exit with code: " + exitCode, TUIColors.CYAN));
    }

    private void printRegState(){
        if(this.printReg){
            for (int i : reg) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    private void decodeInstr(int instr){
        Instruction i = decoder.process(instr);
        this.currInstr = instr;
        this.currInstrObj = i;
    }

    private void exeInstr(Instruction i) throws ExecutionControl.NotImplementedException {
        // map the opcode to the right action's
        switch (i.opcode) {
            // type R
            case 0x33:
                processR((R) i);
                break;
            // type I
            case 0x13:
            case 0x3:
                processI((I) i);
                break;
            case 0x37:
            case 0x17:
                processU((U) i);
                break;
            default:
                System.out.println(c.colorText("Opcode " + i.opcode + " not yet implemented 🛠😤", TUIColors.RED));
        }
    }

    private void processU(U i) throws ExecutionControl.NotImplementedException {
        // LUI
        if(i.opcode == 0x37) {
            if(debug) System.out.println("lui");
            reg[i.rd] = i.imm;
            return;
        }
        // auipc
        if(i.opcode == 0x17) {
            if(debug) System.out.println("auipc");
            reg[i.rd] = pc + i.imm;
            return;
        }

        throw new ExecutionControl.NotImplementedException(c.colorText("U-type instruction not implemented 🛠😤", TUIColors.RED));
    }

    private void processI(I i) throws ExecutionControl.NotImplementedException {
        if(i.opcode == 0x3) {
            if(debug) System.out.println("lb");
            //  LB
            if (i.funct3 == 0x0) {
                if ((memory[reg[i.rs1] + i.imm]) >> 7 == 1)
                    reg[i.rd] = (memory[reg[i.rs1] + i.imm]) | 0xFFFFFF00;
                else
                    reg[i.rd] = (memory[reg[i.rs1] + i.imm]);
                return;
            }
            //  LH
            if (i.funct3 == 0x1) {
                if(debug) System.out.println("lh");
                if ((memory[reg[i.rs1] + i.imm + 1]) >> 7 == 1)
                    reg[i.rd] = (memory[reg[i.rs1] + i.imm]) | ((memory[reg[i.rs1] + i.imm + 1]) << 8) | 0xFFFF0000;
                else
                    reg[i.rd] = (memory[reg[i.rs1] + i.imm]) | ((memory[reg[i.rs1] + i.imm + 1]) << 8);
                return;
            }
            //  LW
            if (i.funct3 == 0x2) {
                if(debug) System.out.println("lw");
                reg[i.rd] = (memory[reg[i.rs1] + i.imm]) | ((memory[reg[i.rs1] + i.imm + 1]) << 8) | ((memory[reg[i.rs1] + i.imm + 2]) << 16) | ((memory[reg[i.rs1] + i.imm + 3]) << 24);
                return;
            }
            //  LD
            if (i.funct3 == 0x3) {
                if(debug) System.out.println("ld");
                reg[i.rd] = memory[reg[i.rs1] + i.imm];
                return;
            }
            // LBU
            if (i.funct3 == 0x4) {
                if(debug) System.out.println("lbu");
                reg[i.rd] = memory[reg[i.rs1] + i.imm];
                return;
            }
            // LHU
            if (i.funct3 == 0x5) {
                if(debug) System.out.println("lhu");
                reg[i.rd] = (memory[reg[i.rs1] + i.imm]) | ((memory[reg[i.rs1] + i.imm + 1]) << 8);
                return;
            }
            // LWU
            if (i.funct3 == 0x6) {
                if(debug) System.out.println("lwu");
                reg[i.rd] = (memory[reg[i.rs1] + i.imm]) | ((memory[reg[i.rs1] + i.imm + 1]) << 8) | ((memory[reg[i.rs1] + i.imm + 2]) << 16) | ((memory[reg[i.rs1] + i.imm + 3]) << 24);
                return;
            }
        }
        else if(i.opcode == 0x13){
        // ADDI
        if(i.funct3 == 0x0){
            if(debug) System.out.println("addi");
//            if((i.instr >> 31) == 1)
//                reg[i.rd] = reg[i.rs1] + (i.imm | 0xFFFFF000);
//            else
                reg[i.rd] = reg[i.rs1] + i.imm;
            return;
        }
        // SLLI
        if(i.funct3 == 0x1 && i.funct6 == 0x00){
            if(debug) System.out.println("slli");
            reg[i.rd] = reg[i.rs1] << (i.imm & 0x1F);
            return;
        }
        // slti
        if(i.funct3 == 0x2){
            if(debug) System.out.println("slti");
            reg[i.rd] = reg[i.rs1] < i.imm ? 1 : 0;
            return;
        }
        // sltiu
        if(i.funct3 == 0x3){
            if(debug) System.out.println("sltiu");
            reg[i.rd] = (reg[i.rs1] < i.imm) ? 1:0;
            return;
        }
        // xori
        if(i.funct3 == 0x4){
            if(debug) System.out.println("xori");
            reg[i.rd] = reg[i.rs1] ^ i.imm;
            return;
        }
        //srli
        if(i.funct3 == 0x5 && i.funct6 == 0x00){
            if(debug) System.out.println("srli");
            reg[i.rd] =  reg[i.rs1] >> i.imm;
            return;
        }
        //srai
        if(i.funct3 == 0x5 && i.funct6 == 0x10){
            if(debug) System.out.println("srai");
            reg[i.rd] = reg[i.rs1] >> i.imm;
            return;
        }
        // ori
        if(i.funct3 == 0x6){
            if(debug) System.out.println("ori");
            reg[i.rd] = reg[i.rs1] | i.imm;
            return;
        }
        // ADDI
        if(i.funct3 == 0x7){
            if(debug) System.out.println("addi");
            reg[i.rd] = reg[i.rs1] & i.imm;
            return;
        }
        if(i.opcode == 0x67) {
            if(debug) System.out.println("jalr");
            // Jalr instruction
            if (i.funct3 == 0x0) {
                if (i.rd != 0)
                    reg[i.rd] = pc + 4;
                // move pc
                pc = reg[i.rs1] + i.imm;
            }
            return;
        }

        throw new ExecutionControl.NotImplementedException(c.colorText("I-type instruction not implemented 🛠😤", TUIColors.RED));
        }
    }

    private void processR(R i) throws ExecutionControl.NotImplementedException {
        // ADD
        if(i.funct3 == 0x00 && i.funct7 == 0x00){
            if(debug) System.out.println("add");
            reg[i.rd] = reg[i.rs1] + reg[i.rs2];
            return;
        }
        // SUB
        if(i.funct3 == 0x0 && i.funct7 == 0x20){
            if(debug) System.out.println("sub");
            reg[i.rd] = reg[i.rs1] - reg[i.rs2];
            return;
        }
        // SLL
        if(i.funct3 == 0x1 && i.funct7 == 0x00){
            if(debug) System.out.println("sll");
            reg[i.rd] = reg[i.rs1] << reg[i.rs2];
            return;
        }
        // XOR
        if(i.funct3 == 0x4 && i.funct7 == 0x0){
            if(debug) System.out.println("xor");
            reg[i.rd] = reg[i.rs1] ^ reg[i.rs2];
            return;
        }
        // SRL
        if(i.funct3 == 0x5 && i.funct7 == 0x00){
            if(debug) System.out.println("srl");
            reg[i.rd] = reg[i.rs1] >> reg[i.rs2];
            return;
        }
        // SRA
        if(i.funct3 == 0x5 && i.funct7 == 0x20){
            if(debug) System.out.println("sra");
            reg[i.rd] = reg[i.rs1] >> reg[i.rs2];
            return;
        }
        //  OR
        if(i.funct3 == 0x6 && i.funct7 == 0x00){
            if(debug) System.out.println("or");
            reg[i.rd] = reg[i.rs1] | reg[i.rs2];
            return;
        }
        // AND
        if(i.funct3 == 0x7 && i.funct7 == 0x00){
            if(debug) System.out.println("and");
            reg[i.rd] = reg[i.rs1] & reg[i.rs2];
            return;
        }
        // slt
        if(i.funct3 == 0x2 && i.funct7 == 0x00){
            if(debug) System.out.println("slt");
            reg[i.rd] = (reg[i.rs1] < reg[i.rs2]) ? 1 : 0;
            return;
        }
        // sltu
        if(i.funct3 == 0x3 && i.funct7 == 0x00){
            if(debug) System.out.println("sltu");
            reg[i.rd] = (reg[i.rs1] < reg[i.rs2]) ? 1 : 0;
            return;
        }

        throw new ExecutionControl.NotImplementedException(c.colorText("R-type instruction not implemented 🛠😤", TUIColors.RED));
    }
}