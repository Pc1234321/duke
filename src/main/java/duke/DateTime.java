package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    //Date time format for program
    private static final DateTimeFormatter formatYYYY_MM_DD_HHmm = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    //Display format for user to view
    private static final DateTimeFormatter displayDateTimeFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    private static final DateTimeFormatter displayDateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");


    public DateTime() {
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
     * Function to convert the correct format date time to a string
     * @param datetime: input date time
     */
    public static String dateString(String datetime) throws DukeException {
        LocalDateTime DateTime = checkDate(datetime.trim());
        // Check if the  date has time, if so, use HHmm format, otherwise use yyyy-MM-dd format
        if (DateTime.getHour() != 0 || DateTime.getMinute() != 0) {
            return DateTime.format(displayDateTimeFormat);
        } else {
            return DateTime.format(displayDateFormat);
        }
    }

    /***
     * A function to convert string to date format
     * @param dateTime user input date format with string
     * @return return a converted date format
     */
    public static LocalDateTime stringToDate(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime.trim(), displayDateTimeFormat);
        } catch (DateTimeParseException e) {
            try {
                // Parsing without time, append "0000" to indicate midnight
                return LocalDateTime.parse(dateTime.trim() + " 0000", displayDateTimeFormat);
            } catch (DateTimeParseException ex) {
                System.out.println("The dateTime format is wrong");
                return null;
            }
        }
    }

    /***
     * Function to check if the input date time is later than the current date time
     * @param input input date time
     */
    public static boolean isDateValid(String input) throws DukeException {
        LocalDateTime targetDate = checkDate(input.trim());
        LocalDateTime currentDateTime = LocalDateTime.now();
        return targetDate.isAfter(currentDateTime);
    }

    /***
     * Function to check if current date is before target date
     * @param startingDate date time for from date
     * @param targetDate date time for by date
     */
    public static boolean isEventValid(String startingDate, String targetDate) throws DukeException {
        LocalDateTime startingDateTime = checkDate(startingDate.trim());
        LocalDateTime targetDateTime = checkDate(targetDate.trim());
        return !targetDateTime.isAfter(startingDateTime);
    }

    /***
     * Function to check if a date is within the given period
     * @param targetDate a date from user input
     * @param startingDate a starting date of an event
     * @param endingDate an ending date of an event
     * @return return true if the date is within the period
     */
    public static boolean isDateValid(LocalDateTime targetDate, LocalDateTime startingDate, LocalDateTime endingDate) {
        return !targetDate.isBefore(startingDate) && !targetDate.isAfter(endingDate);
    }

    /***
     * Function to check if the date is before the given date
     * @param targetDate a date from user input
     * @param endingDate an ending date of an event
     * @return true if the date is before the ending date
     */
    public static boolean isDateValid(LocalDateTime targetDate, LocalDateTime endingDate) {
        return !targetDate.isAfter(endingDate);
    }


}
