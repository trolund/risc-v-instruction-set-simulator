import org.junit.jupiter.api.Test;

public class test1 {

    @Test
    public void test1(){

        ProgramLoader loader = new ProgramLoader();
        ISASimulator testvm = new ISASimulator();

        // test case
        testvm.runProgram(loader.loadTest("first", ProgramLoader.ProgramType.BINARY));

    }

}
