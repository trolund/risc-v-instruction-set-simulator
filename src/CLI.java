import picocli.CommandLine;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "RV32I", mixinStandardHelpOptions = true, version = "RV32I 1.0", description = "A basic RISC-V simulator. Supporting the RV32I instructions.")
public class CLI implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "Path to the bin file containing the program.")
    private String path;

    @CommandLine.Option(names = {"-p", "--print"}, description = "Will print the 32 registers to the console after each instruction. default: false.")
    private boolean print = false;

    @CommandLine.Option(names = {"-d", "--debug"}, description = "Will do debug printing. default: false.")
    private boolean debug = false;

    @CommandLine.Option(names = {"-u", "--dump"}, description = "Write data-dump file after execution. default: false.")
    private boolean dump = false;

    @CommandLine.Option(names = {"-r", "--result"}, description = "Print the result of the 32 registers after execution. default: true.")
    private boolean result = true;

    @CommandLine.Option(names = {"-e", "--ecall"}, description = "The register that 'ecall' uses to decide the kind of env call. default: a7.")
    private int ecall = 17;

    @Override
    public Integer call() throws IOException {
        ProgramLoader loader = new ProgramLoader();
        File file = new File(System.getProperty("user.dir") + this.path);
        int[] progr = loader.readBinFile(file);
        Path fileName = file.toPath().getFileName();
        ISASimulator vm = new ISASimulator(fileName.getFileName().toString(), print, debug, dump, ecall, result);
        vm.runProgram(progr);
        return 0;
    }

}
