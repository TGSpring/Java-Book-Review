package MissionControlTask;

public class MissionTask implements Comparable<MissionTask> {
    // Stores the information and current state of this mission task.
    private String taskName;
    private String astronaut;
    private int priority;
    private double estTime;
    private boolean completed;

    // Constructor
    // Initializes the task's information.
    // completed defaults to false when a new MissionTask is created.
    public MissionTask(String taskName, String astronaut, int priority,
            double estTime) {

        this.taskName = taskName;
        this.astronaut = astronaut;
        this.priority = priority;
        this.estTime = estTime;

    }

    // Task State methods
    // Methods for changing and checking the task's completion state.
    // complete() updates the state and isComplete() checks the current state.
    public void complete() {
        completed = true;
    }

    public boolean isComplete() {
        return completed;

    }

    // Comparable / sorting
    // Sort higher-priority task first.
    // When this task has a higher priority than other, compareTo() produces a
    // negative result so this task
    // comes first.
    // if tasks have equal priority they are sorted alphabetically.
    @Override
    public int compareTo(MissionTask other) {
        if (this.priority != other.priority) {
            return Integer.compare(other.priority, this.priority);
        }

        return this.taskName.compareTo(other.taskName);
    }

    // Getter for taskName
    public String getTaskName() {
        return taskName;
    }

    // Getter for estTime.
    public double getEstTime() {
        return estTime;
    }

    // String representation
    // Provides a readable representation of the task when it is printed.
    @Override
    public String toString() {
        return "Mission: " + taskName + "\n" +
                "Priority: " + priority + "\n" +
                "Completion status: " + completed;
    }
}
