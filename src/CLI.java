public class CLI {

    public static void main(String[] args) {

        ProgramLoader loader = new ProgramLoader();
        IsaSim vm = new IsaSim();

        // test case
        vm.runProgram(loader.loadTest("first", ProgramLoader.ProgramType.BINARY));

        // run all programs added
        for (String fileUrl: args) {
            int[] progr = loader.loadProgram(fileUrl);
            vm.runProgram(progr);
        }
    }

}
