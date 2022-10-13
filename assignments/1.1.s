addi x4 x0 -16

bge x4 x0 exit # if x4 >= zero jump to exit
slli t0 x4 29  # if x4 multiple of 8 then the 3 (32-29) least-significant bits must be 0, written to t0
bne t0 x0 exit # if t0 != zero jump to exit (if zero continue)
li x1 0        # set x1 to zero if both conditions is meet.
exit: # lable use to jump to the end of the program