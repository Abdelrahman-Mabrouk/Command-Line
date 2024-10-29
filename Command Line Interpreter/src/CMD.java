//import java.util.Scanner;

import java.io.File;

public class CMD {
    private String command;

    private void setCommand(String c) {
        command = c;
    }

    String execute(String command) {
        setCommand(command);
        if (command.contains("mkdir")) {
            return mkdir();
        } else if (command.contains("rmdir")) {
            return rmdir();
        } else if (command.equals("touch")) {
            return touch();
        } else if (command.equals("mv")) {
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
            return "Error: Command is not complete, please enter a folder name at next time.";
        }
        String[] nameFolder = command.split(" ");
        for (int i = 1; i < nameFolder.length; i++) {
            String nestedFolder,fatherFolder;

            if (nameFolder[i].contains("/")) {
                nestedFolder = nameFolder[i].substring(nameFolder[i].lastIndexOf("/") + 1);
                fatherFolder = nameFolder[i].substring(0, nameFolder[i].lastIndexOf("/"));
                File folder = new File(fatherFolder);

                if (folder.exists() && folder.isDirectory()) {
                    folder = new File(fatherFolder,nestedFolder);
                    if (!folder.mkdirs()) {
                        System.out.println("The folder failed to be created or already exists.");
                    } else {
                        System.out.println("Directory created successfully.");
                    }
                }
                else {
                    System.out.println("The Father Folder does not exist.");
                }

            } else {
                File folder = new File(nameFolder[i]);

                if (!folder.mkdir()) {
                    System.out.println("The folder failed to be created or already exists.");
                }
                else {
                    System.out.println("Directory created successfully.");
                }
            }
        }
            return "";
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
