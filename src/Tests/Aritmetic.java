import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aritmetic {

    private ProgramLoader loader = new ProgramLoader();

    @Test
    public void add(){


        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("add"));

        int[] reg = vm.getReg();

        assertEquals(5, reg[3]);
    }

    @Test
    public void addi(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("addi"));

        int[] reg = vm.getReg();

        assertEquals(42, reg[11]);

    }

    @Test
    public void sub(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("sub"));

        int[] reg = vm.getReg();

        assertEquals(41, reg[11]);

    }

    @Test
    public void slt(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("slt"));

        int[] reg = vm.getReg();

        assertEquals(0, reg[11]);

    }

    @Test
    public void slt2(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("slt2"));

        int[] reg = vm.getReg();

        assertEquals(1, reg[11]);

    }

    @Test
    public void slti(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("slti"));

        int[] reg = vm.getReg();

        assertEquals(1, reg[11]);

    }

    @Test
    public void sltu(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("sltu"));

        int[] reg = vm.getReg();

        assertEquals(1, reg[12]);

    }

    @Test
    public void sltiu(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("sltiu"));

        int[] reg = vm.getReg();

        assertEquals(1, reg[12]);

    }

    @Test
    public void lui(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("lui"));

        int[] reg = vm.getReg();

        assertEquals(40960, reg[5]);

    }

    @Test
    public void auipc(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("auipc"));

        int[] reg = vm.getReg();

        assertEquals(40968, reg[13]); // pc moved 8 bit

    }

    @Test
    public void auipc2(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("auipc2"));

        int[] reg = vm.getReg();

        assertEquals(40960, reg[13]);

    }

}
