/*
 * Tyler Spring
 * 8/15/2025
 * Chapter 12 Question 1
 * Write a recursive method called starString that accepts an integer as a parameter and prints to the console a string of stars 
 * (asterisks) that is 2^n.
 * For example:
 * starString(0) should print * (because 2^0 == 1).
 */
public class Chpt12_Q1 {

    public static void main(String[] args) {
        int n = 5;
        starString(n);
    }

    /*
     * In this example of n = 5, first the call
     * starString(5), calls starString(4), then again, starString(4).
     * Each starString(4) calls starString(3).
     * This recurs until it reaches starString(0). Which prints a single *.
     * For each level of n, you call the previous level n - 1 twice.
     * This means that the number of stars doubles each level. Resulting in 2^n
     * stars total.
     */
    public static void starString(int n) {
        if (n == 0) {
            System.out.print("*");
        } else {
            starString(n - 1);
            starString(n - 1);
        }
    }
}
/*
 * Space complexity is O(n), deepest the call stack gets is n, no structures
 * used.
 * 
 * Time complexity is O(2^n), each call to starString makes two recursive calls
 * to starString(n-1).
 * This forms a binary tree recursion tree.
 * The total number of calls is:
 * Level n: 1 call.
 * Level n-1: 2 calls.
 * Level n-2: 4 calls.
 * ...
 * Level 0: 2^n calls.
 */