package CmdProgram;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;



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
        } else if (command.contains("cat")) {
            return cat();
        } else if (command.contains("pwd")) {
            pwd();
            return "";
        } else if (command.startsWith("cd")) {
            return cd();
        } else if (command.startsWith("ls -a")) {
            return lsa();
        }else if (command.startsWith("ls -r")) {
            return lsr();
        } else if (command.startsWith("ls")) {
            return ls();
        }  else if (command.equals("help")) {
            return help();
        } else if (command.equals("quit")) {
            result = "Exiting...";
            System.out.println(result);
            return result;
        }else {
            result = "Error: Unknown command: " + command;
            System.out.println(result);
            return result;
        }

    }
    private Path currentDirectory = Paths.get(System.getProperty("user.dir"));  // Start in user's working directory

    private String cd() {
        String[] parts = command.split(" ",2);
        if (!isValidCommand(parts, "cd") || parts.length < 2) {
            return "Error: No directory specified for cd command.";
        }
        Path newDir = currentDirectory.resolve(parts[1].trim()).normalize();
        if (Files.isDirectory(newDir)) {
            currentDirectory = newDir;
            result= "Changed directory to: " + currentDirectory.toString();

        } else {
            result= "Error: Directory does not exist: " + parts[1];
        }
        System.out.println(result);
        return result;
    }

    private String pwd() {
        System.out.println( currentDirectory.toString());
        return currentDirectory.toString();
    }
    private String ls() {
        String[] parts = command.split(" ", 2);

        Path directory;
        if (parts.length == 2) {
            directory = currentDirectory.resolve(parts[1].trim()).normalize();// directory b2a el7ta eltania
            if (!Files.isDirectory(directory)) {
                result = "Error: The specified path is not a directory.";
                System.out.println(result);
                return result;
            }

        } else {
            directory = currentDirectory; // Default to current directory if no directory is specified
        }

        try {

            List<String> files = Files.list(directory)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(name -> !name.startsWith(".")) // Exclude hidden files
                    .collect(Collectors.toList());
            result = String.join("\n", files);
        } catch (IOException e) {
            result = "Error: Unable to list directory contents.";
        }
        System.out.println(result);
        return result;
    }
    private String lsr() {
        String[] parts = command.split(" ", 3);

        Path directory;
        if (parts.length == 3) {
            directory = currentDirectory.resolve(parts[2].trim()).normalize();// directory b2a el7ta eltania
            if (!Files.isDirectory(directory)) {
                result = "Error: The specified path is not a directory.";
                System.out.println(result);
                return result;
            }

        } else {
            directory = currentDirectory; // Default to current directory if no directory is specified
        }
        try {
            Collections Comparator = null;
            List<String> files = Files.list(directory)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(name -> !name.startsWith(".")) // Exclude hidden files
                    .sorted(Collections.reverseOrder())  // Sort in reverse alphabetical order
                    .collect(Collectors.toList());

            result = String.join("\n", files);
        } catch (IOException e) {
            result = "Error: Unable to list directory contents in reverse.";
        }
        System.out.println(result);
        return result;
    }
    private String lsa() {
        String[] parts = command.split(" ", 3);

        Path directory;
        if (parts.length == 3) {
            directory = currentDirectory.resolve(parts[2].trim()).normalize();// directory b2a el7ta eltania
            if (!Files.isDirectory(directory)) {
                result = "Error: The specified path is not a directory.";
                System.out.println(result);
                return result;
            }

        } else {
            directory = currentDirectory; // Default to current directory if no directory is specified
        }
        try {
            List<String> files = Files.list(directory)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .sorted()
                    .collect(Collectors.toList());
            result = String.join("\n", files);
        } catch (IOException e) {
            result = "Error: Unable to list all files, including hidden.";
        }
        System.out.println(result);
        return result;
    }


    private String mv() {
        String[] args = command.split(" ");
        if (!isValidCommand(args, "mv") || args.length < 3) {
            result = "Error: mv command requires a source and a destination.";
            System.out.println(result);
            return result;
        }

        File sourceFile = new File(System.getProperty("user.dir"), args[1]);
        File destination = new File(System.getProperty("user.dir"), args[2]);

        if (!sourceFile.exists()) {
            result = "Error: Source file does not exist: " + sourceFile.getPath();
            System.out.println(result);
            return result;
        }

        if (destination.isDirectory()) {
            File newFile = new File(destination, sourceFile.getName());
            boolean success = sourceFile.renameTo(newFile);
            if (success) {
                System.out.println("Moved: " + sourceFile.getName() + " to " + newFile.getPath());
            } else {
                System.out.println("Failed to move: " + sourceFile.getName());
            }
        } else {
            boolean success = sourceFile.renameTo(destination);
            if (success) {
                System.out.println("Moved: " + sourceFile.getName() + " to " + destination.getPath());
            } else {
                System.out.println("Failed to move: " + sourceFile.getName());
            }
        }
        result = "Successfully moved: " + sourceFile.getName();
        return result;
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
        return "Writing process successful.";
    }

    private String appendFile() {
        String[] parts = command.split(" >> ", 2);
        if (parts.length < 2) {
            return "Error: Append command is incomplete. Use the format 'content >> filename'.";
        }
        String content = parts[0].trim();
        String fileName = parts[1].trim();
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(content);
            writer.write(System.lineSeparator()); // Add a new line after the content for confirmation
            System.out.println("Content appended successfully.");
            return "Content appended successfully.";
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
                    System.out.println(output);
                    return output;
                }
            } else {
                System.out.println(output);
                return output;
            }
        }
        System.out.println(previousOutput);
        return previousOutput;
    }
    // Ahmad's work ends here :)


    private String help() {
        System.out.println("Available commands:");
        System.out.println("pwd - Print working directory");
        System.out.println("cd <directory> - Change directory");
        System.out.println("ls - List files in directory");
        System.out.println("ls -a - List all files, including hidden");
        System.out.println("ls -r - List files in reverse order");
        System.out.println("mkdir <directory> - Create a new directory");
        System.out.println("rmdir <directory> - Remove a directory");
        System.out.println("touch <file> - Create a new file");
        System.out.println("mv <source> <destination> - Move or rename a file");
        System.out.println("rm <file> - Remove a file");
        System.out.println("cat <file> - Display file content");
        System.out.println("quit - Terminate the CLI");
        System.out.println("help - Display this help information");
        System.out.println("> and >> - Redirect output to a file");
        System.out.println("| - Pipe output to another command");
        return "Available commands: ";
    }

    private String cat() {
        String[] args = command.split(" ");
        if (!isValidCommand(args, "cat") || args.length < 2) {
            result = "Error: cat command requires a file name.";
            System.out.println(result);
            return result;
        }

        File file = new File(args[1]);

        // Check if the file exists
        if (!file.exists()) {
            result = "Error: File does not exist: " + file.getPath();
            System.out.println(result);
            return result;
        }

        // Read and print the file contents
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            result = "Error reading file: " + e.getMessage();
            System.out.println(result);
            return result;
        }

        // Print the contents of the file
        System.out.println(fileContent.toString());
        return fileContent.toString();
    }

}