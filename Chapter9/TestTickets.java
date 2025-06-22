public class TestTickets {
    public static void main(String[] args) {
        // Testing Question 6
        WalkUpTicket ticket = new WalkUpTicket(123);
        System.out.println(ticket); // Should print: Number: 123, Price: 50.0

        // Testing Question 7
        AdvanceTicket ticket2 = new AdvanceTicket(456, 10);
        AdvanceTicket ticket3 = new AdvanceTicket(789, 11);
        AdvanceTicket ticket4 = new AdvanceTicket(1011, 9);

        System.out.println(ticket2);
        System.out.println(ticket3);
        System.out.println(ticket4);

        // Testing Question 8
        StudentAdvanceTicket ticket5 = new StudentAdvanceTicket(113, 10);
        StudentAdvanceTicket ticket6 = new StudentAdvanceTicket(114, 11);
        StudentAdvanceTicket ticket7 = new StudentAdvanceTicket(115, 9);

        System.out.println(ticket5);
        System.out.println(ticket6);
        System.out.println(ticket7);
    }
}