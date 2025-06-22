/*
 * Tyler Spring
 * 6/20/2025
 * Chapter 9 Question 5
 * For the next four problems, consider the task of representing types of tickets to campus events. Each ticket has a unique number and price. There are three types of tickets:
 * walk-up tickets, advance tickets, and student advance tickets. 
 * - Walk-up tickets are purchased the day of the event and cost $50.
 * - Advance tickets purchased 10 or more days before the event cost $30, and advance tickets purchased fewer than 10 days before the event cost $40.
 * - Student advance tickets are sold at half price of normal advance tickets: When they are purchased 10 or more days early they cost $15, and when they are purchased fewer 
 * than 10 days early they cost $20.
 * Implement a class called Ticket that will serve as the superclass for all three types of tickets. Define all common operations in this class, and specify all differing
 * operations in such a way that every subclass must implement them. No actual objects of type Ticket will be created: Each actual ticket will be an object of a subclass type.
 * Define the following operations:
 * - The ability to construct a ticket by number.
 * - The ability to ask for a ticket's price.
 * - The ability to println a ticket object as a String. An example would be "Number: 17, Price: 50.0".
 */

public abstract class Ticket {
    // Field for ticketNumber
    private int ticketNumber;

    // Constructor for creating ticket number.
    public Ticket(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public abstract double getPrice();

    // Using protected getter for StudentAdvanceTicket.
    protected int getTicketNumber() {
        return ticketNumber;
    }

    @Override
    public String toString() {
        return "Number: " + ticketNumber + ", Price: " + getPrice();
    }

}
