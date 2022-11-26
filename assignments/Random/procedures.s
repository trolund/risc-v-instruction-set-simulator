asm_callee:
   addi a0, a0, -2
   addi a0, a0, -3

   jalr x0, 0(x1)


asm_caller:
   addi a0, a0, 0
   addi a0, a0, 4
   addi a0, a0, 3

   jal x1, asm_callee

   li a0, 0