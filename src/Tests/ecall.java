import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ecall {

    private ProgramLoader loader = new ProgramLoader();

    @Test
    public void ecall(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("ecall"));

        int[] reg = vm.getReg();

        assertEquals(0, 0);

    }

}
