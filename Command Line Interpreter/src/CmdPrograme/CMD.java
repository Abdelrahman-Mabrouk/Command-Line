package CmdPrograme;//import java.util.Scanner;

import java.io.File;
import java.io.IOException;

public class CMD {
    private String command;
    private String result;

    private void setCommand(String c) {
        command = c.replaceFirst("^\\s+", "");
    }

    private boolean isValidCommand(String[] nameFolders) {
        if (nameFolders.length > 1) {
            return true;
        }
        result = "Error: Command is not complete, please enter a folder or file name at next time.";
        System.out.println(result);
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

    public String execute(String command) {
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
            result = "Exiting...";
            System.out.println(result);
            return result;
        } else {
            result = "Error: Unknown command: " + command;
            System.out.println(result);
            return result;
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
            return result;
        }

        for (int i = 1; i < nameFolders.length; i++) {
            File folder = new File(nameFolders[i]);

            try {
                if (folder.exists()) {
                    result = "Folder already exists: " + nameFolders[i];
                    return result;
                } else {

                    if (folder.mkdir()) {
                        result = "Directory created successfully: " + nameFolders[i];
                        System.out.println(result);
                    } else {
                        result = "Error: Unable to create directory or name is invalid : " + nameFolders[i];
                        System.out.println(result);
                    }

                }
            } catch (Exception e) {
                result = "Error: An unexpected error occurred while creating directory " + nameFolders[i] + e.getMessage();
                return result;
            }
        }

        return result;
    }

    private String rmdir() {
        String[] nameFolders = command.split(" ");
        if (!isValidCommand(nameFolders)) {
            return result;
        }

        for (int i = 1; i < nameFolders.length; i++) {
            File folder = new File(nameFolders[i]);

            if (folder.exists() && folder.isDirectory()) {

                if (folder.delete()) {
                    result = "Directory removed successfully : " + nameFolders[i];
                    System.out.println(result);
                } else {
                    result = "Error: The directory " + nameFolders[i] + " could not be removed. It may not be empty or you don't have permission.";
                    System.out.println(result);
                }
            } else {
                result = "Error: Directory does not exist : " + nameFolders[i];
                System.out.println(result);
            }
        }
        return result;
    }


    private String touch() {

        String[] nameFiles = command.split(" ");
        if (!isValidCommand(nameFiles)) {
            return result;
        }

        for (int i = 1; i < nameFiles.length; i++) {
            File file = new File(nameFiles[i]);
            try {
                if (file.createNewFile()) {
                    result = "File created successfully: " + nameFiles[i];
                    System.out.println(result);
                } else {
                    result = "File " + nameFiles[i] + " already exists.";
                    System.out.println(result);
                }
            } catch (IOException e) {
                result = "Error: Unable to create " + nameFiles[i] + ". " + e.getMessage();
                System.out.println(result);
            }
        }
        return result;
    }


    private String mv() {
        return "Moved successfully.";
    }

    private String rm() {
        String[] nameFiles = command.split(" ");
        if (!isValidCommand(nameFiles)) {
            return result;
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
                            result = "Directory and its contents removed successfully: " + nameFiles[i];
                            System.out.println(result);
                        } else {
                            result = "cannot remove : " + nameFiles[i] + " Is a directory";
                            System.out.println(result);
                        }
                    } else {

                        if (file.delete()) {
                            result = "File removed successfully: " + nameFiles[i];
                            System.out.println(result);
                        } else {
                            result = "Error: Unable to remove file: " + nameFiles[i];
                            System.out.println(result);
                        }
                    }
                } else {
                    result = "Error: " + nameFiles[i] + " does not exist.";
                    System.out.println(result);
                }
            } catch (Exception e) {
                result = "Error: Unable to remove " + nameFiles[i] + ". " + e.getMessage();
                System.out.println(result);
            }
        }
        return result;
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
