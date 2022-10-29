import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class InstructionTests {

    private ProgramLoader loader = new ProgramLoader();

    private void runTask() throws IOException {
        File[] binaryFiles  = loader.getAllFilesWithEx("TestPrograms/InstructionTests/","bin");
        File[] resFiles  = loader.getAllFilesWithEx("TestPrograms/InstructionTests/","res");

        for (int i = 0; i < binaryFiles.length; i++) {

            ISASimulator vm = new ISASimulator(false);

            File bin = binaryFiles[i];
            File res = loader.findFileWithName(bin.getName(), resFiles);

            System.out.println(bin.getName() + " == " +  res.getName());

            int[] program = loader.readBinFile(bin);
            // test case
            vm.runProgram(program);

            int[] reg = vm.getReg();

            // assertEquals(5, reg[3]);
        }
    }

    @Test
    public void task1() throws IOException {
        runTask();
    }

}
