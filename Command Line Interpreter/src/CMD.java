//import java.util.Scanner;

import java.io.File;
import java.io.IOException;

public class CMD {
    private String command;

    private void setCommand(String c) {
        command = c.replaceFirst("^\\s+", "");
    }

    String execute(String command) {
        setCommand(command);
        if (command.contains("mkdir")) {
            return mkdir();
        } else if (command.contains("rmdir")) {
            return rmdir();
        } else if (command.contains("touch")) {
            return touch();
        } else if (command.contains("mv")) {
            return mv();
        } else if (command.equals("rm")) {
            return rm();
        } else if (command.equals("cat")) {
            return cat();
        } else if (command.equals(">")) {
            return writeFile();
        } else if (command.equals(">>")) {
            return appendFile();
        } else if (command.equals("|")) {
            return pipe();
        } else if (command.equals("pwd")) {
            return pwd();
        } else if (command.equals("cd")) {
            return cd();
        } else if (command.equals("ls")) {
            return ls();
        } else if (command.equals("ls -a")) {
            return lsa();
        } else if (command.equals("ls -r")) {
            return lsr();
        } else if (command.equals("help")) {
            return help();
        } else if (command.equals("quit")) {
            return "Exiting...";
        } else {
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
        if (command.length() <= 6) {
            return "Error: Command is not complete, please enter a folder name next time.";
        }

        String[] nameFolders = command.split(" ");
        if (nameFolders.length > 1) {
            for (int i = 1; i < nameFolders.length; i++) {
                File folder = new File(nameFolders[i]);

                try {
                    if (folder.exists()) {
                        return "Folder already exists: " + nameFolders[i];
                    } else {

                        if (folder.mkdir()) {
                            System.out.println("Directory created successfully: " + nameFolders[i]);
                        } else {
                            System.out.println("Error: Unable to create directory " + nameFolders[i]);
                        }
                    }
                } catch (Exception e) {
                    // لمعالجة الأخطاء العامة
                    return "Error: An unexpected error occurred while creating directory " + nameFolders[i];
                }
            }
        }else {
            System.out.println("Error: Command is not complete, please enter a folder name at next time.");
        }
        return "";
    }

    private String rmdir() {
        if (command.length() <= 6) {
            return "Error: Command is not complete, please enter a folder name next time.";
        }

        String[] nameFolder = command.split(" ");
        for (int i = 1; i < nameFolder.length; i++) {
            File folder = new File(nameFolder[i]);

            if (folder.exists() && folder.isDirectory()) {

                if (folder.delete()) {
                    System.out.println("Directory removed successfully : "+ nameFolder[i]);
                } else {
                    System.out.println("Error: The directory "+ nameFolder[i] + " could not be removed. It may not be empty or you don't have permission.");
                }
            } else {
                System.out.println("Error: Directory does not exist : "+ nameFolder[i]);
            }
        }
        return "";
    }


    private String touch() {
        if (command.length() <= 6) {
            return "Error: Command is not complete, please enter a file name next time.";
        }

        String[] nameFile = command.split(" ");
        for (int i = 1; i < nameFile.length; i++) {
            File file = new File(nameFile[i]);
            try {
                if (file.createNewFile()) {
                    System.out.println("File created successfully: " + nameFile[i]);
                } else {
                    System.out.println("File already exists.");
                }
            } catch ( IOException e) {
                System.out.println("Error: Unable to create " + nameFile[i] + ". " + e.getMessage());
            }
        }
        return "";
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
