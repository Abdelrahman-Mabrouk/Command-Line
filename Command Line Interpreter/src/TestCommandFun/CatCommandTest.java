package TestCommandFun;
import CmdProgram.CMD;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CatCommandTest {
    CMD cliCommands = new CMD();

    @Test
    public void testReadExistingFile() {
        String fileName = "testFile.txt";
        String fileContent = "Hello, world!";
        
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(fileContent);
        } catch (IOException e) {
            fail("Setup failed: Unable to create test file.");
        }
        
        String result = cliCommands.execute("cat " + fileName);
        assertEquals(fileContent + System.lineSeparator(), result);
        
        new File(fileName).delete();
    }

    @Test
    public void testReadNonExistentFile() {
        String result = cliCommands.execute("cat nonExistentFile.txt");
        assertEquals("Error: File does not exist: nonExistentFile.txt", result);
    }

    @Test
    public void testEmptyFile() {
        String fileName = "emptyFile.txt";
        
        try {
            new File(fileName).createNewFile();
        } catch (IOException e) {
            fail("Setup failed: Unable to create empty test file.");
        }
        
        String result = cliCommands.execute("cat " + fileName);
        assertEquals("", result); 
 
        new File(fileName).delete();
    }
}
