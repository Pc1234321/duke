package task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = 'D';
    }

    /***
     * Getter to get by time
     */
    public String getBy() {
        return by;
    }

    /***
     * Override function to show the task info
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + " )";
    }

    /***
     * Override function to format the task data into correct format when store into local storage
     */
    @Override
    public String format() {
        return super.format() + "|by: " + by;
    }
}
