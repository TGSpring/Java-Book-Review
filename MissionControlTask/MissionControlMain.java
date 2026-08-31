package MissionControlTask;

import java.util.Scanner;

public class MissionControlMain {
    public static void main(String[] args) {

        // MissionControl manages the collection of mission tasks.
        MissionControl controller = new MissionControl();

        // Scanner handles user input from console.
        Scanner input = new Scanner(System.in);

        int choice;

        // Continue displaying the menu until the user chooses Exit.
        do {
            System.out.println("===== MISSION CONTROL =====");
            System.out.println("1. Add Task");
            System.out.println("2. Display Tasks");
            System.out.println("3. Search Task");
            System.out.println("4. Complete Task");
            System.out.println("5. Remove Task");
            System.out.println("6. Sort Tasks");
            System.out.println("7. Statistics");
            System.out.println("8. Exit");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {

                // Create a new task from information entered by the user.
                case 1:
                    System.out.println("Enter task name:");
                    String taskName = input.nextLine();

                    System.out.println("Enter astronaut's name:");
                    String astroName = input.nextLine();

                    System.out.println("Enter level of priority:");
                    int priority = input.nextInt();

                    System.out.println("Enter estimated completion time:");
                    double estTime = input.nextDouble();

                    MissionTask task = new MissionTask(taskName, astroName, priority, estTime);

                    controller.addTask(task);
                    break;

                // Display every task currently stored by MissionControl.
                case 2:
                    for (MissionTask currTask : controller.getTasks()) {
                        System.out.println(currTask);
                    }
                    break;

                // Search for a task by name.
                case 3:
                    System.out.println("Enter the task name to search for:");
                    String findTask = input.nextLine();

                    MissionTask foundTask = controller.findTask(findTask);

                    if (foundTask == null) {
                        System.out.println("Task not found.");
                    } else {
                        System.out.println(foundTask);
                    }
                    break;

                // Mark a task as complete.
                case 4:
                    System.out.println("Enter task name to complete:");
                    String comTask = input.nextLine();
                    System.out.println(controller.completeTask(comTask));
                    break;

                // Remove a task by name.
                case 5:
                    System.out.println("Enter name of task to remove:");
                    String remove = input.nextLine();
                    System.out.println(controller.removeTask(remove));
                    break;

                // Sort tasks using MissionTask's Comparable implementation.
                case 6:
                    controller.sortTasks();
                    System.out.println("Tasks sorted.");
                    break;

                // Display statistics calculated by MissionControl.
                case 7:
                    System.out.println("Statistics");
                    System.out.println("----------");
                    System.out.println("Completed tasks: " + controller.countCompletedTasks());
                    System.out.println("Total estimated time: " + controller.countEstTime());
                    break;

            }
        } while (choice != 8);

    }
}
