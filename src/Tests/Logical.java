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
}
