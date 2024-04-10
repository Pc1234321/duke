package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private final String index;

    public MarkCommand(String commandType, String index) {
        super(commandType);
        this.index = index;
    }

    /***
     * Function to execute the command
     * @param taskList: the task list
     * @param ui: ui functions
     * @param storage： make use of the storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.updateStatus(getCommandType(), index);
    }

    /***
     * function to set if this command will end the program
     */
    public boolean Exit() {
        return false;
    }
}
