public class CargoShip extends Ship {
    private int cargoCap;

    // Constructor
    public CargoShip(String name, int health, int speed, int cargoCap) {
        super(name, health, speed);
        this.cargoCap = cargoCap;
    }

    // Methods
    @Override
    public String getType() {
        return "Cargo Ship";
    }

    @Override
    public void performMission() {
        System.out.println("Moving cargo.");
    }

    public void loadCargo() {
        System.out.println("Loading cargo.");
    }

    // Getters
    public int getCargoCap() {
        return cargoCap;
    }

}
