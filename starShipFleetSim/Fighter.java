
/*
Fighter represents a combat-oriented ship.

It inherits general ship information from Ship while adding
specialized behavior and data related to combat.
 */
public class Fighter extends Ship {

    /*
     * Fighter uses the Weapon interface instead of depending on a specific
     * weapon class.
     * 
     * Because of interface polymorphism, a Fighter can use any object that
     * follows the Weapon contract, such as LaserWeapon or MissileWeapon.
     */
    private Weapon weapon;

    /*
     * super() sends shared ship information to the parent class.
     * 
     * Fighter only initializes the fields that make it unique.
     */
    public Fighter(String name, int health, int speed, Weapon weapon) {
        super(name, health, speed);
        this.weapon = weapon;
    }

    // Methods
    @Override
    public String getType() {
        return "Fighter";
    }

    @Override
    public void performMission() {
        System.out.println("Engaging enemy targets.");
    }

    /*
     * Fighter does not need to know how a weapon works internally.
     * 
     * It only knows that every Weapon can fire.
     * The actual implementation is decided at runtime.
     */
    public void attack() {
        System.out.println("Weapon damage: " + weapon.getDamage());
        weapon.fire();
    }
}
