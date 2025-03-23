
/**
 * Tyler Spring
 * 3/23/2025
 * 
 * Chapter 5 Question 4
 * 4. Assertion Guard
 * Write a method public static double safeDivide(double a, double b) that uses
 * an assertion to ensure b != 0. If b is 0, the program should abort.
 * Otherwise, return a / b.
 */
public class chpt5_4 {
    public static void main(String[] args) {
        System.out.println(safeDivide(2, 2));
        System.out.println(safeDivide(2, 0));

    }

    public static double safeDivide(double a, double b) {
        assert b != 0 : "Divider b must not be zero";

        return a / b;
    }
}
/**
 * Space complexity is O(1). We do not create any data structures, just a method
 * that returns a double.
 * 
 * Time complexity is also O(1), worst case or best case run the same amount of
 * times.
 */