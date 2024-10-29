package TestCommandFun;

import CmdPrograme.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RmCommandTest {
    CMD cliCommands = new CMD();

    // اختبارات لحذف الملفات
    @Test
    public void testRemoveExistingFile() {
        String result = cliCommands.execute("rm TestFolder/ExistingFile.txt");
        assertEquals("File removed successfully: TestFolder/ExistingFile.txt", result);
    }

    @Test
    public void testRemoveNonExistentFile() {
        String result = cliCommands.execute("rm TestFolder/NonExistentFile.txt");
        assertEquals("Error: TestFolder/NonExistentFile.txt does not exist.", result);
    }


    @Test
    public void testRemoveDirectoryInsteadOfFile() {
        String result = cliCommands.execute("rm TestFolder/ExistingDirectory");
        assertEquals("cannot remove : TestFolder/ExistingDirectory Is a directory", result);
    }


    @Test
    public void testRemoveReadOnlyFile() {
        String result = cliCommands.execute("rm TestFolder/OnlyRead.txt");
        assertEquals("Error: Unable to remove TestFolder/OnlyRead.txt. Permission denied.", result);
    }
    @Test
    public void testRemoveExistingFileWithForce() {
        String result = cliCommands.execute("rm -f TestFolder/ExistingFile.txt");
        assertEquals("File removed successfully: TestFolder/ExistingFile.txt", result);
    }

    @Test
    public void testRemoveNonExistentFileWithForce() {
        String result = cliCommands.execute("rm -f TestFolder/NonExistentFile.txt");
        assertEquals("Error: TestFolder/NonExistentFile.txt does not exist.", result);
    }

    @Test
    public void testRemoveExistingDirectoryWithRecursion() {
        String result = cliCommands.execute("rm -r TestFolder/ExistingDirectory");
        assertEquals("Directory and its contents removed successfully: TestFolder/ExistingDirectory", result);
    }

    @Test
    public void testRemoveNonExistentDirectoryWithRecursion() {
        String result = cliCommands.execute("rm -r TestFolder/NonExistentDirectory");
        assertEquals("Error: TestFolder/NonExistentDirectory does not exist.", result);
    }

    @Test
    public void testRemoveDirectoryWithoutForce() {
        String result = cliCommands.execute("rm TestFolder/ExistingDirectory");
        assertEquals("cannot remove : TestFolder/ExistingDirectory Is a directory", result);
    }
}
