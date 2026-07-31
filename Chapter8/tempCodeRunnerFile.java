public class miniNetworkSim {
    public static void main(String[] args) {

        // 1. Create networkController.
        int maxDevices = 25;
        NetworkController network = new NetworkController(maxDevices);
        NetworkController tester1 = new NetworkController(2);

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

        /*Device tester2 = new Device(
            3,
            "Phone",
            "Mobile",
            "192.168.1.20"
        );
        */

        // 3. Add devices.
        network.addDevice(computer);
        network.addDevice(router);
        //network.addDevice(tester2);

        //Testing full network.
        //tester1.addDevice(computer);
        //tester1.addDevice(router);
        //tester1.addDevice(tester2);

        // 4. Activate devices.
        computer.activate();
        router.activate();

        //Testing deactivate
        //router.deactivate();

        // 5. Send Packet.
        Packet packet = new Packet(1,1,2, "Hello router");
        network.sendPacket(1, 2, "Hello Router.");
        //network.sendPacket(1, 99, "Testing failure.");

        // 6. Display results.
        System.out.println(computer);

        System.out.println(router);

        System.out.println("Network packets: " + network.getPacketsSent());

    }