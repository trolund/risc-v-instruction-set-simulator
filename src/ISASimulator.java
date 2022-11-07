import Instruction.R;
import Instruction.I;
import Instruction.U;
import Instruction.SB;
import Instruction.S;
import Instruction.abstact.Instruction;
import jdk.jshell.spi.ExecutionControl;

public class ISASimulator {

    private boolean printReg = true;
    private boolean debug = true;
    private int pc;
    private int[] reg;
    private int[] memory;
    private int currInstr;

    private int[] progr;
    private Instruction currInstrObj;
    private InstructionDecoder decoder;
    private TUIColors c;

    private int instrCount;

    private boolean forceEnd = false;
    private int exitCode = 0;

    public ISASimulator(boolean printReg, boolean debug) {
        this.printReg = printReg;
        this.debug = debug;
        resetSim();
    }

    public ISASimulator() {
        resetSim();
    }

    private void resetSim() {
        this.pc = 0;
        this.memory = new int[10000];
        this.reg = new int[32];

        this.decoder = new InstructionDecoder();
        this.c = new TUIColors();
        instrCount = 0;
    }

    public int[] getReg() {
        return reg;
    }

    public void runProgram(int[] progr) {
        System.out.println(c.colorText("🛠 RISC-V Simulator started. 🚀", TUIColors.PURPLE_BACKGROUND));
        if (progr.length <= 0) {
            System.out.println(c.colorText("Empty program (∅ == 🪹)", TUIColors.YELLOW_BACKGROUND));
            exitPrint();
            return;
        }
        this.progr = progr;

        pc = 0;

        while (true) {
            if(forceEnd){ break; }

            // fetch new instruction
            currInstr = progr[pc >> 2];

            // process the instruction
            try {
                decodeInstr(currInstr);
                exeInstr(currInstrObj);
                instrCount++;
            } catch (Exception e) {
                // e.printStackTrace();
            }

            pc += 4; // One instruction is four bytes (32 bit) -> move program counter to next instruction 🛠

            // program have hit the end.
            if ((pc >> 2) >= progr.length) {
                break;
            }

            printRegState();
        }

        exitPrint();
    }

    private void exit(int exitCode) {
        forceEnd = true;
        this.exitCode = exitCode;
    }
    private void exitPrint() {
        System.out.println(c.colorText("Program exit with code: " + exitCode, TUIColors.CYAN));
        System.out.println(c.colorText("Executed " + instrCount + " instructions", TUIColors.CYAN_BACKGROUND));
    }

