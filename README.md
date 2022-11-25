# InstructionSetSimulator

RISC-V simulator implementing the RV32I Instructions.

The project is done as part of the course 02155 Computer Architecture and Engineering at DTU.

RV32I Instructions info:
https://msyksphinz-self.github.io/riscv-isadoc/html/rvi.html#lbu
https://itnext.io/risc-v-instruction-set-cheatsheet-70961b4bbe8

## RV32I usage

The CLI can be used by using the compiled jar file *rv32i.jar* like this: 

    java -cp "picocli-4.7.0.jar:rv32i.jar" RV32I

The CLI have a number of options that can be seen by using the command:

    RV32I --help 

this will result in this info:

    Usage: RV32I [-dhpruV] [-e=<ecall>] [-er=<ecallReg>] <path>
    A basic RISC-V simulator. Supporting the RV32I instructions.
    <path>            Path to the bin file containing the program.
    -d, --debug           Will do debug printing. default: false.
    -e, --ecall=<ecall>   The register that 'ecall' uses to decide the kind of
    env call. default: a7.
    -er, --ecallreg=<ecallReg>
    The register that 'ecall' uses to as input. default: a0.
    -h, --help            Show this help message and exit.
    -p, --print           Will print the 32 registers to the console after each
    instruction. default: false.
    -r, --result          Print the result of the 32 registers after execution.
    default: true.
    -u, --dump            Write data-dump file after execution. default: false.
    -V, --version         Print version information and exit.


## ecall config

### Venus:
* ecall = 10 
* ecallReg = 11 

https://github.com/kvakil/venus/wiki/Environmental-Calls

### Ripes:
* ecall = 17
* ecallReg = 10

https://github.com/mortbopet/Ripes/blob/master/docs/ecalls.md

## Java version

openjdk 18.0.2.1 2022-08-18
OpenJDK Runtime Environment (build 18.0.2.1+1-1)
OpenJDK 64-Bit Server VM (build 18.0.2.1+1-1, mixed mode, sharing)

## Test cases

| Test   cases  |            | ✅/❌️ |
|---------------|------------|------|
| Task 1        |            | ✅    |
|               | addneg     | ✅    |
|               | addpos     | ✅    |
|               | addlarge   | ✅    |
|               | shift2     | ✅    |
|               | set        | ✅    |
|               | bool       | ✅    |
|               | shift      | ✅    |
| Task 2        |            | ✅    |
|               | branchcnt  | ✅    |
|               | branchmany | ✅    |
|               | branchtrap | ✅    |
| Task 3        |            | ✅    |
|               | string     | ✅    |
|               | loop       | ✅    |
|               | width      | ✅    |
|               | recursive  | ✅    |
| Task 4        |            | ✅    |
|               | t1         | ✅    |
|               | t2         | ✅    |
|               | t3         | ✅    |
|               | t4         | ✅    |
|               | t5         | ✅    |
|               | t6         | ✅    |
|               | t7         | ✅    |
|               | t8         | ✅    |
|               | t9         | ✅    |
|               | t10        | ✅    |
|               | t11        | ✅    |
|               | t12        | ✅    |
|               | t13        | ✅    |
|               | t14        | ✅    |
|               | t15        | ✅    |