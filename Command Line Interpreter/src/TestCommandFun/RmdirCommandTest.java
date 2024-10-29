package TestCommandFun;

import CmdPrograme.CMD;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RmdirCommandTest {
    CMD cliCommands = new CMD();

    @Test
    public void testRemoveExistingDirectory() {
        String result = cliCommands.execute("rmdir TestFolder/NewDirectory");
        assertEquals("Directory removed successfully : TestFolder/NewDirectory", result);
    }

    @Test
    public void testRemoveNonExistentDirectory() {
        String result = cliCommands.execute("rmdir TestFolder/NonExistentDirectory");
        assertEquals("Error: Directory does not exist : TestFolder/NonExistentDirectory", result);
    }

    @Test
    public void testRemoveDirectoryNotEmpty() {
        String result = cliCommands.execute("rmdir TestFolder/NonEmptyDirectory");
        assertEquals("Error: The directory TestFolder/NonEmptyDirectory could not be removed. It may not be empty or you don't have permission.", result);
    }


}
