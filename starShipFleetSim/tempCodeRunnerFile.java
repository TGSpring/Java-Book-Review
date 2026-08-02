public class StarShipFleet {
    public static void main(String[] args) {
        // Ship Array.
        Ship[] fleet = {
                new Fighter("Falcon", 100, 300, new LaserWeapon()),
                new CargoShip("Hauler", 150, 100, 500),
                new Explorer("Voyager", 120, 200, "Advanced Scanner")
        };

        System.out.println("--- Fleet Status ---");
        for (Ship ship : fleet) {
            System.out.println(ship.getType());
            System.out.println(ship.getName());
            System.out.println("Health: " + ship.getHealth());
            System.out.println("Speed: " + ship.getSpeed());

            ship.performMission();

            System.out.println();
        }

        // Mission Array
        Mission[] missions = {
                new CombatMission("Enemy Base Assault", 5),
                new RescueMission("Lost Crew Recovery", 3)
        };

        System.out.println("--- Mission Status ---");

        for (Mission mission : missions) {
            System.out.println(mission);
            mission.execute();
            System.out.println();
        }

        // Testing Fighter.
        Fighter fighter1 = new Fighter("MR72", 100, 300, new LaserWeapon());

        System.out.println(fighter1.getType());
        fighter1.performMission();
        fighter1.attack();

        // Testing weapons
        Fighter laserFighter = new Fighter("Raptor", 100, 300, new MissileWeapon());
        System.out.println(laserFighter.getType());
        laserFighter.performMission();
        laserFighter.attack();

        Ship testShip = new Fighter("Test Fighter", 100, 200, new MissileWeapon());

        System.out.println(testShip.getType());
        testShip.performMission();
    }
}