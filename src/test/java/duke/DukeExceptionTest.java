package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DukeExceptionTest extends Exception {
    /***
     * Test function to test isInteger is working when input is an integer
     */
    @Test
    public void testIsIntegerWithValidInteger() {
        assertTrue(DukeException.isInteger("1"));
    }
    /***
     * Test function to test isInteger is working when input is a letter
     */
    @Test
    public void testIsIntegerWithInvalidInteger() {
        assertFalse(DukeException.isInteger("A"));
    }
    /***
     * Test function to test isInteger is working when input is empty
     */
    @Test
    public void testIsIntegerWithEmptyString() {
        assertFalse(DukeException.isInteger(""));
    }
    /***
     * Test function to test isInteger is working when input is number with leading space
     */
    @Test
    public void testIsIntegerWithLeadingWhitespace() {
        assertFalse(DukeException.isInteger(" 1"));
    }
    /***
     * Test function to test isInteger is working when input is number with trailing space
     */
    @Test
    public void testIsIntegerWithTrailingWhitespace() {
        assertFalse(DukeException.isInteger("1 "));
    }
    /***
     * Test function to test isInteger is working when input is a space
     */
    @Test
    public void testIsIntegerWithWhitespaceOnly() {
        assertFalse(DukeException.isInteger(" "));
    }
    /***
     * Test function to test isInteger is working when input is special characters
     */
    @Test
    public void testIsIntegerWithSpecialCharacters() {
        assertFalse(DukeException.isInteger("!)(*&!^@#"));
    }

}

