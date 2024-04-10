package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Character type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /***
     * Getter function to get task status, if task is marked, return "X", otherwise return space
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /***
     * Getter function to get if a task is marked as done
     */
    public boolean getStatus() {
        return isDone;
    }

    /***
     * Getter function to get task name
     */
    public String getTaskName() {
        return description;
    }

    /***
     * Getter function to get the task type
     */
    public Character getTaskType() {
        return type;
    }

    /***
     * Setter to set if a task is marked or unmarked
     */
    public void setStatus(boolean status) {
        isDone = status;
    }

    /***
     * Function to convert a task info into a string
     */
    public String toString() {
        return "[" + type + "][" + getStatusIcon() + "] " + description;
    }

    /***
     * Getter function to get from date, this function is override function
     */
    public String getFrom() {
        return "";
    }

    /***
     * Getter function to get by date, this function is override function
     */
    public String getBy() {
        return "";
    }

    /***
     * Override function to format the task data into correct format when store into local storage
     */
    public String format() {
        return type + "|" + isDone + "|Name: " + description;
    }
}