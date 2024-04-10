package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class InvalidCommand extends Command {
    public InvalidCommand(String commandType) {
        super(commandType);
    }

    /***
     * Function to execute the command
     * @param taskList: the task list
     * @param ui: ui functions
     * @param storage： make use of the storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
    }

    /***
     * function to set if this command will end the program
     */
    public boolean Exit() {
        return false;
    }

}
