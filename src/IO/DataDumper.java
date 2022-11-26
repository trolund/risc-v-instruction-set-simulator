package IO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

public class DataDumper {

    public void writeFile(String name, int[] reg) throws IOException {
        File dir = new File("dump/");
        if (!dir.exists()){
            dir.mkdirs();
        }

        DataOutputStream os = new DataOutputStream(new FileOutputStream(getName(name)));

        //write the length first so that you can later know how many ints to read
        for (int i = 0 ; i < reg.length; ++i){
                int x = Integer.reverseBytes(reg[i]);
                os.writeInt(x);
        }
        os.close();
    }

    private String getName(String name){
        // Timestamp ts = new Timestamp(System.currentTimeMillis());
        String dumpPath = "dump/";
        return dumpPath + name + ".res";
    }

//    private byte[] toByte(int[] data) throws IOException {
//
//        byte[] bytes = new byte[data.length];
//        for (int i = 0; i < bytes.length; i++) {
//            bytes[i] = data[i];
//        }
//        return bytes;
//    }

}
