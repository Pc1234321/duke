package duke;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    /***
     * Function to check if a string is an integer
     * @param value: input the value to check if it is integer
     */
    public static boolean isInteger(String value) {
        assert value != null;
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

