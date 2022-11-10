# Week 2

    Suggested exercises:
    2.1, 2.2, 2.3, 2.7, 2.8, 2.12, 2.14.

### 2.1 For the following C statement, write the corresponding RISC-V assembly code. Assume that the C variables f, g, and h, have already been placed in registers x5, x6, and x7 respectively. Use a minimal number of RISC-V assembly instructions.

    f = g + (h − 5);

    f = x5
    g = x6
    h = x7

---

    addi x5, x7, -5
    add x5, x5, x6
    
addi f,h,-5 (note, no subi) add f,f,g


### 2.2 Write a single C statement that corresponds to the two RISC-V assembly instructions below.

    add f, g, h
    add f, i, f

----

    f = g+h+i


### 2.3 For the following C statement, write the corresponding RISC-V assembly code. Assume that the variables f, g, h, i, and j are assigned to registers x5, x6, x7, x28, and x29, respectively. Assume that the base address of the arrays A and B are in registers x10 and x11, respectively.

    f = x5
    g = x6
    h = x7
    i = x28
    j = x29

    A = x10
    B = x11
---

    B[8] = A[i−j];
---

    sub x30, x28, x29 // compute i-j
    slli x30, x30, 3 // multiply by 8 to convert the word offset to a byte offset

    add x3, x30, x10  // add array pointer (address) with offset of the data 
    ld x30 0(x3)  // load A[i-j]
    sd x30, 64(x11)  // store in B[8]

### 2.7  Translate the following C code to RISC-V. Assume that the variables f, g, h, i, and j are assigned to registers x5, x6, x7, x28, and x29, respectively. Assume that the base address of the arrays A and B are in registers x10 and x11, respectively. Assume that the elements of the arrays A and B are 8-byte words:

    B[8] = A[i] + A[j];

    i = x28
    j = x29

    A = x10
    B = x11

---

    slli x28, x28, 3    // x28 = i*8
    add x10,x10,x28     // x10 = &A[i]
    ld x28, 0(x10)      // x28 = A[i]
    slli x29, x29, 3    // x29 = j*8
    add x12,x11,x29     // x12 = &B[j]
    ld x29,0(x12)       // x29 = B[j]
    add x29, x28, x29   // x29 = B[i]+B[j]
    sd x29, 64(x11)     // B[8] = B[i]+B[j]


### 2.8 Translate the following RISC-V code to C. Assume that the variables f, g, h, i, and j are assigned to registers x5, x6, x7, x28, and x29, respectively. Assume that the base address of the arrays A and B are in registers x10 and x11, respectively.

    addi x30, x10, 8 
    addi x31, x10, 0 
    sw x31, 0(x30) 
    lw x30, 0(x30) 
    add x5, x30, x31

--- 
C:

f = 2*(&A) 

description:

addi x30, x10, 8    // x30 = &A[1]
addi x31, x10, 0    // x31 = &A
sd x31, 0(x30)      // A[1] = &A
ld x30, 0(x30)      // x30 = A[1] = &A
add x5, x30, x31    // f = &A + &A = 2*(&A)

### 2.12 Provide the instruction type and assembly language instruction for the following binary value:

    (0000 0000 0001 0000 1000 0000 1011 0011)_2


    | funct7   | rs2    | rs1    | funct3 | rd     | opcode   |
    |----------|--------|--------|--------|--------|----------|
    | 0000000  | 00001  | 00001  | 000    | 00001  | 0110011  |

---

Look at FIGURE 2.18 RISC-V instruction encoding.

Find Opcode, then func fields!!

Result:

    R-type: add x1, x1, x1


### 2.14 Provide the instruction type, assembly language instruction, and binary representation of instruction described by the following RISC-V fields:

opcode=0x33, funct3=0x0, funct7=0x20, rs2=5, rs1=7, rd=6

rd = (6)_6 = (110)_2 

rs1 = (7)_6 = (111)_2 

rs2 = (5)_6 = (101)_2 

(0x40538333)_6 : (0100 0000 0101 0011 1000 0011 0011 0011)_2


    | funct7   | rs2    | rs1    | funct3 | rd     | opcode   |
    |----------|--------|--------|--------|--------|----------|
    | 0100000  | 00101  | 00111  | 000    | 00110  | 0110011  |

Look at FIGURE 2.18 RISC-V instruction encoding.

Find Opcode, then func fields!!

Result 

R-type: sub x6, x7, x5 