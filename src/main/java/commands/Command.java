package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public abstract class Command {

    private final String commandType;

    public Command(String commandType) {
        this.commandType = commandType;
    }

    /***
     * abstract function for Command classes
     */
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage);

    /***
     * Abstract function for Command classes
     * @return return nothing here since it is abstract function.
     */
    public abstract boolean Exit();

    /***
     * Return a type of command e.g. mark, unmark, deadline, event, etc.
     * @return return type of command in String format
     */
    public String getCommandType() {
        return commandType;
    }
}
