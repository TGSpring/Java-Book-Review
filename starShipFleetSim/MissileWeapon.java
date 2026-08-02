public class MissileWeapon implements Weapon {
    @Override
    public int getDamage() {
        return 50;
    }

    @Override
    public void fire() {
        System.out.println("Missile launched!");
    }

}
 