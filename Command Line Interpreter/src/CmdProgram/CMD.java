package CmdProgram;
import java.io.*;

public class CMD {
    private String command, result;

    private void setCommand(String c) {
        command = c.replaceFirst("^\\s+", "");
    }

    private boolean isValidCommand(String[] nameFolders, String command) {

        if (nameFolders.length > 1 && nameFolders[0].equals(command)) {
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
                if (!file.canWrite()) {
                    result = "Error: Unable to remove " + file.getName() + ". Permission denied.";
                    System.out.println(result);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }

    public String execute(String command) {
        setCommand(command);
        if (command.contains(" | ")) {
            return pipe();
        } else if (command.contains(" >> ")) {
            return appendFile();
        } else if (command.contains(" > ")) {
            return writeFile();
        } else if (command.contains("mkdir")) {
            return mkdir();
        } else if (command.contains("rmdir")) {
            return rmdir();
        } else if (command.contains("touch")) {
            return touch();
        } else if (command.contains("mv")) {
            return mv();
        } else if (command.contains("rm")) {
            return rm();
        } else if (command.equals("cat")) {
            return cat();
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

    private String mv() {
        return "Moved successfully.";
    }

    // Mabrouk's work starts from here :)
    private String mkdir() {
        String[] nameFolders = command.split(" ");
        if (!isValidCommand(nameFolders, "mkdir")) {
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
                result = "Error: An unexpected error occurred while creating directory " + nameFolders[i]
                        + e.getMessage();
                return result;
            }
        }

        return result;
    }

    private String rmdir() {
        String[] nameFolders = command.split(" ");
        if (!isValidCommand(nameFolders, "rmdir")) {
            return result;
        }

        for (int i = 1; i < nameFolders.length; i++) {
            File folder = new File(nameFolders[i]);

            if (folder.exists() && folder.isDirectory()) {

                if (folder.delete()) {
                    result = "Directory removed successfully : " + nameFolders[i];
                    System.out.println(result);
                } else {
                    result = "Error: The directory " + nameFolders[i]
                            + " could not be removed. It may not be empty or you don't have permission.";
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
        if (!isValidCommand(nameFiles, "touch")) {
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

    private String rm() {
        String[] nameFiles = command.split(" ");
        if (!isValidCommand(nameFiles, "rm")) {
            return result;
        }
        boolean is_rmr = false, is_forceDelete = false;
        int i = 1;

        if (nameFiles[1].equals("-r")) {
            is_rmr = true;
        } else if (nameFiles[1].equals("-f") || nameFiles[1].equals("--force")) {
            is_forceDelete = true;
        }
        i++;
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
                        if (!is_forceDelete && !file.canWrite()) {
                            result = "Error: Unable to remove " + nameFiles[i] + ". Permission denied.";
                            System.out.println(result);
                        } else {

                            if (file.delete()) {
                                result = "File removed successfully: " + nameFiles[i];
                                System.out.println(result);
                            } else {
                                result = "Error: Unable to remove file: " + nameFiles[i];
                                System.out.println(result);
                            }
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
    // Mabrouk's work ends from here :)


    // Ahmad's work starts here :)
    private String writeFile() {
        String[] parts = command.split(" > ", 2);
        if (parts.length < 2) {
            System.out.println("Error: Invalid command format. Usage: writeFile <content> > <fileName>");
            return "Error: Invalid command format. Usage: writeFile <content> > <fileName>";
        }
        String content = parts[0].trim();
        String fileName = parts[1].trim();

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            System.out.println("Writing process successful.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
        return "File written.";
    }

    private String appendFile() {
        String[] parts = command.split(" >> ", 2);
        if (parts.length < 2) {
            return "Error: Append command is incomplete. Use the format 'content >> filename'.";
        }
        String content = parts[0].trim();
        String fileName = parts[1].trim();
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();  // Create the file if it doesn't exist
            } catch (IOException e) {
                return "Error creating file: " + e.getMessage();
            }
        }
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(content);
            writer.write(System.lineSeparator());  // Optional: Add a new line after the content
            return "File appended successfully.";
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
            return "Error appending to file: " + e.getMessage();
        }
    }

    private String pipe() {
        String[] commands = command.split("\\|");
        String previousOutput = ""; 
        for (String cmd : commands) {
            String currentCommand = cmd.trim(), output = execute(currentCommand);
            if (commands.length > 1) {
                if (!output.startsWith("Error:")) { 
                    previousOutput += output + System.lineSeparator(); 
                } else {
                    return output; 
                }
            } else {
                return output; 
            }
        }
        return previousOutput; 
    }
    // Ahmad's work ends here :)

    private String ls() {
        return "Listing files.";
    }

    private String help() {
        return "Available commands: ";
    }

    private String cat() {
        return "Displaying file contents.";
    }

}
