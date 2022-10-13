addi x24 x24 2  
addi x24 x20 4
add  x20 x22 x24
jal  x1 B  


B:

jal x1 C
lw  x22 0(x20)
jalr x0 0(x1)

C:
add  x20 x0 x21
addi x24 x20 4
jalr x0 0(x1)