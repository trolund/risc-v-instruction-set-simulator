#Alternative Complex Multiplication
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
    jal altComplexMul # Multiply z and w
    nop
    j end # Jump to end of program
    nop
    .text
    altComplexMul:
        #x5=(a+b)
        add x5, a0, a1
        #x6=(d-c)
        sub x6, a3, a2
        #x7=(c+d)
        add x7, a2, a3

        #K1 c*(a+b)
        mul x5, a2, x5
        #K2 a*(d-c)
        mul, x6, a0, x6
        #K3 b*(c+d)
        mul x7, x7, a1

        #real part K1-K3
        sub a0, x5, x7
        #im part K1+K2
        add a1, x5, x6

    # return control to caller
    jalr x0 0(ra)
end:
nop