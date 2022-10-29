import IO.FileReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTests {

    private ProgramLoader loader = new ProgramLoader();

    private void runTask(int taskID) throws IOException {
        File[] binaryFiles  = loader.getAllFilesWithEx("TestPrograms/BINARY/task" + taskID + "/","bin");
        File[] resFiles  = loader.getAllFilesWithEx("TestPrograms/BINARY/task" + taskID + "/","res");

        for (int i = 0; i < binaryFiles.length; i++) {

            ISASimulator vm = new ISASimulator();

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
