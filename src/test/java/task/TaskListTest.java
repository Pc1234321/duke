package task;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TaskListTest {
    private final ArrayList<Task> taskList = new ArrayList<>();
    /***
     * Test function to test if the insert function is working
     */
    @Test
    public void testInsertTask() {
        Task task = new Todo("Task 1");
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }
    /***
     * Test function to test if the delete function is working
     */
    @Test
    public void testDeleteTask() {
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        taskList.add(task1);
        taskList.add(task2);
        taskList.remove(0);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.get(0));
    }

}
