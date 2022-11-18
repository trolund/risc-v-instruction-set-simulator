import picocli.CommandLine;

public class RV32I {

    public static void main(String... args) {
        int exitCode = new CommandLine(new CLI()).execute(args);
        System.exit(exitCode);
    }

}
