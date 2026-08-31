package MissionControlTask;

import java.util.ArrayList;
import java.util.Collections;

public class MissionControl {

    // Stores all mission tasks managed by Mission Control.
    private ArrayList<MissionTask> tasks;

    // Creates an empty ArrayList to store mission tasks.
    public MissionControl() {
        tasks = new ArrayList<>();
    }

    // Adds a new task to the ArrayList.
    public void addTask(MissionTask task) {
        tasks.add(task);
    }

    // Returns current list of mission tasks.
    public ArrayList<MissionTask> getTasks() {
        return tasks;
    }

    // Sorts tasks using MissionTask's compareTo() method.
    public void sortTasks() {
        Collections.sort(tasks);

    }

    // Searches the ArrayList for a task with the given name.
    // Returns the first matching task or null if no match is found.
    public MissionTask findTask(String taskName) {
        for (MissionTask task : tasks) {
            if (task.getTaskName().equals(taskName)) {
                return task;
            }
        }

        return null;
    }

    // Finds a task by name and marks it as complete.
    // Returns true if the task was found and completed.
    public boolean completeTask(String taskName) {
        MissionTask task = findTask(taskName);
        if (task != null) {
            task.complete();
            return true;
        }

        return false;
    }

    // Finds a task by name and removes it from the ArrayList.
    // Returns true if the task was found and removed.
    public boolean removeTask(String taskName) {
        MissionTask task = findTask(taskName);
        if (task != null) {
            tasks.remove(task);
            return true;
        }

        return false;
    }

    // Counts the number of completed tasks in the ArrayList.
    public int countCompletedTasks() {
        int completedTasks = 0;
        for (MissionTask task : tasks) {
            if (task.isComplete()) {
                completedTasks++;
            }
        }
        return completedTasks;
    }

    // Calculates the total estimated completion time for all tasks.
    public double countEstTime() {
        double totalEstTime = 0;
        for (MissionTask task : tasks) {
            totalEstTime += task.getEstTime();
        }
        return totalEstTime;
    }
}
