public class LaserWeapon implements Weapon {
    @Override
    public int getDamage() {
        return 25;
    }

    @Override
    public void fire() {
        System.out.println("Laser fired!");
    }
}
