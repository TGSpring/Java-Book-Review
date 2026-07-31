/*
 * Chapter 8 Project — Mini Network Simulator
 *
 * Goal:
 * Build a small object-oriented network simulator that models how devices
 * communicate through a network.
 *
 * This project is designed to practice:
 * - Classes and objects
 * - Encapsulation
 * - Constructors
 * - Private fields
 * - Accessor and mutator methods
 * - Object interaction
 * - Class responsibility
 * - Cohesion
 * - Separation of concerns
 *
 *
 * PROJECT RULES:
 *
 * Allowed:
 * - Classes
 * - Objects
 * - Fields
 * - Constructors
 * - Methods
 * - Private/public modifiers
 * - Getters/setters
 * - Scanner
 * - Loops
 * - Conditionals
 * - Arrays
 *
 * Not allowed:
 * - ArrayList
 * - HashMap
 * - Collections
 * - Inheritance
 * - Interfaces
 * - Abstract classes
 * - Files
 * - GUI
 * - Threads
 * - External libraries
 *
 *
 * PROJECT STRUCTURE:
 *
 * MiniNetworkSimulator
 *
 *      |
 *      |
 *      +-- NetworkController
 *      |
 *      +-- Device
 *      |
 *      +-- Packet
 *
 *
 * ============================================================
 * CLASS 1: Device
 * ============================================================
 *
 * Purpose:
 * Represents a device connected to the network.
 *
 * Examples:
 * - Computer
 * - Server
 * - Router
 *
 *
 * Fields:
 *
 * - Unique ID
 * - Device name
 * - Active status
 *
 * Fields should be private to maintain encapsulation.
 *
 *
 * Constructor:
 *
 * Create a valid Device object.
 *
 * Questions to consider:
 *
 * - What information does a device need when created?
 * - Should a device start active or inactive?
 * - Can a device exist without an ID?
 *
 *
 * Methods:
 *
 * activate()
 * - Turns the device on.
 *
 *
 * deactivate()
 * - Turns the device off.
 *
 *
 * receivePacket(Packet packet)
 * - Allows the device to receive a packet.
 *
 *
 * displayInfo()
 * - Displays information about the device.
 *
 *
 *
 * ============================================================
 * CLASS 2: Packet
 * ============================================================
 *
 * Purpose:
 * Represents a message traveling through the network.
 *
 *
 * Fields:
 *
 * - Source device
 * - Destination device
 * - Message
 *
 *
 * Constructor:
 *
 * Creates a packet with:
 *
 * - Source
 * - Destination
 * - Message
 *
 *
 * Questions to consider:
 *
 * - Should packets have setters?
 * - Should packet information be changed after creation?
 *
 *
 * Methods:
 *
 * - Get source
 * - Get destination
 * - Get message
 *
 *
 *
 * ============================================================
 * CLASS 3: NetworkController
 * ============================================================
 *
 * Purpose:
 * Manages the network and coordinates communication between devices.
 *
 * The controller should manage devices but should not represent a device.
 *
 *
 * Fields:
 *
 * - Array of Device objects
 *
 * Note:
 * ArrayList is not allowed because this project is restricted to
 * Chapter 8 concepts.
 *
 *
 * Methods:
 *
 * addDevice(Device device)
 *
 * Responsibilities:
 * - Add devices to the network
 * - Handle full network cases
 * - Prevent duplicate devices if necessary
 *
 *
 * displayNetwork()
 *
 * Responsibilities:
 * - Display all devices
 * - Show device status
 *
 *
 * sendPacket(Packet packet)
 *
 * Responsibilities:
 * - Handle sending messages through the network
 * - Determine if delivery is possible
 *
 *
 * Questions to consider:
 *
 * - Should NetworkController send the packet?
 * - Should Device send the packet?
 * - Which class owns this responsibility?
 *
 *
 *
 * ============================================================
 * MAIN PROGRAM: MiniNetworkSimulator
 * ============================================================
 *
 * Purpose:
 * Test and demonstrate the objects working together.
 *
 *
 * Main should:
 *
 * 1. Create a NetworkController
 *
 * 2. Create several Device objects
 *
 * 3. Add devices to the network
 *
 * 4. Create Packet objects
 *
 * 5. Send packets
 *
 * 6. Display network results
 *
 *
 * Main should NOT:
 *
 * - Directly modify object fields
 * - Manage device state
 * - Contain network logic
 *
 * Main is responsible only for running and testing the program.
 *
 *
 *
 * ============================================================
 * DESIGN QUESTIONS
 * ============================================================
 *
 * Answer these before coding:
 *
 *
 * 1. Why should Device fields be private?
 *
 *
 *
 * 2. Why should Packet be its own class instead of just passing
 *    around a String message?
 *
 *
 *
 * 3. What responsibilities belong to Device?
 *
 *
 *
 * 4. What responsibilities belong to NetworkController?
 *
 *
 *
 * 5. What responsibilities belong to Main?
 *
 *
 *
 * 6. If a new feature is added later (example:
 *    packet size or device IP address), which class should change?
 *
 *    Why?
 *
 *
 *
 * ============================================================
 * STRETCH GOALS
 * ============================================================
 *
 * Optional improvements after the base project works:
 *
 * - Add maximum network size
 * - Prevent inactive devices from receiving packets
 * - Track number of packets sent
 * - Add device type
 * - Add IP addresses
 * - Remove devices from network
 *
 *
 *
 * ============================================================
 * SUCCESS CRITERIA
 * ============================================================
 *
 * Before considering this project complete, explain:
 *
 * - Why Packet is a separate object
 * - Why NetworkController exists
 * - Why Main should not contain network logic
 * - Why fields are private
 * - How objects communicate with each other
 *
 *
 * Main lesson:
 *
 * A program is not just functions manipulating data.
 * A program is a collection of objects with responsibilities
 * that interact with each other.
 *
 */
package MiniSDNSim;
 public class miniNetworkSim {
    public static void main(String[] args) {

        // 1. Create networkController.
        int maxDevices = 25;
        int maxPackets = 25;
        NetworkController network = new NetworkController(maxDevices, maxPackets);

        // 2. Create devices.
        Device computer = new Device(
                1,
                "Tylers-Pc",
                "Computer",
                "192.168.1.10");

        Device router = new Device(
                2,
                "Home-Router",
                "Router",
                "192.168.1.1");

        /*
         * Device tester2 = new Device(
         * 3,
         * "Phone",
         * "Mobile",
         * "192.168.1.20"
         * );
         */

        // 3. Add devices.
        network.addDevice(computer);
        network.addDevice(router);
        // network.addDevice(tester2);

        // Testing full network.
        // tester1.addDevice(computer);
        // tester1.addDevice(router);
        // tester1.addDevice(tester2);

        // 4. Activate devices.
        computer.activate();
        router.activate();

        // Testing deactivate
        // router.deactivate();

        // 5. Request network to send packet.
        network.sendPacket(1, 2, "Hello Router.");
        // network.sendPacket(1, 99, "Testing failure.");

        network.sendPacket(2, 1, "Hello Computer.");

        // 6. Display results.
        System.out.println(computer + "\n");

        System.out.println(router + "\n");

        System.out.println("Network packets: " + network.getPacketsSent() + "\n");

        System.out.println(network);

        network.displayPacketHistory();

        System.out.println(network.getPacketById(1));

    }

}
