# Mini SDN Network Simulator

## Overview

Mini SDN Network Simulator is a small object-oriented Java project that models
how devices communicate through a network.

The purpose of this project is to practice:

- Classes and objects
- Encapsulation
- Constructors
- Private fields
- Methods
- Object interaction
- Separation of responsibilities

## Project Structure

├── Device.java
├── Packet.java
├── NetworkController.java
└── miniNetworkSim.java

## Classes

### Device

Represents a device connected to the network.

Examples:

- Computer
- Router
- Server

Responsibilities:

- Store device information
- Track active status
- Receive packets
- Track packets sent and received

### Packet

Represents a message traveling through the network.

Responsibilities:

- Store packet ID
- Store source device
- Store destination device
- Store message contents

### NetworkController

Manages the network and coordinates communication between devices.

Responsibilities:

- Store devices
- Add devices
- Find devices by ID
- Send packets
- Track network statistics

### miniNetworkSim

Main testing program.

Responsibilities:

- Create objects
- Connect objects together
- Test network communication
- Display results

## Example Output

Device Information:
ID: 1
Name: Tylers-Pc
Type: Computer
IP Address: 192.168.1.10
Active: true
Packets Sent: 1
Packets Received: 1

Network Information:
Maximum Devices: 25
Current Devices: 2
Packets Sent: 2
Packet History: 2/25

## Concepts Demonstrated

### Encapsulation

Fields are private and accessed through methods.

Example:
private boolean active;

The device controls its own state through:
activate()
deactivate()

### Object Interaction

Objects communicate with each other:
NetworkController
|
v
Packet
|
v
Device

### Separation of Responsibilities

Each class has a specific purpose:

- Device represents a machine
- Packet represents a message
- NetworkController manages communication
- Main tests the program

## Future Improvements

Possible extensions:

- Device removal
- Packet routing
- Network connections
- Packet size tracking
- More advanced SDN behavior

## How to Run

From the Chapter8 directory:

```bash
javac MiniSDNSim/*.java
java MiniSDNSim.miniNetworkSim