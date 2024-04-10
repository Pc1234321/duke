package task;

public class Event extends Task {
    protected String from;
    protected String by;

    public Event(String description, String from, String by) {
        super(description);
        this.from = from;
        this.by = by;
        this.type = 'E';
    }

    public String getFrom() {
        return from;
    }

    public String getBy() {
        return by;
    }

    /***
     * Override function to show the task info
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + by + " )";
    }

    /***
     * Override function to format the task data into correct format when store into local storage
     */
    @Override
    public String format() {
        return super.format() + "|from: " + from + "|by: " + by;
    }
}
