public class Explorer extends Ship {
    private String scanner;

    // Constructor
    public Explorer(String name, int health, int speed, String scanner) {
        super(name, health, speed);
        this.scanner = scanner;
    }

    // Methods
    @Override
    public String getType() {
        return "Exploration Ship";
    }

    @Override
    public void performMission() {
        System.out.println("Scanning unknown planets");
    }

    public void scanPlanet() {
        System.out.println("Scanning planet.");
    }

    // Getters
    public String getScanner() {
        return scanner;
    }

}
