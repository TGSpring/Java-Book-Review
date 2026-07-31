package MiniSDNSim;

/*
Device represents a single object connected to the network.

Examples:
    - Computer
    - Router
    - Server

A device is responsible for managing its own state:
    - Identity information
    - Active status 
    - Packet statistics

Device does not control the network or communicate directly with 
other devices. NetworkController handles communication between Device objects.
 */
public class Device {
    /*
     * Fields are private to protect the object's state.
     * 
     * Other classes cannot directly change:
     * - ID
     * - Active status
     * - Packet counters
     * 
     * Changes must happen through methods.
     */

    private final int id;

    private int packetsSent = 0;
    private int packetsReceived = 0;

    // Device information that does not change after creation.
    private final String name;
    private final String deviceType;
    private final String ipAddress;

    // Device start inactive until explicitly activated.
    private boolean active = false;

    // Constructor
    /*
     * Creates a valid Device object.
     * 
     * A device requires identification information when created.
     * Invalid device are prevented from existing by validating input
     * inside the constructor.
     */
    public Device(int id, String name, String deviceType, String ipAddress) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
        if (name == null || deviceType == null || ipAddress == null) {
            throw new IllegalArgumentException("Device information cannot be null");
        }
        this.id = id;
        this.name = name;
        this.deviceType = deviceType;
        this.ipAddress = ipAddress;
    }

    // Methods
    /*
     * Changes the active state of the device.
     * 
     * Only active devices should participate in network communication.
     */
    public void activate() {
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    /*
     * Allows the device to receive a Packet.
     * 
     * The Device updates its own packet count instead of allowing another class is
     * directly modify the field.
     */
    void receivePacket(Packet packet) {
        if (packet == null) {
            throw new IllegalArgumentException("Packet cannot be null.");
        }
        if (!active) {
            throw new IllegalStateException("Device inactive");
        }
        packetsReceived++;
    }

    /*
     * Records a packet being sent by this device
     * 
     * NetworkController calls this when communication succeeds.
     */
    void recordPacketSent() {
        if (!active) {
            throw new IllegalStateException("Device inactive. Not recorded.");
        }
        packetsSent++;
    }

    // Getter methods provide controlled read access to private fields.
    // No setters exist because a device's identity should not change after
    // creation.
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public boolean isActive() {
        return active;
    }

    public int getPacketsSent() {
        return packetsSent;
    }

    public int getPacketsReceived() {
        return packetsReceived;
    }

    /*
     * Provides a readable representation of the Device object.
     * 
     * Allows the object to be printed without manually accessing each field.
     */
    @Override
    public String toString() {
        return "Device Information:\n" +
                "ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Type: " + deviceType + "\n" +
                "IP Address: " + ipAddress + "\n" +
                "Active: " + active + "\n" +
                "Packets Sent: " + packetsSent + "\n" +
                "Packets Received: " + packetsReceived;
    }

    /*
     * Displays device information.
     * 
     * This keeps display responsibility inside the Device class
     * instead of forcing Main or NetworkController to know how
     * Device information is stored.
     */
    public void displayInfo() {
        System.out.println(this);
    }

}
