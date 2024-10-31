package TestCommandFun;

import CmdProgram.CMD;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PipeCommandTest {
    CMD cliCommands = new CMD();

    @Test
    public void testPipe() {
        String result = cliCommands.execute("mkdir newdir | cd newdir | touch newfile.txt");
        assertEquals("File created successfully: TestFolder/newfile.txt", result);
    }
}
