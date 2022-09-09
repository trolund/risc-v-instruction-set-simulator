package Instruction.abstact;

/**
 * // 32-bit instruction abstract base class
 */
public abstract class Instruction {
    public int opcode;

    public Instruction(int instr) {
        this.opcode = instr & 0x7f;;
    }
}

