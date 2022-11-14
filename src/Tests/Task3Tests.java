import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Tests {

    private final TestUtil test = new TestUtil();

    @Test
    public void loop() throws IOException {
        assertTrue(test.runTestWithName(3, "loop"));
    }

    @Test
    public void recursive() throws IOException {
        assertTrue(test.runTestWithName(3, "recursive"));
    }

    @Test
    public void string() throws IOException {
        assertTrue(test.runTestWithName(3, "string"));
    }

    @Test
    public void width() throws IOException {
        assertTrue(test.runTestWithName(3, "width"));
    }
    
}
