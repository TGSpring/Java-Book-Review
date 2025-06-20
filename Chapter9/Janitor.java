/*
 * Tyler Spring
 * 6/20/2025
 * Chapter 9 Question 2
 * Write the class Janitor to accompany the other law firm classes described in this chapter. Janitors work twice as many hours per week as other employees 
 * (80 hours/week), they make $30,000($10,000 less than general employees), they get half as much vacation as other employees (only 5 days), and they have an additional method 
 * clean that prints "Workin' for the man." Make sure to interact with the appropriate superclass.  
 */
public class Janitor extends Employee {
    @Override
    public void showHours() {
        System.out.println("I work 80 hours per week");
    }

    @Override
    public void showSalary() {
        System.out.println("My salary is $30,000");
    }

    @Override
    public void showVacation() {
        System.out.println("I receive 5 days of vacation");
    }

    public void clean() {
        System.out.println("Workin' for the man");
    }

}
