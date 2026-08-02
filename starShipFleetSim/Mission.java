/*
Mission is an abstract representation of a task assigned to ships.

Different mission types share common information but implement their own execution behavior.
 */
public abstract class Mission {
    private String title;
    private int difficulty;

    // Constructor
    public Mission(String title, int difficulty) {
        if (title == null || difficulty <= 0) {
            throw new IllegalArgumentException("Titles or difficulty can not be null.");

        }

        this.title = title;
        this.difficulty = difficulty;
    }

    public abstract void execute();

    @Override
    public String toString() {
        return "Mission: " + title +
                "\nDifficulty: " + difficulty;
    }

    public String getTitle() {
        return title;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
