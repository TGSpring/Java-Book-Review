/**
 * Tyler Spring
 * 1/24/2025
 * Chapter 3 problem 2
 * 
 * Multiple Parameters and Their Order
 * Problem:
 * Write a method formatDate that accepts three parameters:
 * 
 * A day (int)
 * A month (String)
 * A year (int)
 * The method should return a properly formatted string like "20 January 2025".
 * If the order of arguments is wrong (e.g., passing year first), the program
 * should throw an exception with a clear error message.
 * 
 */
public class chp3_3 {

    public static String formatDate(int day, String month, int year) {
        // Validation
        if (year < 1000 && day > 31) {
            throw new IllegalArgumentException("Parameters out of order.");
        }
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Day must be between 1 and 31");
        }
        if (month == null || month.length() == 0) {
            throw new IllegalArgumentException("Month must be valid string");
        }
        if (year <= 0) {
            throw new IllegalArgumentException("Year must be greater than 0");
        }
        return String.format("%d %s %d", day, month, year);
    }

    public static void main(String[] args) {
        try {
            System.out.println(formatDate(20, "January", 2025));
            System.out.println(formatDate(32, "January", 1001));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
