import java.util.Scanner;
import java.io.File;

public class CMD {
    private String command;

    private void setCommand(String c) {
        command = c;
    }

    String execute(String command) {
        setCommand(command);
        switch (command) {
            case "mkdir":
                return mkdir();
            case "rmdir":
                return rmdir();
            case "touch":
                return touch();
            case "mv":
                return mv();
            case "rm":
                return rm();
            case "cat":
                return cat();
            case ">":
                return writeFile();
            case ">>":
                return appendFile();
            case "|":
                return pipe();
            case "pwd":
                return pwd();
            case "cd":
                return cd();
            case "ls":
                return ls();
            case "ls -a":
                return lsa();
            case "ls -r":
                return lsr();
            case "help":
                return help();
            case "quit":
                return "Exiting...";
            default:
                return "Unknown command";
        }

    }

    private String lsr() {
        return "Listing files.";
    }

    private String lsa() {
        return "Listing files.";
    }

    private String pwd() {
        return "user.dir";
    }

    private String cd() {
        return "Changed directory to: ";
    }

    private String mkdir() {
        System.out.println("Enter Name Folder : ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        File folder = new File(name);

        if (folder.mkdir()) {
            System.out.println("Folder created successfully.");
        } else {
            System.out.println("The folder failed to be created or already exists.");
        }
        scanner.close();
        return "Directory created successfully.";
    }

    private String rmdir() {
        return "Directory removed successfully.";
    }

    private String touch() {
        return "File created successfully.";
    }

    private String mv() {
        return "Moved successfully.";
    }

    private String rm() {
        return "Removed successfully.";
    }

    private String cat() {
        return "Displaying file contents.";
    }

    private String writeFile() {
        return "File written.";
    }

    private String appendFile() {
        return "File appended.";
    }

    private String pipe() {
        return "Piping output.";
    }

    private String ls() {
        return "Listing files.";
    }

    private String help() {
        return "Available commands: ";
    }
}
