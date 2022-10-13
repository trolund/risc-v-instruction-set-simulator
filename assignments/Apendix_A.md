# A.2

digital = High (1, true) or low (0, false) voltage

    The values 0 and 1 are called complements or inverses of one another.


    Any logic function can be implemented with only AND, OR, and NOT functions.

to types of Ligic blocks:

* Blocks **without memory** are called **combinational logic**
  * The output of a combinational block depends only on the current input.
* Blocks **with memory** are called **sequential logic**
  * In blocks with memory, the outputs can depend on both the inputs and the value stored in memory, which is called the state of the logic block.

## Boolean Algebra

* The OR operator is written as +, as in A + B. The result of an OR operator is 1 if either of the variables is 1. The OR operation is also called a logical sum, since its result is 1 if either operand is 1.
  
* The AND operator is written as ·, as in A · B. The result of an AND operator is 1 only if both inputs are 1. The AND operator is also called logical product, since its result is 1 only if both operands are 1.
  
* The unary operator NOT is written as A (Med streg over). The result of a NOT operator is 1 only if the input is 0. Applying the operator NOT to a logical value results in an inversion or negation of the value (i.e., if the input is 0 the output is 1, and vice versa).

### manipulating logic equations:
https://www.mi.mun.ca/users/cchaulk/misc/boolean.htm

## Truth Tables

    For a logic block with n inputs, there are 2^n entries in the truth table

## Gates

* AND (less-space-ship :))
* OR (space-ship)
* NOT (triangle with bubble)
  
* XOR

The two common inverting (logical NOT) gates are called NOR and NAND and correspond to inverted OR and AND gates, respectively.

Rather than draw inverters (logical NOT) explicitly, a common practice is to add “bubbles” (circuls on the wire). 


## Decoders

The most common type of decoder has an n-bit input and 2^n outputs, where only one output is asserted for each input combination. 

Kunne fx. være en decoder der tager in instruction og splitter den op i de forskelige dele den indeholder så som opcode, func, imm osv.


## Multiplexors (Selector)

### Simple

A multiplexor might more properly be called a selector, since its output is one of the inputs that is selected by a control.

Consider the two-input multiplexor.  Then the multiplexor has three inputs: two data values and a selector (or control) value.

The selector value determines which of the inputs becomes the output.

------------------------

### General

Multiplexors can be created with an arbitrary number of data inputs. When there are only two inputs, the selector is a single signal that selects one of the inputs if it is true (1) and the other if it is false (0). If there are n data inputs, there will need to be |log2n|selector inputs.


In this case, the multiplexor basically consists of three parts:

1. A decoder that generates n signals, each indicating a different input value
   
2. An array of n AND gates, each combining one of the inputs with a signal
from the decoder

3. A single large OR gate that incorporates the outputs of the AND gates


## Two-Level Logic and PLAs

Any logic function can be written in a canonical form, where every input is either a true or complemented variable and there are only two levels of gates—one being AND and the other OR—with a possible inversion on the final output.

A **PLA** has a set of inputs and corresponding input complements (which can be implemented with a set of inverters), and two stages of logic.


--> page A-12