package TestCommandFun;

import CmdProgram.CMD;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppendCommandTest {
    CMD cliCommands = new CMD();

    @Test
    public void testAppendtoFile() {
        String result = cliCommands.execute("appended content >> newtextfile.txt");
        assertEquals("Content appended successfully.", result);
    }
}
