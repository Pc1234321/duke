package commands;

import task.*;
import ui.Ui;
import storage.Storage;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(String commandType, Task task) {
        super(commandType);
        this.task = task;
    }

    /***
     * Function to execute the command
     * @param taskList: the task list
     * @param ui: ui functions
     * @param storage： make use of the storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.insertTask(task);
    }

    /***
     * Function to set if this command will end the program
     * @return return false since this command is not exit command
     */
    public boolean Exit() {
        return false;
    }
}
