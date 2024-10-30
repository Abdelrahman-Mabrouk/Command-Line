package TestCommandFun;
import CmdProgram.CMD;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TouchCommandTest {
    CMD cliCommands = new CMD();

    @Test
    public void testCreateNewFile() {
        String result = cliCommands.execute("touch TestFolder/newfile.txt");
        assertEquals("File created successfully: TestFolder/newfile.txt", result);
    }

    @Test
    public void testFileAlreadyExists() {
        String result = cliCommands.execute("touch TestFolder/existingfile.txt");
        assertEquals("File TestFolder/existingfile.txt already exists.", result);
    }

    @Test
    public void testInvalidFilename() {
        String result = cliCommands.execute("touch TestFolder/invalidName?.txt");
        assertEquals("Error: Unable to create TestFolder/invalidName?.txt. The filename, directory name, or volume label syntax is incorrect", result);
    }
}
