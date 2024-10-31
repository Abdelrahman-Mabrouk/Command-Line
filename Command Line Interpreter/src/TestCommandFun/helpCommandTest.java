package TestCommandFun;

import CmdProgram.CMD;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

 class helpCommandTest {
    CMD cliCommands = new CMD();

    @Test
    public void testHelpOutput() {
        String result = cliCommands.execute("help");

        assertTrue(result.contains("Available commands:"));
        assertTrue(result.contains("pwd - Print working directory"));
        assertTrue(result.contains("cd <directory> - Change directory"));
        assertTrue(result.contains("ls - List files in directory"));
        assertTrue(result.contains("ls -a - List all files, including hidden"));
        assertTrue(result.contains("ls -r - List files in reverse order"));
        assertTrue(result.contains("mkdir <directory> - Create a new directory"));
        assertTrue(result.contains("rmdir <directory> - Remove a directory"));
        assertTrue(result.contains("touch <file> - Create a new file"));
        assertTrue(result.contains("mv <source> <destination> - Move or rename a file"));
        assertTrue(result.contains("rm <file> - Remove a file"));
        assertTrue(result.contains("cat <file> - Display file content"));
        assertTrue(result.contains("quit - Terminate the CLI"));
        assertTrue(result.contains("help - Display this help information"));
        assertTrue(result.contains("> and >> - Redirect output to a file"));
        assertTrue(result.contains("| - Pipe output to another command"));
    }
}
