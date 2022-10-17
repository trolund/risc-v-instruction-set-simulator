# Week 1

    Suggested exercises:
    1.3, 1.4, 1.5, 1.6, 1.9, 1.12(1,3,4,6).

### Describe the steps that transform a program written in a high-level language such as C into a representation that is directly executed by a computer processor.

The C source code vil first be **complied** to assembly code for the specific architecture of the machine. The compiler uses a lot of techniques to optimize the code. After this the after this the **assembler** translate the code into binary code. 


### Assume a color display using 8 bits for each of the primary colors (red, green, blue) per pixel and a frame size of 1280 × 1024.

#### a. What is the minimum size in bytes of the frame buffer to store a frame?

8 bit = 1 byte

    1280 × 1024 = 1,310,720 pixels

    1,310,720 × 3 = 3,932,160 bytes/frame

#### b. How long would it take, at a minimum, for the frame to be sent over a 100 Mbit/s network?

Mega = 10^6

    3,932,160 × 8 / (100 * 10^6) = 0.31 seconds


### 1.5 [5] Consider the table below, which tracks several performance indicators for Intel desktop processors since 2010.

    The “Tech” column shows the minimum feature size of each processor’s fabrication process. Assume that die size has remained relatively constant, and the number of transistors comprised in each processor scales at (1/t)2, where t = the minimum feature size.

    For each performance indicator, calculate the average rate of improvement from 2010 to 2019 as well as the number of years required to double each at that corresponding rate.


    


|Desktop processor   |Year|Toch |Max. clock speed (GHz)|Integer IPC/ core|Cores|Max. DRAM Bandwidth (GB/s)|SP floating point (Gflop/s)|L3 cache (MIB)|
|--------------------|----|-----|----------------------|-----------------|-----|--------------------------|---------------------------|--------------|
|Westmere i7-620     |2010|32   |3.33                  |4                |2    |17.1                      |107                        |4             |
|Ivy Bridge i7-3770K |2013|22   |3.90                  |6                |4    |25.6                      |250                        |8             |
|Broadwell i7-6700K  |2015|14   |4.20                  |8                |4    |34.1                      |269                        |8             |
|Kaby Lake i7-7700K  |2017|14   |4.50                  |8                |4    |38.4                      |288                        |8             |
|Coffee Lake i7-9700K|2019|14   |4.90                  |8                |8    |42.7                      |627                        |12            |
|Imp./year           |    |%    |_%                    |%                |%    |%                         |%                          |_%            |
|Doubles every       |    |years|years                 |years            |years|years                     |years                      |years         |


### 1.6 [4] <§1.6> Consider three different processors P1, P2, and P3 executing the same instruction set. P1 has a 3GHz clock rate and a CPI of 1.5. P2 has a 2.5GHz clock rate and a CPI of 1.0. P3 has a 4.0GHz clock rate and has a CPI of 2.2.

#### a. Which processor has the highest performance expressed in instructions per second?

    Instructions per second = Clock rate / CPI
    3GHz == 3 × 10^9 (GHz)

Performance of each processor:
  * P1 = 3 × 10^9 / 1.5 = 2 × 10^9 instructions/sec
  * P2 = 2.5 × 10^9 / 1.0 = 2.5 × 10^9 instructions/sec
  * P3 = 4 × 10^9 / 2.2 = 1.8 × 10^9 instructions/sec

#### b. If the processors each execute a program in 10 seconds, find the number of cycles and the number of instructions.

**Number of cycles**

    Number of cycles = time in sec * Clock rate

  * cycles(P1) = 10s × 3 × 10^9 = 30 × 10^9 s 
  * cycles(P2) = 10s × 2.5 × 10^9 = 25 × 10^9 s 
  * cycles(P3) = 10s × 4 × 10^9 = 40 × 10^9 s

**Number of instructions** 

    No. instructions = number of cycles / CPI

  * No. instructions(P1) = 30 × 10^9 / 1.5 = 20 × 10^9
  * No. instructions(P2) = 25 × 10^9 / 1 = 25 × 10^9
  * No. instructions(P3) = 40 × 10^9 / 2.2 = 18.18 × 10^9

#### c. We are trying to reduce the execution time by 30%, but this leads to an increase of 20% in the CPI. What clock rate should we have to get this time reduction?

First the new CPI is found.

CPI_new = CPI_old × 1.2, then 
   - CPI(P1) = 1.8, 
   - CPI(P2) = 1.2,
   - CPI(P3) = 2.6 

Then the number of instructions found in b is used and multiplied by the new CPI.  

    time = 10s * (1 - 0.30) = 7s

f = No. instr. × CPI / time, then
   - f(P1) = 20 × 10^9 × 1.8 / 7s = 5.14GHz 
   - f(P2) = 25 × 10^9 × 1.2 / 7s = 4.28GHz 
   - f(P3) = 18.18 × 10^9 × 2.6 / 7s = 6.75 GHz

### 1.9 The Pentium 4 Prescott processor, released in 2004, had a clock rate of 3.6 GHz and voltage of 1.25 V. 

Assume that, on average, it consumed 10 W of static power and 90 W of dynamic power.

The Core i5 Ivy Bridge, released in 2012, has a clock rate of 3.4GHz and voltage of 0.9 V. Assume that, on average, it consumed 30 W of static power and 40 W of dynamic power.

