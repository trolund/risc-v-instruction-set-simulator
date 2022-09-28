# Exercise A1.1

### a. Write the RISC-V segment of code to detect if a negative integer number, stored in register x4, is a multiple of 8. If this condition is verified, set register x1 to zero.


#### Answer

First ADDI is used just load in the input in x4, and then the number 8. 

after that the code:

```
    SUB x2, zero, x4  # x2 ← zero - x4
```

Is used to negate the number (This is properly not nesasery?)

x1 is zero when x4, is a multiple of 8 other wise it is the number of remainers.

#### Code
```risc-v
ADDI x4 zero -16
ADDI x3 zero 8
SUB x2, zero, x4  # x2 ← zero - x4 # (Get negation of number)
REM x1 x2 x3
```


### b. Extract the 5 least-significant bits from register x5 and put the 5 extracted bits in register x1 (other bits in x1 must be zero). Write the RISC-V code for two different ways of doing it. Use a different set of instructions in the two cases.


```risc-v
li x5 input        # load input into x5
li x2 0x1F         # load immediate with the mask
and x5 x5 x2       # apply mask to reg x5 
```


https://stackoverflow.com/questions/64747340/how-can-i-extract-bits-in-risc-v-assembly-code



´´´risc-v
.min_max:    
	li t4 0x100000 # memmory start
    
    # t0 = max 
    lw 		t0 0(t4)
    
    li      t2, 101
    li 		t3, 0
    
    # test values
    li t6 200
    sw t6 0(t4)
    li t6 400
    sw t6 4(t4)
    
    loop_head:
    blt     t2, t3, loop_end
    # Repeated code goes here
    lw 		t5 0(t4)
    ADDI t4 t4 4
    ADDI t3 t3 1
    
    blt t5 t0 loop_head
  
    # assign max
    assign:
    ADD t0 zero t5
    

    j		loop_head
    loop_end:
´´´


´´´riscv
.test_data:
    # test values
    li t6 2
    sw t6 0(t4)
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
	li t4 0x100000 # memmory start
    

    lw 		t0 0(t4)    # t0 = max 
    lw 		t1 0(t4)    # t1 = min 
    li      t2, 101 	# number of loop's (length in memory)
    li 		t3, 0		# counter vaiable
    
    loop_head:
    blt     t2, t3, loop_end  # run loop while t2 < t3 (t2 == 101)
    
    # Repeated code goes here
    lw 		t5 0(t4)	# load value from memory 
    ADDI t4 t4 4		# add 4 to memmory pointer    
    ADDI t3 t3 1 		# add 1 to t3 (to end loop at a 100)
    
    # check max
    blt t5 t0 assign_min # if t5 < t0 then assign else jump to assign_min (jump over assign max)
    ADD t0 zero t5       # assign max
    
    assign_min:
    # check min
    blt t0 t5 loop_head  # if t5 > t0 then assign else jump to loop_head (jump over assign min)
    ADD t1 zero t5       # assign min

    j		loop_head
    loop_end:
    # Repeated code ends here
´´´
