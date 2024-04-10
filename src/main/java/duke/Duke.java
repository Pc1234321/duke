package duke;

import storage.Storage;
import task.*;
import ui.Ui;
import commands.*;

public class Duke {
    private final TaskList taskList;
    private static Storage storage;
    private static Ui ui;

    /***
     * Function to initiate the UI functions, storage and task list
     * @param filePath indicate the storage file path
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
        taskList.setList(storage.readFromFile());
    }

    /***
     * Function to run the Duke program
     */
    public void run() {
        ui.welcomeMenu();
        Ui.listMenu(taskList.getList());
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(taskList, ui, storage);
            isExit = c.Exit();
        }
    }

    public static void main(String[] args) {
        new Duke("./data/task.txt").run();
    }
}
