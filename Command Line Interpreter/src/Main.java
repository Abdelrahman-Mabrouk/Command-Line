
//  import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CMD Cmd = new CMD();
        String Command;
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter command or 'quit' to exit: ");
            Command = input.nextLine();
            System.out.println(Cmd.execute(Command));
            if (Command.equals("quit")) {
                input.close();
                break;
            }
            input.close();
        }
    }
}