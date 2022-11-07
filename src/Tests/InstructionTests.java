import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InstructionTests {

    private ProgramLoader loader = new ProgramLoader();

    private void runTask() throws IOException {
        File[] binaryFiles  = loader.getAllFilesWithEx("TestPrograms/InstructionTests/","bin");
        File[] resFiles  = loader.getAllFilesWithEx("TestPrograms/InstructionTests/","res");

        int c = 0;
        int e = 0;

        for (int i = 0; i < binaryFiles.length; i++) {

            ISASimulator vm = new ISASimulator(true, false);

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

            if(testResult) {
                System.out.println("Correct ✅");
                c++;
            }else {
                System.out.println("Error ‼️");
                e++;
            }

           assertEquals(expectedReg, reg);
           assertTrue(testResult);
        }
        System.out.println("✅: " + c + " ‼️: " + e);
    }

    @Test
    public void task1() throws IOException {
        runTask();
    }

}
