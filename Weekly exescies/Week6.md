# Week 6

    Suggested exercises:
    4.17, 4.26, 4.27.

## 4.17 What is the minimum number of cycles needed to completely execute *n* instructions on a CPU with a *k* stage pipeline? Justify your formula.

    n + k − 1. 

Let’s look at when each instruction is in the WB stage. In a k-stage pipeline, the 1st instruction doesn’t enter the WB stage until cycle k. From that point on, at most one of the remaining n − 1 instructions is in the WB stage during every cycle.

This gives us a minimum of 

    k + (n − 1) = n + k − 1 cycles.

## 4.26

!!! missing

## 4.27

!!! missing