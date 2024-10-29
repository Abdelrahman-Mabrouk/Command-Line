//import java.util.Scanner;

import java.io.File;
import java.io.IOException;

public class CMD {
    private String command;

    private void setCommand(String c) {
        command = c.replaceFirst("^\\s+", "");
    }

    private boolean isValidCommand(String[] nameFolders) {
        if (nameFolders.length > 1) {
            return true;
        }
        System.out.println("Error: Command is not complete, please enter a folder or file name at next time.");
        return false;
    }

    private void removeDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    removeDirectory(file);
                }
                file.delete();
            }
        }
        directory.delete();
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
        } else if (command.contains("rm")) { // rm and rm -r
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
        String[] nameFolders = command.split(" ");
        if (!isValidCommand(nameFolders)) {
            return "";
        }

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

        return "";
    }

    private String rmdir() {

        String[] nameFolders = command.split(" ");
        if (!isValidCommand(nameFolders)) {
            return "";
        }

        for (int i = 1; i < nameFolders.length; i++) {
            File folder = new File(nameFolders[i]);

            if (folder.exists() && folder.isDirectory()) {

                if (folder.delete()) {
                    System.out.println("Directory removed successfully : " + nameFolders[i]);
                } else {
                    System.out.println("Error: The directory " + nameFolders[i] + " could not be removed. It may not be empty or you don't have permission.");
                }
            } else {
                System.out.println("Error: Directory does not exist : " + nameFolders[i]);
            }
        }
        return "";
    }


    private String touch() {

        String[] nameFiles = command.split(" ");
        if (!isValidCommand(nameFiles)) {
            return "";
        }

        for (int i = 1; i < nameFiles.length; i++) {
            File file = new File(nameFiles[i]);
            try {
                if (file.createNewFile()) {
                    System.out.println("File created successfully: " + nameFiles[i]);
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("Error: Unable to create " + nameFiles[i] + ". " + e.getMessage());
            }
        }
        return "";
    }


    private String mv() {
        return "Moved successfully.";
    }

    private String rm() {
        String[] nameFiles = command.split(" ");
        if (!isValidCommand(nameFiles)) {
            return "";
        }
        boolean is_rmr = false;
        int i = 1;
        if (nameFiles[1].equals("-r")) {
            is_rmr = true;
            i++;
        }

        for (; i < nameFiles.length; i++) {
            File file = new File(nameFiles[i]);
            try {
                if (file.exists()) {
                    if (file.isDirectory()) {
                        if (is_rmr) {
                            removeDirectory(file);
                            System.out.println("Directory and its contents removed successfully: " + nameFiles[i]);
                        } else {
                            System.out.println("cannot remove : " + nameFiles[i] + " Is a directory");
                        }
                    } else {

                        if (file.delete()) {
                            System.out.println("File removed successfully: " + nameFiles[i]);
                        } else {
                            System.out.println("Error: Unable to remove file: " + nameFiles[i]);
                        }
                    }
                } else {
                    System.out.println("Error: " + nameFiles[i] + " does not exist.");
                }
            } catch (Exception e) {
                System.out.println("Error: Unable to remove " + nameFiles[i] + ". " + e.getMessage());
            }
        }
        return "";
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
