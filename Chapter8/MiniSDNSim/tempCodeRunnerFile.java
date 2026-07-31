package MiniSDNSim;

public class Device {
    // Fields
    private final int id;
    private int packetsSent = 0;
    private int packetsReceived = 0;

    private final String name;
    private final String deviceType;
    private final String ipAddress;

    private boolean active = false;

    // Constructor
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
    public void activate() {
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    void receivePacket(Packet packet) {
        if (packet == null) {
            throw new IllegalArgumentException("Packet cannot be null.");
        }
        if (!active) {
            throw new IllegalStateException("Device inactive");
        }
        packetsReceived++;
    }

    void recordPacketSent() {
        if (!active) {
            throw new IllegalStateException("Device inactive. Not recorded.");
        }
        packetsSent++;
    }

    // Getters
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

    public void displayInfo() {
        System.out.println(this);
    }

}
