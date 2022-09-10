import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;

public class Dump {

    private static String dumpPath = "dump/";

    public void writeFile(int[] reg) throws IOException {
        File outputFile = new File(getName());
        Files.write(outputFile.toPath(), toByte(reg));
    }

    private String getName(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return dumpPath + ts.toString() + ".d";
    }

    private byte[] toByte(int[] data) throws IOException {

        byte[] bytes = new byte[data.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) data[i];
        }
        return bytes;
    }

}
