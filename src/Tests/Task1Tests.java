import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Tests {

    private TestUtil test = new TestUtil();

    @Test
    public void addlarge() throws IOException {
        assertTrue(test.runTestWithName(1, "addlarge"));
    }


    @Test
    public void addneg() throws IOException {
        assertTrue(test.runTestWithName(1, "addneg"));
    }

    @Test
    public void addpos() throws IOException {
        assertTrue(test.runTestWithName(1, "addpos"));
    }

    @Test
    public void bool() throws IOException {
        assertTrue(test.runTestWithName(1, "bool"));
    }

    @Test
    public void set() throws IOException {
        assertTrue(test.runTestWithName(1, "set"));
    }

    @Test
    public void shift() throws IOException {
        assertTrue(test.runTestWithName(1, "shift"));
    }

    @Test
    public void shift2() throws IOException {
        assertTrue(test.runTestWithName(1, "shift2"));
    }
}
