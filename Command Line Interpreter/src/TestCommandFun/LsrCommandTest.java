package TestCommandFun;

import CmdProgram.CMD;
import org.junit.jupiter.api.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class LsrCommandTest {

    private CMD cmd;
    private Path testDirectory;

    @BeforeEach
    void setUp() throws Exception {
        cmd = new CMD();

        // Create a test directory
        testDirectory = Paths.get("testDir");
        Files.createDirectory(testDirectory);

        // Create sample files in alphabetical order
        Files.createFile(testDirectory.resolve("fileA.txt"));
        Files.createFile(testDirectory.resolve("fileB.txt"));
        Files.createFile(testDirectory.resolve("fileC.txt"));
    }

    @AfterEach
    void tearDown() throws Exception {
        // Clean up: delete files and directory after each test
        Files.deleteIfExists(testDirectory.resolve("fileA.txt"));
        Files.deleteIfExists(testDirectory.resolve("fileB.txt"));
        Files.deleteIfExists(testDirectory.resolve("fileC.txt"));
        Files.deleteIfExists(testDirectory);
    }

    @Test
    void testLsListsFilesInOrder() {
        cmd.execute("cd testDir");
        String output = cmd.execute("ls");

        // Check that files are listed in normal alphabetical order
        int indexA = output.indexOf("fileA.txt");
        int indexB = output.indexOf("fileB.txt");
        int indexC = output.indexOf("fileC.txt");

        assertTrue(indexA < indexB);
        assertTrue(indexB < indexC);
    }

    @Test
    void testLsListsFilesInReverseOrderWithROption() {
        cmd.execute("cd testDir");
        String output = cmd.execute("ls -r");

        // Check that files are listed in reverse alphabetical order
        int indexA = output.indexOf("fileA.txt");
        int indexB = output.indexOf("fileB.txt");
        int indexC = output.indexOf("fileC.txt");

        assertTrue(indexC < indexB);
        assertTrue(indexB < indexA);
    }
}
