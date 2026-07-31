package MiniSDNSim;

/*
Packet represents a message traveling through the network.

A Packet is its own class because messages have their own information
that should be tracked separately from the devices sending and receiving them.

Packets are immutable after creation.
    - Fields are private.
    - Fields are final.
    - No setters are provided.

Once a packet is created, its information should not change while it
travels through the network.
*/
public class Packet {
    // Unique identifier used to track individual packets.
    private final int packetId;

    // Stores which device sent the packet.
    private final int sourceId;

    // Stores which device should receive the packet.
    private final int destinationId;

    // The actual message being transmitted.
    private final String message;

    // Constructor
    /*
     * Creates a Packet object with a source, destination, and message.
     * 
     * Validation is performed here so that invalid Packet objects cannot be
     * created.
     */
    public Packet(int packetId, int sourceId, int destinationId, String message) {
        if (packetId <= 0 || sourceId <= 0 || destinationId <= 0) {
            throw new IllegalArgumentException("Invalid ID for packet, source or destination.");
        }
        if (message == null) {
            throw new IllegalArgumentException("Message can not be null");
        }
        this.packetId = packetId;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.message = message;

    }

    // Getter methods provide read-only access to private fields.
    public int getPacketId() {
        return packetId;

    }

    public int getSourceId() {
        return sourceId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public String getMessage() {
        return message;
    }

    /*
     * Provides a readable representation of the Packet object.
     * Allows the object to be printed directly instead of manually printing each
     * field.
     */
    @Override
    public String toString() {
        return "Packet Information:\n" +
                "Packet ID: " + packetId + "\n" +
                "Source ID: " + sourceId + "\n" +
                "Destination ID: " + destinationId + "\n" +
                "Message: " + message;
    }
}