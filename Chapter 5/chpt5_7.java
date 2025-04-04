
/**
 * Tyler Spring
 * 4/4/2025
 * Chapter 5 Question 7
 * Input Validation with hasNextInt()
 * Prompt the user to enter an even number. Validate that the input is an
 * integer using hasNextInt() and that it’s even. Keep prompting until they get
 * it right.
 */

import java.util.Scanner;

public class chpt5_7 {
    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
        int input;
        boolean trigger = false;

        System.out.println("Please enter an even integer.");
        while (!trigger) {
            if (!user.hasNextInt()) {
                System.out.println("Not an integer. Try again.");
                user.next();
                continue;
            }
            input = user.nextInt();
            if (input % 2 != 0) {
                System.out.println("Not even integer. Try again");
            }

            else {
                System.out.println("Number valid");
                trigger = true;
            }
        }
    }
}
/**
 * Time complexity in the worst case is O(N), where n is the number of inputs
 * from the user.
 * Space complexity is O(1), we are not making any new structures and only use a
 * few variables.
 */