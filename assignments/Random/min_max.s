.test_data:
    li t4 0x100000 # memory pointer start at 0x100000
    # test values
    li t6 2 # load intermited to t6
    sw t6 0(t4) # store t6 in memory 
    li t6 4
    sw t6 4(t4)
    li t6 5
    sw t6 8(t4)
    li t6 6
    sw t6 12(t4)
    li t6 8
    sw t6 16(t4)
    li t6 -3
    sw t6 20(t4)
    li t6 -1
    sw t6 24(t4)

.min_max:    
    li t4 0x100000 # memory pointer start at 0x100000

    lw 		a1 0(t4)    # a1 = temp max 
    lw 		a0 0(t4)    # a0 = temp min 
    li      t2, 99 	    # number of loop's (length in memory - 1)
    li 		t3, 0	    # counter variable
    
    loop_head:
        blt     t2, t3, loop_end  # run loop while t2 < t3 (t2 == 101)
        
    # loop code
        lw 	t5 0(t4)		# load value from memory 
        ADDI t4 t4 4		# add 4 to memory pointer    
        ADDI t3 t3 1 		# add 1 to t3 (to end loop at a 100)
        
        # check max
        blt t5 a1 assign_min # if t5 > a1 then jump to assign_min (jump over assign max)
        ADD a1 zero t5       # assign max to a1
        
        assign_min:
        # check min
        bge t5 a0 loop_head  # if t5 < a0 then jump to loop_head (jump over assign min)
        ADD a0 zero t5       # assign min to a0

        j loop_head
    loop_end: