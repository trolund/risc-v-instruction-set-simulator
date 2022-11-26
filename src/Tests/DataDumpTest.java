import Core.ISASimulator;
import IO.ProgramLoader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataDumpTest {

    private final ProgramLoader loader = new ProgramLoader();

    @Test
    public void dataDumpString() throws IOException {
        String name = "string";

        ISASimulator vm = new ISASimulator(true, true, true);

        // test case
        File bin = loader.getFilesWithExFirst("TestPrograms/DUMP/", "string", "bin");
        vm.runProgram(loader.readBinFile(bin), name);

        int[] dumpReg = loader.readBinFile(new File("dump/" + name + ".res"));

        int[] reg = vm.getReg();

        boolean b = Arrays.equals(reg, dumpReg);
        assertTrue(b);
    }

    @Test
    public void dataDumpLoop() throws IOException {
        String name = "loop";

        ISASimulator vm = new ISASimulator(true, true, true);

        // test case
        File bin = loader.getFilesWithExFirst("TestPrograms/DUMP/", "loop", "bin");
        vm.runProgram(loader.readBinFile(bin), name);

        int[] dumpReg = loader.readBinFile(new File("dump/" + name + ".res"));

        int[] reg = vm.getReg();

        boolean b = Arrays.equals(reg, dumpReg);
        assertTrue(b);
    }

}
