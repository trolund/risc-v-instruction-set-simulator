import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Tests {

    private final TestUtil test = new TestUtil();

    @Test
    public void loop2() throws IOException {
        assertTrue(test.runTestWithName(5, "loop2"));
    }
    
}
