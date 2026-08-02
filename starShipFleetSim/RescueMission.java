public class RescueMission extends Mission {
    public RescueMission(String title, int difficulty) {
        super(title, difficulty);
    }

    @Override
    public void execute() {
        System.out.println("Searching for survivors.");
    }
}
