# Week 3

    Suggested exercises:
        2.25, 2.26, 2.27, 2.28, 2.40.


## 2.25

Translate the following C code to RISC-V assembly code. Use a minimum number of instructions. Assume that the values of a, b, i, and j are in registers x5, x6, x7, and x29, respectively. Also, assume that register x10 holds the base address of the array D.

a = x5
b = x6
i = x7
j = x29

array base = x10

```
for(i=0; i<a; i++)
        for(j=0; j<b; j++)
           D[4*j] = i + j;
```

Answer:

![Alt text](../Test%20exsams/img/Screenshot%202022-11-26%20at%2019.39.52.png)

## 2.26 ?? (vent)

How many RISC-V instructions does it take to implement the C code from Exercise 2.25? If the variables a and b are initialized to 10 and 1 and all elements of D are initially 0, what is the total number of RISC-V instructions executed to complete the loop?

    Answers will vary, but will generally require 12 to 13 RISC-V instructions and 123 to 132 instructions executed for a = 10 and b = 1.


## 2.27

Translate the following loop into C. Assume that the C-level integer i is held in register x5,x6 holds the C-level integer called result, and x10 holds the base address of the integer *MemArray*.

      addi x6, x0, 0
      addi x29, x0, 100 
LOOP: lw x7, 0(x10) 
      add x5, x5, x7
      addi x10, x10, 8 
      addi x6, x6, 1
      blt x6, x29, LOOP

Answer:

![Alt text](../Test%20exsams/img/Screenshot%202022-11-26%20at%2019.42.31.png)
    
## 2.28

Rewrite the loop from Exercise 2.27 to reduce the number of RISC-V instructions executed. 

Hint: Notice that variable *i* is used only for loop control.


The address of the last element of MemArray can be used to terminate the loop:
add x29, x10, 800 // x29 = &MemArray[101]

Answer:

    LOOP:
        ld x7, 0(x10)
        add x5, x5, x7
        addi x10, x10, 8
        blt x10, x29, LOOP // Loop until MemArray points to one-past the last element



## 2.40

Assume that for a given program 70% of the executed instructions are arithmetic, 10% are load/store, and 20% are branch.

### 2.40.1

Given this instruction mix and the assumption that an arithmetic instruction requires two cycles, a load/store instruction takes six cycles, and a branch instruction takes three cycles, find the average CPI.

![Alt text](../Test%20exsams/img/avg_cpi.png)

Answer:

(2 * 0.7) + (6 * 0.1) + (3 * 0.2) = 2.6 CPI

### 2.40.2 

For a 25% improvement in performance, how many cycles, on average, may an arithmetic instruction take if load/store and branch instructions are not improved at all?

Answer:

For a 25% improvement, we must reduce the CPU to 2.6 * 0.75 = 1.95.

Thus, we want 0.7*x + 0.1*6 + 0.2*3 <= 1.95. Solving for x shows that the arithmetic instructions must have a CPI of at most 1.07.

In maple : solve(0.7*x + 0.1*6 + 0.2*3 = 1.95, x)

### 2.40.3

 For a 50% improvement in performance, how many cycles, on average, may an arithmetic instruction take if load/store and branch instructions are not improved at all?


 For a 50% improvement, we must reduce the CPU to 2.6 * 0.50 = 1.3.

 Thus, we want 0.7*x + 0.1*6 + 0.2*3 <= 1.3. Solving for x shows that the arithmetic instructions must have a CPI of at most 0.14.

 In maple : solve(0.7*x + 0.1*6 + 0.2*3 = 1.3, x)