/*
 * Tyler Spring
 * 6/22/2025
 * Chapter 9 Question 7
 * Implement a class called AdvanceTicket to represent tickets purchased in advance.
 * An advance ticket is constructed with a ticket number and with the number of days 
 * in advance that ticket was purchased. Advance tickets purchased 10 or more days before
 * the event cost $30, and advance tickets purchased fewer than 10 days before the event cost $40.
 */
public class AdvanceTicket extends Ticket {

    private int daysInAdvance;

    public AdvanceTicket(int ticketNumber, int daysInAdvance) {
        super(ticketNumber); // Remember that the super call must always be first. Will give compiler error.
        this.daysInAdvance = daysInAdvance;

    }

    @Override
    public double getPrice() {
        if (daysInAdvance >= 10) {
            return 30.0;
        } else {
            return 40.0;
        }
    }
}
