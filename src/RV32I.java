public class RV32I {

    public static void main(String[] args) {

        ProgramLoader loader = new ProgramLoader();
        ISASimulator testvm = new ISASimulator();

        // test case
        testvm.runProgram(loader.loadTest("first", ProgramLoader.ProgramType.BINARY));

        // run all programs added run on their own VM
        for (String fileUrl: args) {
            ISASimulator vm = new ISASimulator();
            int[] progr = loader.loadProgram(fileUrl);
            vm.runProgram(progr);
        }
    }

}
