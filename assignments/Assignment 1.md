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
```assembly
ADDI x4 zero -16
ADDI x3 zero 8
SUB x2, zero, x4  # x2 ← zero - x4 # (Get negation of number)
REM x1 x2 x3
```


### b. Extract the 5 least-significant bits from register x5 and put the 5 extracted bits in register x1 (other bits in x1 must be zero). Write the RISC-V code for two different ways of doing it. Use a different set of instructions in the two cases.


```assembly
li x5 input        # load input into x5
li x2 0x1F         # load immediate with the mask
and x5 x5 x2       # apply mask to reg x5 
```


https://stackoverflow.com/questions/64747340/how-can-i-extract-bits-in-risc-v-assembly-code


# Exercise A1.2


The code was tested using the code in .test_data and t2 is set to the length of the memory vector - 1. When running the code a0 is set to -3 and a1 is set to 8.


```assembly
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

        lw 		t0 0(t4)    # t0 = temp max 
        lw 		t1 0(t4)    # t1 = temp min 
        li      t2, 99 	     # number of loop's (length in memory - 1)
        li 		t3, 0	     # counter variable
        
        loop_head:
        blt     t2, t3, loop_end  # run loop while t2 < t3 (t2 == 101)
        
        # Repeated code goes here
        lw 	t5 0(t4)		# load value from memory 
        ADDI t4 t4 4		# add 4 to memory pointer    
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
```

# Exercise A1.3

