LOOP:
        lw x10, 0(x13)
        lw x11, 8(x13)
        add x12, x10, x11
        subi x13, x13, 16
        bnez x12, LOOP