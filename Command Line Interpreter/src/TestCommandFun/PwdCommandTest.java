package TestCommandFun;

import CmdProgram.CMD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PwdCommandTest {

    private CMD cmd;

    @BeforeEach
    public void setUp() {
        cmd = new CMD();
    }
    @Test
    public void testPwdCommand() {
        String result = cmd.execute("pwd");

        // (the expected result)
        assertEquals(System.getProperty("user.dir"), result.trim());
    }
}