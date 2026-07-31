package MiniSDNSim;

/*
    NetworkController manages the network.

    This class is responsible for:
    - Storing connected devices.
    - Finding devices.
    - Coordinating communication.
    - Tracking network statistics.

    NetworkController does NOT represent a device.
    It manages devices but is separate from them.

    This separation keeps Main simple and prevents network logic 
    from being placed inside the testing program.
 */
public class NetworkController {
    /*
     * Arrays are used instead of ArrayList because this project
     * is restricted to Chapter 8 concepts.
     * 
     * The controller owns these arrays because it manages the
     * collection of devices and packets.
     */
    private final Device[] devices;
    private final Packet[] packetHistory;

    // Max capacity limits for the network.
    private final int maxDevices;
    private final int maxPackets;

    // Tracks the current amount of stored objects.
    private int deviceCount;
    private int packetsSent;
    private int packetCount;

    // Used to generate unique packet IDs.
    private int nextPacketId = 1;

    // Constructor
    /*
     * Creates a NetworkController with a fixed capacity.
     * 
     * Arrays require a size when created, so max number of
     * devices and packets must be provided.
     */
    public NetworkController(int maxDevices, int maxPackets) {
        if (maxDevices <= 0) {
            throw new IllegalArgumentException("Can not be 0 or negative devices");
        }

        if (maxPackets <= 0) {
            throw new IllegalArgumentException("Can not be 0 or negative packets.");
        }
        this.maxDevices = maxDevices;
        this.maxPackets = maxPackets;
        devices = new Device[maxDevices];
        packetHistory = new Packet[maxPackets];
    }

    // Methods

    /*
     * Adds a Device object to the network.
     * 
     * NetworkController is responsible for managing devices,
     * so Main does not directly modify the device array.
     */
    public void addDevice(Device device) {
        if (device == null) {
            throw new IllegalArgumentException("Device cannot be null");
        }

        // Device IDs must be unique because the network uses IDs to locate devices.
        if (getDeviceById(device.getId()) != null) {
            throw new IllegalArgumentException("Device ID already exists.");
        }
        if (deviceCount == maxDevices) {
            throw new IllegalStateException("Can not add. Network is full.");
        }

        devices[deviceCount] = device;
        deviceCount++;

    }

    /*
     * Searches the device array for a matching ID.
     * 
     * Returns the Device object if found.
     * Returns null if no device exists with that ID.
     */
    public Device getDeviceById(int id) {
        for (int i = 0; i < deviceCount; i++) {
            if (devices[i].getId() == id) {
                return devices[i];
            }
        }
        return null;
    }

    /*
     * Handles communication between devices.
     * 
     * NetworkController coordinates the transfer because it
     * manages the relationship between multiple devices.
     */
    public void sendPacket(int sourceId, int destinationId, String message) {
        Device source = getDeviceById(sourceId);
        Device destination = getDeviceById(destinationId);

        if (source == null || destination == null) {
            throw new IllegalArgumentException("Invalid source or destination");
        }

        if (packetCount == maxPackets) {
            throw new IllegalStateException("Packet history is full.");
        }
        Packet packet = new Packet(
                nextPacketId,
                sourceId,
                destinationId,
                message);

        nextPacketId++;

        destination.receivePacket(packet);
        source.recordPacketSent();

        // Only stores packets that were successfully delivered.
        packetHistory[packetCount] = packet;
        packetCount++;

        packetsSent++;

    }

    /*
     * Displays all packets that successfully traveled through the network.
     */
    public void displayPacketHistory() {
        for (int i = 0; i < packetCount; i++) {
            System.out.println(packetHistory[i]);
            System.out.println();
        }
    }

    // Searches stored packet history by packet ID.
    public Packet getPacketById(int id) {
        for (int i = 0; i < packetCount; i++) {
            if (packetHistory[i].getPacketId() == id) {
                return packetHistory[i];
            }
        }
        return null;
    }

    // Getter methods provide controlled access to private fields.
    public int getPacketsSent() {
        return packetsSent;
    }

    public int getDeviceCount() {
        return deviceCount;
    }

    public int getPacketCount() {
        return packetCount;
    }

    /*
     * Provides a summary of the current network state.
     */
    @Override
    public String toString() {
        return "Network Information:\n" +
                "Maximum Devices: " + maxDevices + "\n" +
                "Current Devices: " + deviceCount + "\n" +
                "Packets Sent: " + packetsSent + "\n" +
                "Packet History: " + packetCount + "/" + maxPackets;
    }

    /*
     * Displays every device currently connected to the network.
     * 
     * The controller asks each Device object to display itself instead of accessing
     * the Device fields directly.
     */
    public void displayNetwork() {
        for (int i = 0; i < deviceCount; i++) {
            devices[i].displayInfo();
            System.out.println();
        }
    }

}