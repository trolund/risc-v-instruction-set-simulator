import IO.FileReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseTests {

    private ProgramLoader loader = new ProgramLoader();

    private void runTask(int taskID) throws IOException {
        File[] binaryFiles  = loader.getAllFilesWithEx("TestPrograms/BINARY/task" + taskID + "/","bin");
        File[] resFiles  = loader.getAllFilesWithEx("TestPrograms/BINARY/task" + taskID + "/","res");

        for (int i = 0; i < binaryFiles.length; i++) {

            ISASimulator vm = new ISASimulator(true);

            File bin = binaryFiles[i];
            File res = loader.findFileWithName(bin.getName(), resFiles);

            System.out.println(bin.getName() + " == " +  res.getName());

            int[] program = loader.readBinFile(bin);
            // test case
            vm.runProgram(program);

            int[] reg = vm.getReg();

            int[] expectedReg = loader.readBinFile(res);

            System.out.println("---- res -----");
            for (int r: expectedReg) {
                System.out.print(r + " ");
            }
            System.out.println();

            boolean testResult = Arrays.equals(expectedReg, reg);

            assertEquals(expectedReg, reg);
            assertTrue(testResult);
        }
    }

    @Test
    public void task1() throws IOException {
        runTask(1);
    }

    @Test
    public void task2() throws IOException {
        runTask(2);
    }

    @Test
    public void task3() throws IOException {
        runTask(3);
    }

    @Test
    public void task4() throws IOException {
        runTask(4);
    }
}
