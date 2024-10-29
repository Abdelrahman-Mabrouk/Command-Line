
//  import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CMD Cmd = new CMD();
        String Command;
        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter command or 'quit' to exit: ");
                Command = input.nextLine();
                System.out.println(Cmd.execute(Command));
                if (Command.equals("quit")) {
                    input.close();
                    break;
                }
            }
        }
    }
}