import Instruction.R;
import Instruction.I;

public class ISASimulator {

    private static int pc;
    private static int reg[] = new int[4];
    private static InstructionDecoder decoder = new InstructionDecoder();
    private  static TUIColors c = new TUIColors();

    public void runProgram(int[] progr) {
        System.out.println(c.colorText("🛠 RISC-V Simulator started. 🚀", TUIColors.PURPLE_BACKGROUND));

        pc = 0;

        for (; ; ) {

            int instr = progr[pc >> 2];
//            int opcode = instr & 0x7f;
//            int rd = (instr >> 7) & 0x01f;
//            int rs1 = (instr >> 15) & 0x01f;
//            int rs2 = (instr >> 20) & 0x01f;
//            int imm = (instr >> 20); // immediate
//            int imm12 = (instr >> 12); // immediate start bit 12
//            int shamt = (instr >> 20) & 0x01f;

            // process the instruction
            ProcessInstr(instr);

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

    private void ProcessInstr(int instr){
        // extract opcode
        int opcode = instr & 0x7f;

        // map the opcode to the right action's
        switch (opcode) {
            // type R
            case 0x33: // ADD
                processR(decoder.process(instr));
                break;
            // type I
            case 0x13: // ADDI
                processI(decoder.process(instr));
                break;
//                case 0x13: // AND
//                    reg[rd] = reg[rs1] & reg[rs2];
//                    break;
//                case 0x13: // OR
//                    reg[rd] = reg[rs1] | reg[rs2];
//                    break;
//                case 0x13: // XOR
//                    reg[rd] = reg[rs1] ^ reg[rs2];
//                    break;
//                case 0x13: // ANDI
//                    reg[rd] = reg[rs1] & imm12;
//                    break;
//                case 0x13: // ORI
//                    reg[rd] = reg[rs1] | imm12;
//                    break;
//                case 0x13: // XORI
//                    reg[rd] = reg[rs1] ^ imm12;
//                    break;
//                case 0x13: // SSL
//                    reg[rd] = reg[rs1] << reg[rs2];
//                    break;
//                case 0x13: // SRL
//                case 0x13: // SRA
//                    reg[rd] = reg[rs1] >> reg[rs2];
//                    break;
//                case 0x13: // SLLI
//                    reg[rd] = reg[rs1] << shamt;
//                    break;
//                case 0x13: // SRLI
//                case 0x13: // SRAI
//                    reg[rd] = reg[rs1] >> shamt;
//                    break;
            default:
                System.out.println(c.colorText("Opcode " + opcode + " not yet implemented 🛠😤", TUIColors.RED));
                break;
        }
    }

    private void processI(I i) {
        reg[i.rd] = reg[i.rs1] + i.imm;
    }

    private void processR(R i){
        // ADD
        if(i.funct3 == 0x00 && i.funct7 == 0x00){
            reg[i.rd] = reg[i.rs1] + reg[i.rs2];
        }
    }
}