package TestCommandFun;

import CmdProgram.CMD;
import org.junit.jupiter.api.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class LsCommandTest {

    private CMD cmd;
    private Path testDirectory;

    @BeforeEach
    void setUp() throws Exception {
        cmd = new CMD();
        // Create a test directory
        testDirectory = Paths.get("testDir");
        Files.createDirectory(testDirectory);

        // Create sample files in the directory
        Files.createFile(testDirectory.resolve("file1.txt"));
        Files.createFile(testDirectory.resolve("file2.txt"));
    }

    @AfterEach
    void tearDown() throws Exception {
        // Clean up: delete files and directory after each test
        Files.deleteIfExists(testDirectory.resolve("file1.txt"));
        Files.deleteIfExists(testDirectory.resolve("file2.txt"));
        Files.deleteIfExists(testDirectory);
    }

    @Test
    void testLsListsFiles() {
        cmd.execute("cd testDir");
        String output = cmd.execute("ls");

        // Expected output should include both files
        assertTrue(output.contains("file1.txt"));
        assertTrue(output.contains("file2.txt"));
    }

    @Test
    void testLsEmptyDirectory() throws Exception {
        cmd.execute("cd testDir");

        // Delete files to test an empty directory
        Files.deleteIfExists(testDirectory.resolve("file1.txt"));
        Files.deleteIfExists(testDirectory.resolve("file2.txt"));

        String output = cmd.execute("ls");

        // Output should be empty
        assertEquals("", output.trim());
    }
}
