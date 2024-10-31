package CmdProgram;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CMD Cmd = new CMD();
        String Command;
        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                String currentDir = System.getProperty("user.dir").replace("\\", "/");
                String prompt = String.format("root@hostname ~%s $ ", currentDir);
                System.out.print(prompt);
                Command = input.nextLine();
                Cmd.execute(Command);
                if (Command.equals("quit")) {
                    input.close();
                    break;
                }
            }
        }
    }
}