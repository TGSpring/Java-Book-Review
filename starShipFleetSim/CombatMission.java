public class CombatMission extends Mission {

    public CombatMission(String title, int difficulty) {
        super(title, difficulty);
    }

    @Override
    public void execute() {
        System.out.println("Engaging hostile forces.");
    }

}
