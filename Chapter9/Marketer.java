/*
 * Tyler Spring
 * 6/20/2025
 * Chapter 9 Question 1
 * Write the class Marketer to accompany the other law firm classes described in this chapter. Marketers make 
 * $50,000($10,000 more than general employees) and have an additional method called advertise that prints "Act now, while supplies last!" 
 * Make sure to interact with the superclass as appropriate. 
 */
public class Marketer extends Employee{
    public void applyForVacation() {
        System.out.println("Use the yellow vacation form.");
    }

    // This is the only added behavior.
    public void advertise() {
        System.out.println("Act now, while supplies last!");
    }

    public void showHours() {
        System.out.println("I work 40 hours per week.");
    }

    // Different from standard salary.
    public void showSalary() {
        System.out.println("My salary is $50,000.");
    }

    public void showVacation() {
        System.out.println("I receive 2 weeks vacation.");
    }
}