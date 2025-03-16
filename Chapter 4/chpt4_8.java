/**
 * Tyler Spring
 * 3/16/2025
 * Chapter 4 question 8
 * Password Strength Checker
 * 
 * Write a method checkPassword(String password) that returns "Strong" if:
 * It contains at least one digit, one uppercase letter, and is at least 8
 * characters long.
 * Otherwise, return "Weak"
 */
public class chpt4_8 {
    public static void main(String[] args) {
        System.out.println(checkPassword("Test123456"));
        System.out.println(checkPassword(""));
        System.out.println(checkPassword("Test123*&^"));
    }

    public static String checkPassword(String str) {
        if (str.isEmpty() || str.length() < 8)
            return "Weak";

        if (!str.matches(".*[A-Z].*"))
            return "Weak";

        if (!str.matches(".*[0-9].*"))
            return "Weak";

        return "Strong";

    }
}

/**
 * Analysis
 * 
 * We iterate over N characters per string.
 * We are not creating any new structures
 * The function runs once performing 3 checks.
 * 
 * Time and Space complexity are both O(n).
 * Each regex check iterates over the string (O(n) time per check), and since it
 * performs 3 independent checks,
 * it remains O(n) overall (constants are ignored in Big - O notation). No new
 * data structures are created either.
 */
