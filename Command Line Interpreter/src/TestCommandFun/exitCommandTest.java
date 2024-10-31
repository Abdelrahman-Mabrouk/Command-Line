package TestCommandFun;
import CmdProgram.CMD;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class exitCommandTest {
    CMD cliCommands = new CMD();

    @Test
    public void testExitCommand() {
        String result = cliCommands.execute("quit");
        assertEquals("Exiting...", result);
    }
}

