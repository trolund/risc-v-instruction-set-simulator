# Week 4

    Suggested exercises:
        3.6, 3.7, 3.8, 3.12, 3.13, 3.22, 3.23, 3.30.


### General info about number representation

**overflow** - when the results of an operation are larger than be represented in a register

**overflow (floating- point)** - A situation in which a positive exponent becomes too large to fit in the exponent field.

**underflow** - (floating-point) A situation in which a negative exponent becomes too large to fit in the exponent field.

**fraction** - The value, generally between 0 and 1, placed in the fraction field. The fraction is also called the mantissa.

**exponent** - In the numerical representation system of floating-point arithmetic, the value that is placed in the exponent field.

![Alt text](../Test%20exsams/img/fp.png)

VIDEO: https://www.youtube.com/watch?v=RuKkePyo9zk

## 3.6

Assume 185 and 122 are unsigned 8-bit decimal integers. Calculate 185–122. Is there overflow, underflow, or neither?

185 - 122 = 63 = (111111)_2

The number 63 can be represented with 6-bit and there is therefore no overflow. therefore the answer is **neither**.

## 3.7

Assume 185 and 122 are signed 8-bit decimal integers stored in sign-magnitude format. Calculate 185 + 122. Is there overflow, underflow, or neither?

(signed integer value)

    185 = 1_0111001 = -57
    calculation = -57+122 
            
    10111001 <- -57
    01111010 <- +122
    --------
    01000001 <- 65 

65 can be represented by the 8-bit sign-magnitude format. Therefore the answer is **neither**.

Therefore no overflow. therefore the answer is **neither**.

## 3.8 

Assume 185 and 122 are signed 8-bit decimal integers stored in sign-magnitude format. Calculate 185 − 122. Is there overflow, underflow, or neither?

    185 = 1_0111001 = -57
    calculation = -57-122 
            
    10111001 <- -57
    01111010 <- +122
    --------
    110110011 <- -179 (8-bit without sign, 9-bit with sign)

-179 can not be represented with 8-bit decimal integers sign-magnitude format. Therefore there is a **overflow**.


## 3.12 ?? 

![Alt text](../Test%20exsams/img/Figure%203.6.png)

![Alt text](../Test%20exsams/img/fig.3.3.png)

Using a table similar to that shown in Figure 3.6, calculate the product of the octal unsigned 6-bit integers 62 and 12 using the hardware described in Figure 3.3. You should show the contents of each register on each step.

## 3.13 ?? 

Using a table similar to that shown in Figure 3.6, calculate the product of the octal unsigned 8-bit integers 62 and 12 using the hardware described in Figure 3.5. You should show the contents of each register on each step.

## 3.22 ?? 

What decimal number does the bit pattern 0×0C000000
represent if it is a floating point number? Use the IEEE 754 standard.

Use these pages:

* https://babbage.cs.qc.cuny.edu/ieee-754.old/32bit.html
* https://gregstoll.com/~gregstoll/floattohex/

0×0C000000 =
<=> 0000 1100 0000 0000 0000 0000 0000 0000 
<=> 0 0001 1000 0000 0000 0000 0000 0000 000

sign is positive
exp = 0 × 18 = 24 − 127 = −103

gregstoll result: +1 * 2^(24 - 127) * 1 = 1 × 2^−103

there is a hidden 1 mantissa = 0 answer = 1.0 × 2^−103

## 3.23

Write down the binary representation of the decimal number 63.25 assuming the IEEE 754 single precision format.

Use (float to hex):

* https://gregstoll.com/~gregstoll/floattohex/

Use (dec to hex):
* https://www.rapidtables.com/convert/number/decimal-to-binary.html

    will give: 111111.01
    (63.25)_10 = (3F.4)_6

63.25 × 100 = 111111.01 × 20

normalize, move binary point 5 to the left, 1.1111101 × 25

sign = positive (0), exp = 127 + 5 = 132

Final bit pattern: 
<=> 0 1000 0100 1111 1010 0000 0000 0000 000 
<=> 0100 0010 0111 1101 0000 0000 0000 0000 
<=> 0x427D0000

## 3.30 ?? 

Calculate the product of –8.0546875 × 10^0 and −1.79931640625 × 10^–1 by hand, assuming A and B are stored in the 16-bit half-precision format described in Exercise 3.27. 

Assume 1 guard, 1 round bit, and 1 sticky bit, and round to the nearest even. Show all the steps; however, as is done in the example in the text, you can do the multiplication in human-readable format instead of using the techniques described in Exercises 3.12 through 3.14. Indicate if there is overflow or underflow. Write your answer in both the 16-bit floating-point format described in Exercise 3.27 and also as a decimal number. How accurate is your result? How does it compare to the number you get if you do the multiplication on a calculator?

–8.0546875 × 10^0 = -1000.10000101100000111011      
−1.79931640625 × 10^–1