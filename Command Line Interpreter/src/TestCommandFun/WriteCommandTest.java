package TestCommandFun;

import CmdProgram.CMD;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WriteCommandTest {

    CMD cliCommands = new CMD();

    @Test
    public void testWriteToFile() {
        String result = cliCommands.execute("words words > newtextfile.txt");
        assertEquals("Writing process successful.", result);
    }
}
