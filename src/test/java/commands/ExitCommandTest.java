package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ExitCommandTest extends Command {
    public ExitCommandTest(String commandType) {
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
        Exit();
        ui.exitProgram();
        storage.writeToFile(taskList.getList());
    }

    /***
     * function to set if this command will end the program
     */
    @Override
    public boolean Exit() {
        return true;
    }

}
