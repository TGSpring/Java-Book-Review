/**
 * Tyler Spring
 * 3/14/2025
 * Chapter 4 question 4
 * 
 * Factorial with a Loop
 * 
 * Write a method factorial(int n) that returns n! using a loop.
 * Example: factorial(5) = 5 * 4 * 3 * 2 * 1 = 120
 */

public class chpt4_4 {
    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(factorial(6));
        System.out.println(factorial(7));
    }

    public static int factorial(int n) {
        int num = 1;

        while (n > 0) {
            num *= n; // Product is multiplied by n and updated product
            n--; // Decrements n
        }
        return num;
    }
}
