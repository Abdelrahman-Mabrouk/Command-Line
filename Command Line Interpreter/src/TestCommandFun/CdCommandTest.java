package TestCommandFun;

import CmdProgram.CMD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;

class CdCommandTest {
    private CMD cmd;

    @BeforeEach
    void setUp() {
        cmd = new CMD();
    }

    @Test
    void testCdToValidDirectory() {
        String homeDirectory = System.getProperty("user.home");
        String result = cmd.execute("cd " + homeDirectory);
        assertEquals("Changed directory to: " + homeDirectory, result);
    }

    @Test
    void testCdToInvalidDirectory() {
        String result = cmd.execute("cd invalidDirectory");
        assertEquals("Error: Directory does not exist: invalidDirectory", result);
    }

    @Test
    void testCdNoDirectorySpecified() {
        String result = cmd.execute("cd");
        assertEquals("Error: No directory specified for cd command.", result);
    }

    @Test
    void testCdToRelativeDirectory() {
        Path currentDir = Paths.get(System.getProperty("user.dir"));
        String result = cmd.execute("cd ..");
        Path expectedDir = currentDir.getParent();
        assertEquals("Changed directory to: " + expectedDir, result);
    }
}
