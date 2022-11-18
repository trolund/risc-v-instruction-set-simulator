import picocli.CommandLine;
import java.io.File;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "RV32I", mixinStandardHelpOptions = true, version = "RV32I 1.0", description = "")
public class CLI implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "The file containing the program.")
    private File file;

    @CommandLine.Option(names = {"-p", "--print"}, description = "Will print the 32 registers to the console after each instruction.")
    private boolean[] print;

    @CommandLine.Option(names = {"-d", "--debug"}, description = "Will do debug printing.")
    private boolean[] debug;

    @CommandLine.Option(names = {"-u", "--dump"}, description = "Write data-dump file after execution.")
    private boolean[] dump;

    @Override
    public Integer call() {
        ProgramLoader loader = new ProgramLoader();
        int[] progr = loader.loadProgram(file.getPath());
        ISASimulator vm = new ISASimulator(file.getName(), print[0], debug[0], dump[0]);
        vm.runProgram(progr);
        return 0;
    }

}
