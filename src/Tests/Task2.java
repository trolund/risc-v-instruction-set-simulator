import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2 {

    private final TestUtil test = new TestUtil();

    @Test
    public void branchcnt() throws IOException {
        assertTrue(test.runTestWithName(2, "branchcnt"));
    }


    @Test
    public void branchmany() throws IOException {
        assertTrue(test.runTestWithName(2, "branchmany"));
    }

    @Test
    public void branchtrap() throws IOException {
        assertTrue(test.runTestWithName(2, "branchtrap"));
    }

}
