package Core;

public class Config {

    private boolean printReg = false;
    private boolean debug = false;
    private boolean dumpData = false;
    private int ecallAction = 17;
    private int ecallArg = 10;
    private boolean exitPrint = false;

    public Config() {
    }

    public Config(boolean printReg, boolean debug, boolean dumpData, int ecallAction, int ecallArg, boolean exitPrint) {
        this.printReg = printReg;
        this.debug = debug;
        this.dumpData = dumpData;
        this.ecallAction = ecallAction;
        this.ecallArg = ecallArg;
        this.exitPrint = exitPrint;
    }

    public Config(boolean printReg, boolean debug, boolean dumpData) {
        this.printReg = printReg;
        this.debug = debug;
        this.dumpData = dumpData;
    }

    public boolean isPrintReg() {
        return printReg;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isDumpData() {
        return dumpData;
    }

    public int getEcallAction() {
        return ecallAction;
    }

    public int getEcallArg() {
        return ecallArg;
    }

    public boolean isExitPrint() {
        return exitPrint;
    }
}
