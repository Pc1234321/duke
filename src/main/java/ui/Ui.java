package ui;

import java.util.ArrayList;
import java.util.Scanner;

import task.Task;

public class Ui {
    private static Scanner userInput;

    public Ui() {
        userInput = new Scanner(System.in);
    }

    /***
     * Welcome Page everytime when program startup
     */
    public void welcomeMenu() {
        Scanner in = new Scanner(System.in);
        String logo = "         ____        _        \n"
                + "        |  _ \\ _   _| | _____ \n"
                + "        | | | | | | | |/ / _ \\\n"
                + "        | |_| | |_| |   <  __/\n"
                + "        |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "             Hi! I'm Neuro.\n        Glad to see you again:)");
        System.out.println("********** Press to continue ************");
        in.nextLine();
    }

    /***
     * Help manu when user key in a wrong command
     */
    public void helpMenu() {
        System.out.println("**********************************************");
        System.out.println("*        Please use following commands       *");
        System.out.println("**********************************************");
        System.out.println("* 1. todo TaskName                           *");
        System.out.println("* 2. event TaskName /from Date /to Date      *");
        System.out.println("* 3. deadline TaskName /by Date              *");
        System.out.println("* 4. mark/unmark TaskNumber                  *");
        System.out.println("* 5. list                                    *");
        System.out.println("* 6. delete TaskNumber                       *");
        System.out.println("* 7. find Keyword                            *");
        System.out.println("* 8. date DateTime                           *");
        System.out.println("* 9. quit/bye             (To exit program)  *");
        System.out.println("*                                            *");
        System.out.println("**********************************************");
    }

    /**
     * Print out the item in the task list (for list function)
     *
     * @param todoList the task list to print out
     */
    public static void listMenu(ArrayList<Task> todoList) {
        if (todoList.isEmpty()) {
            System.out.println("////////////////////////////////////////////");
            System.out.println("//    You have nothing needs to be done   //");
            System.out.println("////////////////////////////////////////////");
        } else {
            System.out.println("////////////////////////////////////////////");
            System.out.println("//  Here are the things need to follow up //");
            System.out.println("////////////////////////////////////////////");
            printTask(todoList);
            System.out.println("                                            ");
            System.out.println("============================================");
        }

    }

    /***
     * Function to print tasks from taskList
     * @param todoList Task list
     */
    public static void printTask(ArrayList<Task> todoList) {
        for (int i = 0; i < todoList.size(); i++) {
            String status = todoList.get(i).getStatusIcon();
            Character type = todoList.get(i).getTaskType();
            String from = todoList.get(i).getFrom();
            String by = todoList.get(i).getBy();
            if (type.equals('T')) {
                System.out.println((i + 1) + "." + "[" + type + "]" + "[" + status + "]" + " " + todoList.get(i).getTaskName());
            } else if (type.equals('E')) {
                System.out.println((i + 1) + "." + "[" + type + "]" + "[" + status + "]" + " " + todoList.get(i).getTaskName() +
                        " (From: " + from + " To: " + by + ")");
            } else if (type.equals('D')) {
                System.out.println((i + 1) + "." + "[" + type + "]" + "[" + status + "]" + " " + todoList.get(i).getTaskName() +
                        " (By: " + by + ")");
            }
        }

    }

    public static void nothingToDelete() {
        System.out.println("////////////////////////////////////////////");
        System.out.println("//       You have nothing to delete       //");
        System.out.println("////////////////////////////////////////////");
    }

    public static void nothingToMark() {
        System.out.println("////////////////////////////////////////////");
        System.out.println("//        You have nothing to mark        //");
        System.out.println("////////////////////////////////////////////");
    }

    public static void nothingToSearch() {
        System.out.println("////////////////////////////////////////////");
        System.out.println("//       You have nothing to search       //");
        System.out.println("////////////////////////////////////////////");
    }

    public static void nothingInPeriod() {
        System.out.println("////////////////////////////////////////////");
        System.out.println("//     No task in the mentioned period    //");
        System.out.println("////////////////////////////////////////////");
    }

    public static void nothingInKeyword() {
        System.out.println("////////////////////////////////////////////");
        System.out.println("//       No task contain your keyword     //");
        System.out.println("////////////////////////////////////////////");
    }

    public static void printKeywordList(ArrayList<Task> findList) {
        System.out.println("////////////////////////////////////////////");
        System.out.println("//   Here are tasks contain your keyword  //");
        System.out.println("////////////////////////////////////////////");
        printTask(findList);
        System.out.println("                                            ");
        System.out.println("============================================");
    }

    public static void printPeriodList(ArrayList<Task> findList) {
        System.out.println("////////////////////////////////////////////");
        System.out.println("//  The tasks within the mentioned period //");
        System.out.println("////////////////////////////////////////////");
        Ui.printTask(findList);
        System.out.println("                                            ");
        System.out.println("============================================");
    }

    /***
     * Greeting when user exit the program
     */
    public void exitProgram() {
        System.out.println("Bye! See you next time!");
    }

    /***
     * Return the string read from user
     */
    public String readCommand() {
        return userInput.nextLine();
    }

}

