package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class DateTimeTest {
    //Date time format for program
    private static final DateTimeFormatter formatYYYY_MM_DD_HHmm = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    //Display format for user to view
    private static final DateTimeFormatter displayDateTimeFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    private static final DateTimeFormatter displayDateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public DateTimeTest() {
    }

    /***
     * Function to check if user input is a correct date-time format
     * @param datetime: user input date time
     */
    public static LocalDateTime checkDate(String datetime) throws DukeException {
        try {
            // Try parsing datetime with the format yyyy-MM-dd HHmm
            return LocalDateTime.parse(datetime.trim(), formatYYYY_MM_DD_HHmm);
        } catch (DateTimeParseException e) {
            try {
                // If parsing with format yyyy-MM-dd HHmm fails, try parsing with format yyyy-MM-dd
                return LocalDateTime.parse(datetime.trim() + " 0000", formatYYYY_MM_DD_HHmm);
            } catch (DateTimeParseException ex) {
                throw new DukeException("Your Date and Time format is wrong, please use yyyy-MM-dd HHmm or yyyy-MM-dd.\n" +
                        "Remember to add 0 if date time is a single digit. E.g. Mar 8 2021 9:00 -> 2021-03-08 0900");
            }
        }
    }

    /***
     * Test date valid function
     */
    @Test
    public void isDateValid() throws DukeException {
        String date = "2020-10-10";
        LocalDateTime targetDate = checkDate(date);
        LocalDateTime currentDateTime = LocalDateTime.now();
        assertFalse(targetDate.isAfter(currentDateTime));
    }

    /***
     * Test date valid function for 2 dates
     */
    @Test
    public void isEventValid() throws DukeException {
        String startDate = "2020-10-10 2359";
        String targetDate = "2024-01-01 1023";
        LocalDateTime startingDateTime = checkDate(startDate);
        LocalDateTime targetDateTime = checkDate(targetDate);
        assertTrue(targetDateTime.isAfter(startingDateTime));
    }


}
