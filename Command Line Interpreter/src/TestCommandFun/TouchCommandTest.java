package TestCommandFun;
import CmdPrograme.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TouchCommandTest {
    CMD cliCommands = new CMD();

    @Test
    public void testCreateNewFile() {
        String result = cliCommands.execute("touch newfile.txt");
        assertEquals("File created successfully: newfile.txt", result);
    }

    @Test
    public void testFileAlreadyExists() {
        String result = cliCommands.execute("touch existingfile.txt");
        assertEquals("File existingfile.txt already exists.", result);
    }

    @Test
    public void testInvalidFilename() {
        String result = cliCommands.execute("touch invalidName?.txt");
        assertEquals("Error: Unable to create invalidName?.txt. The filename, directory name, or volume label syntax is incorrect", result);
    }
}
