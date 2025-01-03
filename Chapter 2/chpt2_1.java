/**
 * Tyler Spring
 * 1/3/2025
 * Prints the sum of all integers from 1 to 100
 */
public class chpt2_1 {

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i; // Add the current value of i to sum.
        }
        System.out.println("The sum is: " + sum);
    }
}
