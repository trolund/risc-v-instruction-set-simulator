import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataDumpTest {

    private final TestUtil test = new TestUtil();
    private final ProgramLoader loader = new ProgramLoader();

    @Test
    public void dataDump() throws IOException {
        ISASimulator vm = new ISASimulator("string_dump", true, true, true);

        // test case
        File bin = loader.getFilesWithExFirst("TestPrograms/DUMP/", "string", "bin");
        vm.runProgram(loader.readBinFile(bin));

        int[] reg = vm.getReg();

        assertTrue(false); // TODO - finish test

    }

}
