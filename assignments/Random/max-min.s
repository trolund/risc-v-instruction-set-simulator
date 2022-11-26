
main:
    li s0 4  
    li s1 400 

    li s2 0x100000 # load memmory address

    lw a0 0(s2) 
    lw a1 0(s2) 

loop_head:
    bge s0 s1 loop_end  
    add t0 s0 s2 
    lw t1 0(t0) 

    bge a0 t1 not_smaller 
    mv a0 t1 

    not_smaller:
        bge a1 t1 not_greater 
        mv a1 t1 

    not_greater:
        addi s0 s0 4 

j loop_head
loop_end: