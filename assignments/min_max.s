.test_data:
	li t4 0x100000 # memmory start
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
    li t6 3
    sw t6 20(t4)
    li t6 1
    sw t6 24(t4)
.max:    
    li t4 0x100000 # memmory start at 0x100000

    lw 		t0 0(t4)    # t0 = temp max 
    lw 		t1 0(t4)    # t1 = temp min 
    li      t2, 6 	    # number of loop's (length in memory - 1)
    li 		t3, 0		# counter vaiable
    
    loop_head:
    blt     t2, t3, loop_end  # run loop while t2 < t3 (t2 == 101)
    
    # Repeated code goes here
    lw 	t5 0(t4)		# load value from memory 
    ADDI t4 t4 4		# add 4 to memmory pointer    
    ADDI t3 t3 1 		# add 1 to t3 (to end loop at a 100)
    
    # check max
    blt t5 t0 assign_min # if t5 > t0 then jump to assign_min (jump over assign max)
    ADD t0 zero t5       # assign max to t0
    
    assign_min:
    # check min
    bge t5 t1 loop_head  # if t5 < t1 then jump to loop_head (jump over assign min)
    ADD t1 zero t5       # assign min to t1

    j		loop_head
    loop_end:
    # Repeated code ends here

    # return data
    ADD a0 zero t1 # set a0 to the minimum value 
    ADD a1 zero t0 # set a01 to the maximum value 
    
