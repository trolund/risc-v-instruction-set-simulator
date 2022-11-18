package IO;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class ProgramLoader {

    enum ProgramType {
        ASSEMBLY,
        BINARY,
        TEXT,
        DUMP
    }

    public int[] loadTest(String programName) {
        return loadTest(programName, ProgramType.TEXT, ".txt");
    }

    public int[] loadTest(String programName, ProgramType programType) {
        return loadTest(programName, programType, ".txt");
    }

    public int[] loadTest(String programName, ProgramType programType, String ex) {
        InputStream inputStream = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("TestPrograms/" + programType + "/" + programName + ex)).getFile());
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
            System.out.println(System.getProperty("user.dir") + url);
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

    public File getFilesWithExFirst(String path, String filename, String ex){
        File[] files = getFilesWithEx(path, ex);

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if(file.getName().equals(filename + "." + ex) && file.getName().endsWith("." + ex)){
                return file;
            }
        }

        return null;
    }

    public File[] getFilesWithEx(String path, String ex) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(path)).getFile());

        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File f, String name) {
                return name.endsWith("." + ex);
            }
        };

        return file.listFiles(filter);
    }

    public File[] getAllFilesWithEx(String path, String ex) {
        try {
                return getFilesWithEx(path, ex);
             }catch (Exception e) {
            System.out.println(e);
            }
        return null;
    }

    private String getFileName(String filename){
        String[] parts = filename.split("\\.");
        return parts[0];
    }

    public File findFileWithName(String filename, File[] files){

        String name = getFileName(filename);

        for(File f: files){
            if(getFileName(f.getName()).equals(name)) {
                return f;
            }
        }

        return null;
    }

    public int[] readBinFile(File file) throws IOException {
        ArrayList<Integer> program = new ArrayList<>();
        DataInputStream binFile = new DataInputStream(file.toURL().openStream());
        while(binFile.available() > 0) {
            try
            {
                int instr = binFile.readInt();
                instr = Integer.reverseBytes(instr);
                program.add(instr);
            }
            catch(EOFException e) {
                e.printStackTrace();
            }
        }
        binFile.close();

        return program.stream().mapToInt(i -> i).toArray();
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
        if(line.isBlank() || line.isEmpty() || line.charAt(0) == '/' || line.charAt(0) == '#') return -1; // discard line
        String[] parts = line.split("//|#");
        return Integer.decode(parts[0].trim());
    }
}
