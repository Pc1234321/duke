package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.time.LocalDateTime;

public class DateCommand extends Command {
    private final LocalDateTime inputDate;

    public DateCommand(String commandType, LocalDateTime inputDate) {
        super(commandType);
        this.inputDate = inputDate;
    }

    /***
     * Function to execute the command
     * @param taskList: the task list
     * @param ui: ui functions
     * @param storage： make use of the storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.findDate(inputDate);
    }

    /***
     * function to set if this command will end the program
     */
    @Override
    public boolean Exit() {
        return false;
    }
}
