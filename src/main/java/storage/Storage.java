package storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.*;

public class Storage {
    protected String filePath;

    private static final ArrayList<Task> readDataList = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Storage.class.getName());

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //Bug log
    private static void configureLogger() {
        try {
            // Create a directory named "bug" if it doesn't exist
            File directory = new File("bug");
            if (!directory.exists()) {
                boolean created = directory.mkdirs();  // Creates parent directories as needed
                if (!created) {
                    throw new IOException("Failed to create directory 'bug'.");
                }
            }
            // Configure logger to write to a log file in the "bug" directory
            File logFile = new File(directory, "error.log");
            FileHandler fileHandler = new FileHandler(logFile.getAbsolutePath(), true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to configure logger", e);
        }
    }

    /***
     * Function to write data into storage file
     * @param todoList task list to write into the storage file
     */
    public void writeToFile(ArrayList<Task> todoList) {
        try {
            // Create the directory if it doesn't exist
            File directory = new File("./data/");
            if (!directory.exists()) {
                boolean created = directory.mkdirs();
                if (!created) {
                    throw new IOException("Failed to create directory data.");
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Task task : todoList) {
                    writer.write(task.format());
                    writer.newLine(); // Add a new line after each task description
                }
            }
        } catch (IOException e) {
            configureLogger();
            logger.log(Level.SEVERE, "An IOException occurred", e);
        }
    }

    /***
     *Function to read data from local storage file
     */
    public ArrayList<Task> readFromFile() {

        Path path = Paths.get(filePath);
        // Check if the file exists before attempting to read
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return readDataList;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean status = false;
            while ((line = reader.readLine()) != null) {
                String type = "";
                String taskName = "";
                String by = "";
                String from = "";
                String[] txtList = line.split("\\|");
                //Analysis txt file data
                for (String item : txtList) {
                    if (item.equalsIgnoreCase("T") ||
                            item.equalsIgnoreCase("E") ||
                            item.equalsIgnoreCase("D")) {
                        type = item;
                    } else if (item.equalsIgnoreCase("true")) {
                        status = true;
                    } else if (item.equalsIgnoreCase("false")) {
                        status = false;
                    } else {
                        String[] itemList = item.split(" ");
                        if (itemList[0].equalsIgnoreCase("Name:")) {
                            taskName = combineArray(itemList);
                        } else if (itemList[0].equalsIgnoreCase("from:")) {
                            from = combineArray(itemList);
                        } else if (itemList[0].equalsIgnoreCase("by:")) {
                            by = combineArray(itemList);
                        }
                    }
                }
                if (type.isEmpty() || taskName == null || taskName.isEmpty()) {
                    continue;
                }
                //Install data
                addData(type, taskName, from, by, status);
            }
        } catch (IOException e) {
            configureLogger();
            logger.log(Level.SEVERE, "An data input error occurred", e);
        }
        return readDataList;
    }

    /***
     * A helper function for readFromFile() function to store read data to readDataList array
     * @param type pass the event type to function to store data
     * @param taskName pass the task name to function to store data
     * @param from pass the starting time to function to store data
     * @param by pass the ending time to function to store data
     * @param status pass the task status to function to store data
     */
    public static void addData(String type, String taskName, String from, String by, boolean status) throws IOException {
        assert taskName != null;
        if (type.equalsIgnoreCase("T")) {
            if (taskName.isEmpty()) {
                throw new IOException("Failed to read one or more lines of data. Reason: Bad format\n" +
                        "Warning: System will ignore the bad format data, beware of data loss!");
            }
            Todo task = new Todo(taskName);
            task.setStatus(status);
            readDataList.add(task);
        } else if (type.equalsIgnoreCase("E")) {
            assert by != null;
            assert from != null;
            if (taskName.isEmpty() || by.isEmpty() || from.isEmpty()) {
                throw new IOException("Failed to read one or more lines of data. Reason: Bad format\n" +
                        "Warning: System will ignore the bad format data, beware of data loss!");
            }
            Event task = new Event(taskName, from, by);
            task.setStatus(status);
            readDataList.add(task);
        } else if (type.equalsIgnoreCase("D")) {
            assert by != null;
            if (taskName.isEmpty() || by.isEmpty()) {
                throw new IOException("Failed to read one line of data. Reason: Bad format\n" +
                        "System will ignore the bad format data.");
            }
            Deadline task = new Deadline(taskName, by);
            task.setStatus(status);
            readDataList.add(task);
        }

    }

    /***
     * Helper function to combine array into a single string
     * @param array: the array needs to combine from index 1
     */
    public static String combineArray(String[] array) {
        assert array != null;
        if (array.length > 1) {
            StringBuilder result = new StringBuilder(array[1]);
            result.append(" ");
            for (int i = 2; i < array.length; i++) {
                result.append(array[i]);
                result.append(" ");
            }
            return result.toString();
        } else {
            return null;
        }
    }

}
