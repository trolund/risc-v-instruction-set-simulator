# Week 5

    Suggested exercises:
    4.5, 4.6, 4.7.  


## 4.5

In this exercise, we examine in detail how an instruction is executed in a single-cycle datapath. Problems in this exercise refer to a clock cycle in which the processor fetches the following instruction word: 0x00c6ba23.

    (0x00c6ba23)_6 : (1100 0110 1011 1010 0010 0011)_2
    <=>
    (0x00c6ba23)_6 : (1100_01101_011_10100_0100011)_2


    | funct7   | rs2    | rs1    | funct3 | rd     | opcode   |
    |----------|--------|--------|--------|--------|----------|
    | 0000000  | 01100  | 01101  | 011    | 10100  | 0100011  |

Look at he green card!

For context: The encoded instruction is sd x12, 20(x13)


### 4.5.1 What are the values of the ALU control unit’s inputs for this instruction?

ALUOp:
00

ALU Control Lines:
0010


### 4.5.2 What is the new PC address after this instruction is executed? Highlight the path through which this value is determined.

The new PC is the old PC + 4. 

This signal goes from the PC, through the “PC + 4” adder, through the “branch” mux, and back to the PC.

### 4.5.3 For each mux, show the values of its inputs and outputs during the execution of this instruction. List values that are register outputs at Reg [xn].

ALUsrc: Inputs: Reg[x12] and 0x0000000000000014; 
        Output: 0x0000000000000014

MemToReg: Inputs: Reg[x13] + 0x14 and <undefined>; output: <undefined>

Branch: Inputs: PC+4 and PC + 0x28