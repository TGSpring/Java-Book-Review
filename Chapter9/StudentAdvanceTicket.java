/*
 * Tyler Spring
 * 6/22/2025
 * Chapter 9 Question 8
 *Implement a class called StudentAdvanceTicket to represent tickets purchased in advance by students. A student 
 advance ticket is constructed with a ticket number and with the number of days in advance that the ticket was purchased 
 Student advance tickets purchased 10 or more days before the event cost $15, and student advance tickets 
 purchased fewer than 10 days before the event cost $20 (half normal advance ticket). When a student advance ticket 
 is printed, the String should mention that the student must show his or her student ID.
 */

public class StudentAdvanceTicket extends Ticket {

    private int SdaysInAdvance;

    public StudentAdvanceTicket(int ticketNumber, int SdaysInAdvance) {
        super(ticketNumber);
        this.SdaysInAdvance = SdaysInAdvance;
    }

    @Override
    public double getPrice() {
        if (SdaysInAdvance >= 10) {
            return 15.0;
        } else {
            return 20.0;
        }
    }

    // Using toString from Ticket, then appending student ID.
    @Override
    public String toString() {
        return super.toString() + " Must show Student ID";
    }
}
