public class IsaSim {

    static int pc;
    static int reg[] = new int[4];

    public void runProgram(int[] progr) {
        // System.out.println("Hello RISC-V World!");

        pc = 0;

        for (; ; ) {

            int instr = progr[pc >> 2];
            int opcode = instr & 0x7f;
            int rd = (instr >> 7) & 0x01f;
            int rs1 = (instr >> 15) & 0x01f;
            int rs2 = (instr >> 20) & 0x01f;
            int imm = (instr >> 20); // immediate

            switch (opcode) {
                case 51:
                    reg[rd] = reg[rs1] + reg[rs2];
                    break;
                case 0x13:
                    reg[rd] = reg[rs1] + imm;
                    break;
                default:
                    System.out.println("Opcode " + opcode + " not yet implemented");
                    break;
            }

            pc += 4; // One instruction is four bytes
            if ((pc >> 2) >= progr.length) {
                break;
            }
            for (int i = 0; i < reg.length; ++i) {
                System.out.print(reg[i] + " ");
            }
            System.out.println();
        }

        System.out.println("Program exit");

    }

}