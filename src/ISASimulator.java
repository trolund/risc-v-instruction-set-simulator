import Instruction.R;
import Instruction.I;
import jdk.jshell.spi.ExecutionControl;

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
            default:
                System.out.println(c.colorText("Opcode " + opcode + " not yet implemented 🛠😤", TUIColors.RED));
        }
    }

    private void processI(I i) {
        reg[i.rd] = reg[i.rs1] + i.imm;
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

        throw new ExecutionControl.NotImplementedException("R-type instruction not implemented");
    }
}