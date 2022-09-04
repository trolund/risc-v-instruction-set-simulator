import java.io.*;
import java.util.ArrayList;

public class ProgramLoader {

    enum ProgramType {
        ASSEMBLY,
        BINARY
    }

    public void loadTest(String programName) {
        loadTest(programName, ProgramType.ASSEMBLY);
    }

    public int[] loadTest(String programName, ProgramType programType) {
        InputStream inputStream = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("TestPrograms/" + programType + "/" + programName + ".txt").getFile());
            inputStream = new FileInputStream(file);
            return transformInputStream(inputStream);
        }catch (Exception e) {
            System.out.println(e);
            System.err.println("Failed to load program: " + programName);
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new int[0];
    }

    public int[] loadProgram(String url) {
        InputStream inputStream = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(url);
            inputStream = new FileInputStream(file);
            return transformInputStream(inputStream);
        }catch (Exception e) {
            System.out.println(e);
            System.err.println("Failed to load program: " + url);
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new int[0];
    }

    private int[] transformInputStream(InputStream inputStream) throws IOException {
        ArrayList program = new ArrayList<Integer>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                int processedInput = processLine(line);
                if(processedInput > 0) program.add(processedInput);
            }
        }
        return program.stream().mapToInt(i -> (int) i).toArray();
    }

    private int processLine(String line) {
        if(line.isBlank() || line.isEmpty() || line.charAt(0) == '/') return -1; // discard line
        String[] parts = line.split("//");
        return Integer.decode(parts[0].trim());
    }
}
