import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ecall {

    private final ProgramLoader loader = new ProgramLoader();

    @Test
    public void ecallTest(){

        ISASimulator vm = new ISASimulator();

        // test case
        vm.runProgram(loader.loadTest("ecall"));

        int[] reg = vm.getReg();

        assertEquals(0, 0);

    }

}
