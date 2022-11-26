.data
aa: .word 1 # Re part of z
bb: .word 2 # Im part of z
cc: .word 3 # Re part of w
dd: .word 4 # Im part of w
.text
.globl main
main:
    lw a0 , aa
    lw a1 , bb
    lw a2 , cc
    lw a3 , dd
    jal complex_mul # Multiply z and w
    nop
    j end # Jump to end of program
    nop


complex_mul:
    #real part:(a*c-b*d)
    mul x5, a0, a2
    mul x6, a1, a3
    sub a0, x5, x6
    #Im part: (i*(a*d+b*c)
    mul x5, a0, a3
    mul x6, a1, a2
    add a1, x5, x6
    end:
    nop