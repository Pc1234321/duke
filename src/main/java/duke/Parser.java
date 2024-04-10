package duke;

import commands.*;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Todo;
import ui.Ui;

import java.time.LocalDateTime;

import static duke.DukeException.isInteger;


public class Parser extends DateTime {
    private static String taskName;
    private static String by;

    /***
     * Function to check the user input and make sure the input is a valid command.
     * If the user input is valid command, then return the specific command.
     * @param userInput: String of input get from user
     */
    public static Command parse(String userInput) {
        String[] wordList = userInput.split(" ");
        boolean isFormat = true;
        try {
            //Todo checker
            if (wordList[0].equalsIgnoreCase("todo")) {
                if (wordList.length == 1) {
                    throw new DukeException("Please give your task a name");
                } else {
                    return parseTodo(wordList);
                }
            }
            //Event checker
            else if (wordList[0].equalsIgnoreCase("event")) {
                if (wordList.length == 1) {
                    throw new DukeException("Please give your event a name");
                } else {
                    return parseEvent(wordList);
                }
            }
            //deadline
            else if (wordList[0].equalsIgnoreCase("deadline")) {
                if (wordList.length == 1) {
                    throw new DukeException("Please give your deadline a name");
                } else {
                    return parseDeadline(wordList);
                }
            }
            //mark
            else if (wordList[0].equalsIgnoreCase("mark") ||
                    wordList[0].equalsIgnoreCase("unmark")) {
                if (wordList.length == 1) {
                    throw new DukeException("Please tell me which task you would like to mark/unmark");
                } else {
                    String checkNum = wordList[1];
                    if (isInteger(checkNum)) {
                        return new MarkCommand(wordList[0], checkNum);
                    } else {
                        throw new DukeException("Please tell me which number of task you would like to mark/unmark");
                    }
                }
            }
            //delete
            else if (wordList[0].equalsIgnoreCase("delete")) {
                if (wordList.length == 1) {
                    throw new DukeException("Please tell me which task you would like to delete");
                } else {
                    String checkNum = wordList[1];
                    if (isInteger(checkNum)) {
                        return new DeleteCommand("delete", checkNum);
                    } else {
                        throw new DukeException("Please tell me which number of task you would like to delete");
                    }
                }
            }
            //find
            else if (wordList[0].equalsIgnoreCase("find")) {
                if (wordList.length == 1) {
                    throw new DukeException("Please tell me the keywords of the task you would like to find");
                } else {
                    String keyWord = Storage.combineArray(wordList);
                    return new FindCommand("find", keyWord);
                }
            }
            //date
            else if (wordList[0].equalsIgnoreCase("date")) {
                if (wordList.length == 1) {
                    throw new DukeException("Please tell me a specific date you would like to search");
                } else if (wordList.length == 2 || wordList.length == 3) {
                    String dateString = Storage.combineArray(wordList);
                    assert dateString != null;
                    LocalDateTime date = DateTime.checkDate(dateString);
                    return new DateCommand("find", date);
                } else {
                    throw new DukeException("Your date format seems wrong. please try following pattern " +
                            "date + yyyy-MM-dd or date + yyyy-MM-dd HHmm");
                }

            }
            //quit
            else if (wordList[0].equalsIgnoreCase("bye") ||
                    wordList[0].equalsIgnoreCase("quit")) {// Single command no need to check
                return new ExitCommand("exit");
            }
            //list
            else if (wordList[0].equalsIgnoreCase("list")) {
                return new ListCommand("list");
            } else {
                isFormat = false;
                throw new DukeException("I don't get it, I prepared following functions for you.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());// Print the error message
            if (!isFormat) {
                new Ui().helpMenu();
            }
            return new InvalidCommand("Error");
        }
    }

    /***
     * Function to parse Todo task
     * @param wordList input word list from your input value
     * @return return a add todo command
     */
    public static Command parseTodo(String[] wordList) {
        taskName = Storage.combineArray(wordList);
        Todo task = new Todo(taskName);
        return new AddCommand("todo", task);
    }

    /***
     * Function to parse event task
     * @param wordList input word list from your input value
     * @return return add event command
     */
    public static Command parseEvent(String[] wordList) throws DukeException {
        StringBuilder taskNameBuilder = new StringBuilder();
        StringBuilder fromBuilder = new StringBuilder();
        StringBuilder byBuilder = new StringBuilder();
        boolean isFrom = false;
        boolean isTo = false;
        //Check From stage and to stage
        for (int i = 0; i < wordList.length; i++) {
            if (wordList[1].equalsIgnoreCase("/from") ||
                    wordList[1].equalsIgnoreCase("/to")) {
                throw new DukeException("Please give your event a name");
            } else if (wordList[i].equalsIgnoreCase("/from")) {
                if (i + 1 < wordList.length && !wordList[i + 1].equalsIgnoreCase("/to")) {
                    isFrom = true;
                } else {
                    throw new DukeException("Can you tell me about the start date of this event?");
                }
            } else if (wordList[i].equalsIgnoreCase("/to")) {
                if (i + 1 < wordList.length && !wordList[i + 1].equalsIgnoreCase("/from")) {
                    isTo = true;
                } else {
                    throw new DukeException("Can you tell me about the end date of this event?");
                }
            }
        }
        //Handle error
        if (!isFrom || !isTo) {
            throw new DukeException("Your event format seems wrong, please try following pattern:\n" +
                    "event + event Name + /from + Date + /to + Date");
        } else {//If no error
            String currentStage = "name";
            for (String item : wordList) {
                switch (item.toLowerCase()) {
                    case "event":
                        continue; // Skip "event" keyword
                    case "/from":
                        currentStage = "from"; // Switch to "from" stage
                        continue;
                    case "/to":
                        currentStage = "to"; // Switch to "to" stage
                        continue;
                }
                // Append item to the corresponding stage
                switch (currentStage) {
                    case "name":
                        taskNameBuilder.append(item).append(" ");
                        break;
                    case "from":
                        fromBuilder.append(item).append(" ");
                        break;
                    case "to":
                        byBuilder.append(item).append(" ");
                        break;
                }
            }
            taskName = taskNameBuilder.toString();
            String from = fromBuilder.toString();
            by = byBuilder.toString();
            if (!isDateValid(by)) {
                throw new DukeException("Your event already ended");
            } else if (isEventValid(from, by)) {
                throw new DukeException("The end date of your event is earlier than the starting date.");
            } else {
                Event task = new Event(taskName, dateString(from), dateString(by));
                return new AddCommand("event", task);
            }
        }
    }

    /***
     * Function to parse deadline task
     * @param wordList input word list from your input value
     * @return return add deadline command
     */
    public static Command parseDeadline(String[] wordList) throws DukeException {
        StringBuilder taskNameBuilder = new StringBuilder();
        StringBuilder byBuilder = new StringBuilder();
        boolean isBy = false;
        //Check From stage and to stage
        for (int i = 0; i < wordList.length; i++) {
            if (wordList[1].equalsIgnoreCase("/by")) {
                throw new DukeException("Please give your deadline a name");
            } else if (wordList[i].equalsIgnoreCase("/by")) {
                if (i + 1 < wordList.length) {
                    isBy = true;
                    break;
                } else {
                    throw new DukeException("Can you tell me the due date?");
                }
            }
        }
        //Handle error
        if (!isBy) {
            throw new DukeException("Your deadline format seems wrong, please try following pattern:\n" +
                    "deadline + deadline Name + /by + Date");
        } else {
            isBy = false;
            for (String item : wordList) {
                if (item.equalsIgnoreCase("deadline")) {
                    continue;
                } else if (item.equalsIgnoreCase("/by")) {
                    isBy = true;
                    continue;
                }
                if (!isBy) {
                    taskNameBuilder.append(item).append(" ");
                } else {
                    byBuilder.append(item).append(" ");
                }
            }
            taskName = taskNameBuilder.toString();
            by = byBuilder.toString();
            if (isDateValid(by)) {
                Deadline task = new Deadline(taskName, dateString(by));
                return new AddCommand("deadline", task);
            } else {
                throw new DukeException("Your Deadline Date is not a valid date or earlier than today");
            }
        }
    }

}
