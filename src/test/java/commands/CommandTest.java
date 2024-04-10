package commands;

import org.junit.jupiter.api.Test;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    Command byeCommand = new ExitCommandTest("BYE");

    /***
     * Test exit command
     */
    @Test
    public void byeCommandTest() {
        // Bye command is executed so method will return true
        assertTrue(byeCommand.Exit());
    }
}
