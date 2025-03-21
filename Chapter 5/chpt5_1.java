
/**
 * Tyler Spring
 * 3/21/2025
 * Chapter 5 Question 1
 * 
 * You need to prompt the user to enter a number divisible by 7. Keep prompting
 * until they enter a valid one, then print "Valid entry: <number>".
 * Write the full code using Scanner and a while loop with input validation.
 */

import java.util.Scanner;

public class chpt5_1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = 0;

        while (true) {
            if (sc.hasNextInt()) {
                input = sc.nextInt();
                if (input % 7 == 0) {
                    System.out.println("Valid number: " + input);
                    break;
                } else {
                    System.out.println(input + " is not divisible by 7. Try again.");
                }
            } else {
                System.out.println("That is not valid integer. Try again");
                sc.next();
            }
        }
    }
}
/**
 * Space complexity: O(1) because we are only using fixed amount of 
 * memory regardless of the input.
 * 
 * Time complexity: O(n) in the worst case, where n is the number of
 * iterations before a valid input is entered.
 */