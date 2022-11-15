import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class main {

    public static void main(String[] args) throws IOException {

        String fileName = "./string.bin";
        File file = new File(fileName);

        byte [] fileBytes = Files.readAllBytes(file.toPath());
        char singleChar;
        for(byte b : fileBytes) {
            singleChar = (char) b;
            System.out.print(singleChar);
        }
    }

}
