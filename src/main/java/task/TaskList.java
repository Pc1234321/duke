package task;

import ui.Ui;
import duke.DateTime;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /***
     * Insert function to add task into task list
     * @param task the task object needs to insert
     */
    public void insertTask(Task task) {
        taskList.add(task);
        System.out.println("Alright, added " + task.getTaskName() + " into todo list");
        System.out.println("   " + task);
        System.out.println("You have " + taskList.size() + " things now in your todo list");

    }

    /***
     * Delete function to remove a task from task list
     * @param inputIndex task object needs to be deleted
     */
    public void deleteTask(String inputIndex) {
        int index = Integer.parseInt(inputIndex.trim());
        if (taskList.isEmpty()) {
            Ui.nothingToDelete();
        } else if (index < 1 || index > taskList.size()) {
            System.out.println("No such task in your list :(");
        } else {
            System.out.println("Okay, I've remove following task:");
            System.out.println("   " + taskList.get(index - 1).toString());
            taskList.remove(index - 1);
            System.out.println("You have " + taskList.size() + " things now in your todo list");
        }
    }

    /***
     * Function to mark/unmark function
     * @param value: mark or unmark a task
     * @param inputIndex: indicate which task's status needs to be changed
     */
    public void updateStatus(String value, String inputIndex) {
        int index = Integer.parseInt(inputIndex.trim());
        if (taskList.isEmpty()) {
            Ui.nothingToMark();
        } else if (index < 1 || index > taskList.size()) {
            System.out.println("No such task in your list :(");
        } else {
            boolean status = value.equalsIgnoreCase("mark");
            if (taskList.get(index - 1).getStatus() == status && status) {
                System.out.println("The task " + inputIndex + " is already marked as done");
            } else if (taskList.get(index - 1).getStatus() == status && !status) {
                System.out.println("The task " + inputIndex + " is already marked as not done");
            } else {
                if (status) {
                    System.out.println("Okay, I've marked task " + inputIndex + " as done");
                } else {
                    System.out.println("Okay, I've marked task " + inputIndex + " as not done yet");
                }
                taskList.get(index - 1).setStatus(status);
                System.out.println("   " + "[" + taskList.get(index - 1).getStatusIcon() + "] " + taskList.get(index - 1).getTaskName());
            }
        }
    }

    /***
     * Function to find out a task contains certain keyword
     * @param keyword: keyword in the task name
     */
    public void findTask(String keyword) {
        ArrayList<Task> findList = new ArrayList<>();
        if (taskList.isEmpty()) {
            Ui.nothingToSearch();
        } else {
            for (Task task : taskList) {
                String[] wordList = task.getTaskName().split(" ");
                String[] keywordWords = keyword.split(" ");
                //Will search phrase instead of only 1 keyword
                for (int i = 0; i <= wordList.length - keywordWords.length; i++) {
                    boolean isEnd = true;
                    for (int j = 0; j < keywordWords.length; j++) {
                        if (!wordList[i + j].equalsIgnoreCase(keywordWords[j])) {
                            isEnd = false;
                            break;
                        }
                    }
                    if (isEnd) {
                        findList.add(task);
                    }
                }
            }
            if (findList.isEmpty()) {
                Ui.nothingInKeyword();
            } else {
                Ui.printKeywordList(findList);
            }
        }


    }

    public void findDate(LocalDateTime inputDate) {
        ArrayList<Task> findList = new ArrayList<>();
        if (taskList.isEmpty()) {
            Ui.nothingToSearch();
        } else {
            for (Task task : taskList) {
                if (task.type.equals('E')) {
                    LocalDateTime startingDate = DateTime.stringToDate(task.getFrom());
                    LocalDateTime endingDate = DateTime.stringToDate(task.getBy());
                    if (DateTime.isDateValid(inputDate, startingDate, endingDate)) {
                        findList.add(task);
                    }
                } else if (task.type.equals('D')) {
                    LocalDateTime endingDate = DateTime.stringToDate(task.getBy());
                    if (DateTime.isDateValid(inputDate, endingDate)) {
                        findList.add(task);
                    }
                }
            }
            if (findList.isEmpty()) {
                Ui.nothingInPeriod();
            } else {
                Ui.printPeriodList(findList);
            }
        }


    }

    /***
     * Function to return the task list
     */
    public ArrayList<Task> getList() {
        return taskList;
    }

    /***
     * Setter function to set the task list
     * @param taskList: the task list needs to be set
     */
    public void setList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
}
