import Core.ISASimulator;
import Core.TUIColors;
import IO.ProgramLoader;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUtil {

    private final ProgramLoader loader = new ProgramLoader();
    private final TUIColors color = new TUIColors();

    boolean runTestWithName(int task, String name, ISASimulator mashine) throws IOException {
        File bin = loader.getFilesWithExFirst("TestPrograms/BINARY/task" + task + "/", name, "bin");
        File res = loader.getFilesWithExFirst("TestPrograms/BINARY/task" + task + "/", name,"res");
        return runTest(bin, res, mashine, name);
    }

    boolean runTestWithName(int task, String name) throws IOException {
        File bin = loader.getFilesWithExFirst("TestPrograms/BINARY/task" + task + "/", name, "bin");
        File res = loader.getFilesWithExFirst("TestPrograms/BINARY/task" + task + "/", name,"res");
        return runTest(bin, res, null, name);
    }

    private String withOutExt(String s){
        return s.split("\\.")[0];
    }

    private boolean runTest(File bin, File res, ISASimulator mashine, String name) throws IOException {
        ISASimulator vm;
        if(mashine == null){
            vm = new ISASimulator(true, true, false);
        }else {
            vm = mashine;
        }

        System.out.println(color.colorText( "💡 Comparing output of " + bin.getName() + " with " + res.getName(), TUIColors.YELLOW_BACKGROUND));

        int[] program = loader.readBinFile(bin);
        // test case
        vm.runProgram(program, name);

        int[] reg = vm.getReg();

        int[] expectedReg = loader.readBinFile(res);

        System.out.println("---- res -----");
        for (int r: expectedReg) {
            System.out.print(color.colorText(r + " ", TUIColors.PURPLE_BACKGROUND));
        }
        System.out.println();

        return Arrays.equals(expectedReg, reg);
    }

    void runTask(int taskID) throws IOException {
        File[] binaryFiles  = loader.getAllFilesWithEx("TestPrograms/BINARY/task" + taskID + "/","bin");
        File[] resFiles  = loader.getAllFilesWithEx("TestPrograms/BINARY/task" + taskID + "/","res");

        int c = 0;
        int e = 0;

        for (int i = 0; i < binaryFiles.length; i++) {
            File res = loader.findFileWithName(binaryFiles[i].getName(), resFiles);
            boolean testResult = runTest(binaryFiles[i], res, null, withOutExt(res.getName()));
            if(testResult) {
                System.out.println("Correct ✅");
                c++;
            }else {
                System.out.println("Error ‼️");
                e++;
            }
            assertTrue(testResult);
        }

        System.out.println("------ END RESULT ------");
        System.out.println("✅: " + c + " ‼️: " + e);
    }


}
