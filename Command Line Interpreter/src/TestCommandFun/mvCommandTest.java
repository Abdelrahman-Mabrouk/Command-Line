//import CmdProgram.CMD;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import java.io.File;
//import java.io.IOException;
//
//class CMDTest {
//
//    private CMD cmd;
//    private final String testDir = "testDir";
//    private final String testFile = "testFile.txt";
//    private final String destDir = "destDir";
//    private final String destFile = "destFile.txt";
//
//    @BeforeEach
//    void setUp() {
//        cmd = new CMD();
//        new File(testDir).mkdir(); // Setup directory for tests
//        createFile(testDir + File.separator + testFile); // Create a file for testing
//        new File(destDir).mkdir(); // Create a destination directory
//    }
//
//    @AfterEach
//    void tearDown() {
//        deleteFileOrDirectory(new File(testDir)); // Clean up test directories and files
//        deleteFileOrDirectory(new File(destDir));
//    }
//
//    private void createFile(String path) {
//        try {
//            new File(path).createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void deleteFileOrDirectory(File file) {
//        if (file.isDirectory()) {
//            for (File f : file.listFiles()) {
//                deleteFileOrDirectory(f);
//            }
//        }
//        file.delete();
//    }
//
//    @Test
//    void testMoveFileToDirectory() {
//        String command = "mv " + testDir + "/" + testFile + " " + destDir;
//        String result = cmd.execute(command);
//        assertEquals("Successfully moved: " + testFile, result);
//
//        // Check if the file is moved
//        assertFalse(new File(testDir, testFile).exists(), "Source file should not exist");
//        assertTrue(new File(destDir, testFile).exists(), "File should exist in the destination directory");
//    }
//
//    @Test
//    void testMoveNonExistentFile() {
//        String command = "mv nonExistentFile.txt " + destDir;
//        String result = cmd.execute(command);
//        assertEquals("Error: Source file does not exist: " + System.getProperty("user.dir") + "/nonExistentFile.txt", result);
//    }
//
//    @Test
//    void testMoveFileToNewFile() {
//        String command = "mv " + testDir + "/" + testFile + " " + destDir + "/" + destFile;
//        String result = cmd.execute(command);
//        assertEquals("Successfully moved: " + testFile, result);
//
//        // Verify the file is at the new location with the new name
//        assertFalse(new File(testDir, testFile).exists(), "Source file should no longer exist");
//        assertTrue(new File(destDir, destFile).exists(), "Destination file should exist with new name");
//    }
//
//    @Test
//    void testInvalidMvCommand() {
//        String command = "mv " + testDir + "/" + testFile; // Missing destination
//        String result = cmd.execute(command);
//        assertEquals("Error: mv command requires a source and a destination.", result);
//    }
//}
//
