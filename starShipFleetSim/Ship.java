// Ship class.
/*
Abstract parent class representing the common structure of all ships.

This class cannot create a generic Ship object because a ship does not 
have a specific role until it becomes a specialized type.

Subclasses inherit shared data (name, health, speed) and provide their 
own behavior through overridden methods.
 */
public abstract class Ship {
    private String name;
    private int health;
    private int speed;

    // Constructor
    /*
     * The parent constructor initializes the shared fields.
     * 
     * Subclasses call this constructor using super() instead of
     * rewriting the same initialization code.
     */
    public Ship(String name, int health, int speed) {

        if (name == null || health <= 0 || speed <= 0) {
            throw new IllegalArgumentException("Values can not be null or less than 0");
        }

        this.name = name;
        this.health = health;
        this.speed = speed;

    }

    /*
     * These methods define the contract that every ship must follow.
     * 
     * The parent class knows every ship has a type and performs missions,
     * but the child classes decide how those behaviors work.
     */

    public abstract String getType();

    public abstract void performMission();

    // Getters.
    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return getType() +
                "\nName: " + name +
                "\nHealth: " + health +
                "\nSpeed: " + speed;
    }
}
