package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    private final String keyWord;

    public FindCommand(String commandType, String keyWord) {
        super(commandType);
        this.keyWord = keyWord;
    }

    /***
     * Function to execute the command
     * @param taskList: the task list
     * @param ui: ui functions
     * @param storage： make use of the storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.findTask(keyWord);
    }

    /***
     * function to set if this command will end the program
     */
    @Override
    public boolean Exit() {
        return false;
    }
}
