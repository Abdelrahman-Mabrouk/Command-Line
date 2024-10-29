package TestCommandFun;

import CmdPrograme.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MkdirCommandTest {
    CMD cliCommands = new CMD();

    @Test
    public void testCreateNewDirectory() {
        String result = cliCommands.execute("mkdir TestFolder/NewDirectory");
        assertEquals("Directory created successfully: TestFolder/NewDirectory", result);
    }

    @Test
    public void testDirectoryAlreadyExists() {
        String result = cliCommands.execute("mkdir TestFolder/ExistingDirectory");
        assertEquals("Folder already exists: TestFolder/ExistingDirectory", result);
    }

    @Test
    public void testInvalidDirectoryName() {
        String result = cliCommands.execute("mkdir TestFolder/invalidDirName?");
        assertEquals("Error: Unable to create directory or name is invalid : TestFolder/invalidDirName?", result);
    }
}
