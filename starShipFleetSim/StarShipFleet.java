/*
Starship Fleet Simulation

This project demonstrates Chapter 9 OOP concepts:
inheritance, abstract classes, interfaces, and polymorphism.

The simulator uses parent references (Ship and Mission)
to interact with different child objects without needing 
separate logic for each specific type.
 */
public class StarShipFleet {
    public static void main(String[] args) {
        // Ship Array.

        /*
         * The fleet is stored using the parent ship type.
         * 
         * Even though each object is a different subclass, Java uses
         * runtime method dispatch to execute the correct overridden behavior.
         * 
         * This allows the program to work with many object types without
         * needing separate logic for each one.
         */
        Ship[] fleet = {
                new Fighter("Falcon", 100, 300, new LaserWeapon()),
                new CargoShip("Hauler", 150, 100, 500),
                new Explorer("Voyager", 120, 200, "Advanced Scanner")
        };

        /*
         * The loop only knows that each object is a Ship.
         * 
         * Java determines the actual object type at runtime and calls
         * the correct overridden methods.
         * 
         * This demonstrates runtime polymorphism.
         */
        System.out.println("--- Fleet Status ---");
        for (Ship ship : fleet) {
            System.out.println(ship);
            ship.performMission();
            System.out.println();
        }

        // Mission Array
        Mission[] missions = {
                new CombatMission("Enemy Base Assault", 5),
                new RescueMission("Lost Crew Recovery", 3)
        };

        /*
         * Missions are stored using the parent Mission type.
         * 
         * Each mission provides its own implementation of execute(),
         * allowing new mission types to be added without changing
         * the code that runs missions.
         */

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