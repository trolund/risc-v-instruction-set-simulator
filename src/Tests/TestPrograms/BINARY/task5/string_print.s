LOOP:
    lw x3, 0(x2)
    and x7, x3, x5
    slt x1, x6, x3
    addi x2, x2, -4
    bne x2, x0, LOOP