import Instruction.abstact.Instruction;
import Instruction.R;
import Instruction.I;
import Instruction.S;
import Instruction.U;

public class InstructionDecoder {

    public <T extends Instruction> T process(int instr) {
        try{
            return (T) process(instr, false);
        }catch (Exception e) {
            return null;
        }
    }

    public Instruction process(int instr, boolean debug) throws Exception {

        int opcode = instr & 0x7f;

        int rd = (instr >> 7) & 0x01f;
        int rs1 = (instr >> 15) & 0x01f;
        int rs2 = (instr >> 20) & 0x01f;
        int imm = (instr >> 20); // immediate*/
        int funct3;
        int funct7;
        int funct6;


        switch (opcode) {
            //Format: R-type
            case 0x33:
                if (debug) System.out.println("Type: R-type");
                return new R(instr);
            //Format: I-type
            case 0x3:
            case 0x13:
            case 0x67:
                if (debug) System.out.println("Type: I-type");
                return new I(instr);
            //Format: S-type
            case 0x23:
                if (debug) System.out.println("Type: S-type");
                return new S(instr);
            //Format: SB-type
            case 0x63:
                if (debug) System.out.println("Type: SB-type");

                imm = (instr & 0xF00) >> 7;  //imm[4:1]
                imm |= (instr & 0x80) << 4; //imm[11]
                funct3 = (instr >> 12) & 0x7;
                rs1 = (instr >> 15) & 0x1F;
                rs2 = (instr >> 20) & 0x1F;
                imm = (instr & 0x7E000080) >> 20; //imm[10:5]
                throw new Exception("Type is not implemented");
                //Format: U-type
            case 0x37:
            case 0x17:
                if (debug) System.out.println("Type: U-type");
                return new U(instr);
            case 0x6F:  //UJ type
                if (debug) System.out.println("Type: UJ-type");

                rd = (instr >> 7) & 0x1F;
                imm = instr & 0xFF000;        //imm[19:12]
                imm |= (instr >> 9) & 0x800;     //imm[11]
                imm |= (instr >> 20) & 0x7FE;   //imm[10:1]
                throw new Exception("Type is not implemented");
            default:
                throw new Exception("Type is not implemented");
        }


    }
}
