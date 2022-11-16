import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;

public class DataDumper {

    public void writeFile(String name, int[] reg) throws IOException {
        File outputFile = new File(getName(name));
        Files.write(outputFile.toPath(), toByte(reg));
    }

    private String getName(String name){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String dumpPath = "./dump/";
        return dumpPath + name + "_" + ts + ".res";
    }

    private byte[] toByte(int[] data) throws IOException {

        byte[] bytes = new byte[data.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) data[i];
        }
        return bytes;
    }

}
