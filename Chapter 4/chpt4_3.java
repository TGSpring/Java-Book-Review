/**
 * Tyler Spring
 * 3/14/2025
 * Chapter 4 question 3
 * 
 * Cumulative Algorithms (Loops + Running Sums/Products)
 * Digit Sum
 * 
 * Write a method sumDigits(int num) that takes an integer and returns the sum
 * of its digits.
 * Example: sumDigits(251) → 2 + 5 + 1 = 8
 * Factorial with a Loop
 * 
 */
public class chpt4_3 {
    public static void main(String[] args) {
        System.out.println(sumDigits(101));
        System.out.println(sumDigits(102));
        System.out.println(sumDigits(1011111897));
    }

    public static int sumDigits(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10; // adds the last digit
            num /= 10; // removes the last digit
        }
        return sum;
    }
}
