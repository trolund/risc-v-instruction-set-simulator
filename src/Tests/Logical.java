import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Logical {

    private ProgramLoader loader = new ProgramLoader();

    @Test
    public void and(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("and"));

        int[] reg = vm.getReg();

        assertEquals(0, reg[3]);

    }

    @Test
    public void and2(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("and2"));

        int[] reg = vm.getReg();

        assertEquals(1, reg[3]);

    }

    @Test
    public void or(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("or"));

        int[] reg = vm.getReg();

        assertEquals(0, reg[3]);

    }

    @Test
    public void or2(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("or2"));

        int[] reg = vm.getReg();

        assertEquals(1, reg[3]);

    }

    @Test
    public void xor(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("xor"));

        int[] reg = vm.getReg();

        assertEquals(0, reg[3]);

    }

    @Test
    public void xor2(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("xor2"));

        int[] reg = vm.getReg();

        assertEquals(1, reg[3]);

    }

    @Test
    public void xor3(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("xor3"));

        int[] reg = vm.getReg();

        assertEquals(0, reg[3]);

    }

    @Test
    public void andi(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("andi"));

        int[] reg = vm.getReg();

        assertEquals(0, reg[3]);

    }

    @Test
    public void andi2(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("andi2"));

        int[] reg = vm.getReg();

        assertEquals(1, reg[3]);

    }

    @Test
    public void ori(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("ori"));

        int[] reg = vm.getReg();

        assertEquals(0, reg[3]);

    }

    @Test
    public void ori2(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("ori2"));

        int[] reg = vm.getReg();

        assertEquals(1, reg[3]);

    }

    @Test
    public void xori(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("xori"));

        int[] reg = vm.getReg();

        assertEquals(1, reg[3]);

    }
}