    private void printRegState() {
        if (this.printReg) {
            for (int i : reg) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    private void decodeInstr(int instr) {
        Instruction i = decoder.process(instr);
        this.currInstr = instr;
        this.currInstrObj = i;
    }

    private void exeInstr(Instruction i) throws ExecutionControl.NotImplementedException {
        // map the opcode to the right action's
        switch (i.opcode) {
            // type R
            case 0x33 -> {
                if (debug) System.out.println("type-R");
                processR((R) i);
            }
            // type I
            case 0x3, 0x13, 0x67, 0x73 -> {
                if (debug) System.out.println("type-I");
                processI((I) i);
            }
            // type U
            case 0x37, 0x17 -> {
                if (debug) System.out.println("type-U");
                processU((U) i);
            }
            // type SB
            case 0x63 -> {
                if (debug) System.out.println("type-SB");
                processSB((SB) i);
            }
            case 0x23 -> {
                if (debug) System.out.println("type-S");
                processS((S) i);
            }
            default ->
                    System.out.println(c.colorText("Opcode " + i.opcode + " not yet implemented 🛠😤", TUIColors.RED));
        }
    }

    private void processS(S i) {
        //  sb instruction
        if((i.funct3 == 0x0)){
            if (debug) System.out.println("sb");
            // Store 8-bit, values from the low bits of register rs2 to memory.
            // imm = offset
            memory[reg[i.rs1] + i.imm] = reg[i.rs2] & 0xFF;
            return;
        }
        //  sh instruction
        if((i.funct3 == 0x1)){
            if (debug) System.out.println("sh");
            // Store 16-bit, values from the low bits of register rs2 to memory
            memory[reg[i.rs1] + i.imm] = reg[i.rs2] & 0xFFFF;
            return;
        }
        //  sw instruction
        if((i.funct3 == 0x2)){
            if (debug) System.out.println("sw");
            // Store 32-bit, values from the low bits of register rs2 to memory.
            memory[reg[i.rs1] + i.imm] = reg[i.rs2];
            return;
        }

    }

    private void processU(U i) throws ExecutionControl.NotImplementedException {
        // LUI
        if (i.opcode == 0x37) {
            if (debug) System.out.println("lui");
            reg[i.rd] = i.imm;
            return;
        }
        // auipc
        if (i.opcode == 0x17) {
            if (debug) System.out.println("auipc " + pc / 4);
            reg[i.rd] = pc + i.imm;
            return;
        }

        throw new ExecutionControl.NotImplementedException(c.colorText("U-type instruction not implemented 🛠😤", TUIColors.RED));
    }

    private void processI(I i) throws ExecutionControl.NotImplementedException {
        // ecall https://github.com/kvakil/venus/wiki/Environmental-Calls
        if (i.opcode == 0x73) {
            if (debug) System.out.println("ecall");
            int a0 = reg[10];

            if (a0 == 1) {
                System.out.println(reg[11]);
            } else if (a0 == 4) {
                System.out.println(reg[11]); // TODO print string?
            } else if (a0 == 9) { // allocates a1 bytes on the heap, returns pointer to start in a0

            } else if (a0 == 10) {
                exit(0);
            } else if (a0 == 11) {
                System.out.println((char) reg[11]);
            } else if (a0 == 17) {
                exit(reg[11]);
            } else {
                System.out.println(c.colorText("Invalid ecall: " + a0, TUIColors.YELLOW_BACKGROUND));
                exit(1);
            }
            return;
        }
        if (i.opcode == 0x3) {
            if (debug) System.out.println("lb");
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
                if (debug) System.out.println("lh");
                if ((memory[reg[i.rs1] + i.imm + 1]) >> 7 == 1)
                    reg[i.rd] = (memory[reg[i.rs1] + i.imm]) | ((memory[reg[i.rs1] + i.imm + 1]) << 8) | 0xFFFF0000;
                else
                    reg[i.rd] = (memory[reg[i.rs1] + i.imm]) | ((memory[reg[i.rs1] + i.imm + 1]) << 8);
                return;
            }
            //  LW
            if (i.funct3 == 0x2) {
                if (debug) System.out.println("lw");
                reg[i.rd] = (memory[reg[i.rs1] + i.imm]) | ((memory[reg[i.rs1] + i.imm + 1]) << 8) | ((memory[reg[i.rs1] + i.imm + 2]) << 16) | ((memory[reg[i.rs1] + i.imm + 3]) << 24);
                return;
            }
            //  LD
            if (i.funct3 == 0x3) {
                if (debug) System.out.println("ld");
                reg[i.rd] = memory[reg[i.rs1] + i.imm];
                return;
            }
            // LBU
            if (i.funct3 == 0x4) {
                if (debug) System.out.println("lbu");
                reg[i.rd] = memory[reg[i.rs1] + i.imm];
                return;
            }
            // LHU
            if (i.funct3 == 0x5) {
                if (debug) System.out.println("lhu");
                reg[i.rd] = (memory[reg[i.rs1] + i.imm]) | ((memory[reg[i.rs1] + i.imm + 1]) << 8);
                return;
            }
            // LWU
            if (i.funct3 == 0x6) {
                if (debug) System.out.println("lwu");
                reg[i.rd] = (memory[reg[i.rs1] + i.imm]) | ((memory[reg[i.rs1] + i.imm + 1]) << 8) | ((memory[reg[i.rs1] + i.imm + 2]) << 16) | ((memory[reg[i.rs1] + i.imm + 3]) << 24);
                return;
            }
        }
        if (i.opcode == 0x13) {
            // addi
            if (i.funct3 == 0x0) {
                if (debug) System.out.println("addi: reg[" + i.rd + "] = " + reg[i.rs1] + " + " + i.imm);
                if ((i.imm >> 11) == 1) {
                    reg[i.rd] = reg[i.rs1] + (0xFFFFF000 + i.imm);
                } else {
                    reg[i.rd] = reg[i.rs1] + i.imm;
                }
                return;
            }
            // slli
            if (i.funct3 == 0x1 && i.funct6 == 0x00) {
                if (debug) System.out.println("slli");
                reg[i.rd] = reg[i.rs1] << (i.imm & 0x1F);
                return;
            }
            // slti
            if (i.funct3 == 0x2) {
                if (debug) System.out.println("slti");
                reg[i.rd] = reg[i.rs1] < i.imm ? 1 : 0;
                return;
            }
            // sltiu
            if (i.funct3 == 0x3) {
                if (debug) System.out.println("sltiu");
                reg[i.rd] = (reg[i.rs1] < unsignedValue(i.imm)) ? 1 : 0;
                return;
            }
            // xori
            if (i.funct3 == 0x4) {
                if (debug) System.out.println("xori");
                reg[i.rd] = reg[i.rs1] ^ i.imm;
                return;
            }
            //srli
            if (i.funct3 == 0x5 && i.funct6 == 0x00) {
                if (debug) System.out.println("srli");
                reg[i.rd] = (int) (unsignedValue(reg[i.rs1]) >> unsignedValue(i.imm));
                return;
            }
            //srai
            if (i.funct3 == 0x5 && i.funct6 == 0x10) {
                if (debug) System.out.println("srai");
                reg[i.rd] = reg[i.rs1] >> i.imm;
                return;
            }
            // ori
            if (i.funct3 == 0x6) {
                if (debug) System.out.println("ori");
                reg[i.rd] = reg[i.rs1] | i.imm;
                return;
            }
            // ADDI
            if (i.funct3 == 0x7) {
                if (debug) System.out.println("addi");
                reg[i.rd] = reg[i.rs1] & i.imm;
                return;
            }
            if (i.opcode == 0x67) {
                if (debug) System.out.println("jalr");
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

    private void processSB(SB i) throws ExecutionControl.NotImplementedException {
        // beq instruction
        if ((i.funct3 == 0x0)) {
            if (debug) System.out.println("beq");
            if (reg[i.rs1] == reg[i.rs2]) {
                pc += i.imm - 4;
            }
            return;
        }
        // bne instruction
        if ((i.funct3 == 0x1)) {
            if (debug) System.out.println("bne");
            if (reg[i.rs1] != reg[i.rs2]) {
                pc += i.imm - 4; // -4 because the machine moves the pointer one forward
            }
            return;
        }
        // blt instruction
        if ((i.funct3 == 0x4)) {
            if (debug) System.out.println("blt");
            if (reg[i.rs1] < reg[i.rs2]) {
                pc += i.imm - 4;
            }
            return;
        }
        // bge instruction
        if ((i.funct3 == 0x5)) {
            if (debug) System.out.println("bge");
            if (reg[i.rs1] >= reg[i.rs2]) {
                pc = pc + i.imm - 4;
                return;
            }
        }
        //bltu instruction
        if ((i.funct3 == 0x6)) {
            if (debug) System.out.println("bltu");
            if (reg[i.rs1] < reg[i.rs2]) {
                pc += i.imm - 4;
            }
            return;
        }
        //bgeu instruction
        if ((i.funct3 == 0x7)) {
            if (debug) System.out.println("bgeu");
            if (unsignedValue(reg[i.rs1]) >= unsignedValue(reg[i.rs2])) {
                pc += i.imm - 4;
            }
            return;
        }
    }

    private void processR(R i) throws ExecutionControl.NotImplementedException {
        // ADD
        if (i.funct3 == 0x00 && i.funct7 == 0x00) {
            if (debug) System.out.println("add");
            reg[i.rd] = reg[i.rs1] + reg[i.rs2];
            return;
        }
        // SUB
        if (i.funct3 == 0x0 && i.funct7 == 0x20) {
            if (debug) System.out.println("sub");
            reg[i.rd] = reg[i.rs1] - reg[i.rs2];
            return;
        }
        // SLL
        if (i.funct3 == 0x1 && i.funct7 == 0x00) {
            if (debug) System.out.println("sll");
            reg[i.rd] = reg[i.rs1] << reg[i.rs2];
            return;
        }
        // XOR
        if (i.funct3 == 0x4 && i.funct7 == 0x0) {
            if (debug) System.out.println("xor");
            reg[i.rd] = reg[i.rs1] ^ reg[i.rs2];
            return;
        }
        // srl
        if (i.funct3 == 0x5 && i.funct7 == 0x00) {
            if (debug) System.out.println("srl: " + reg[i.rs1] + " >> " + reg[i.rs2]);
            reg[i.rd] = (int) (unsignedValue(reg[i.rs1]) >> unsignedValue(reg[i.rs2]));
            return;
        }
        // sra
        if (i.funct3 == 0x5 && i.funct7 == 0x20) {
            if (debug) System.out.println("sra");
            reg[i.rd] = reg[i.rs1] >> reg[i.rs2];
            return;
        }
        //  OR
        if (i.funct3 == 0x6 && i.funct7 == 0x00) {
            if (debug) System.out.println("or");
            reg[i.rd] = reg[i.rs1] | reg[i.rs2];
            return;
        }
        // AND
        if (i.funct3 == 0x7 && i.funct7 == 0x00) {
            if (debug) System.out.println("and");
            reg[i.rd] = reg[i.rs1] & reg[i.rs2];
            return;
        }
        // slt
        if (i.funct3 == 0x2 && i.funct7 == 0x00) {
            if (debug) System.out.println("slt");
            reg[i.rd] = (reg[i.rs1] < reg[i.rs2]) ? 1 : 0;
            return;
        }
        // sltu
        if (i.funct3 == 0x3 && i.funct7 == 0x00) {
            if (debug) System.out.println("sltu");
            reg[i.rd] = (reg[i.rs1] < reg[i.rs2]) ? 1 : 0;
            return;
        }

        throw new ExecutionControl.NotImplementedException(c.colorText("R-type instruction not implemented 🛠😤", TUIColors.RED));
    }

    private long unsignedValue(int v) {
        return v & 0xffffffffL;
    }
}