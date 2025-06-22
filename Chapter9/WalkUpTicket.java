/*
 * Tyler Spring
 * 6/22/2025
 * Chapter 9 Question 6
 * Implement a class called WalkUpTicket to represent a walk-up event ticket. Walk-up tickets are also constructed by number, and they have a price of $50.
 */

//Remember to extends and not implement, this is for an abstract class(Ticket), not an interface.
public class WalkUpTicket extends Ticket {

    public WalkUpTicket(int ticketNumber) {
        super(ticketNumber); // Call the Ticket Constructor
    }

    @Override
    public double getPrice() {
        return 50.0;
    }

}
