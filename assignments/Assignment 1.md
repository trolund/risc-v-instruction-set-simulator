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




