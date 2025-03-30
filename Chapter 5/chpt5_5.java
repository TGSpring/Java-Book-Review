
/**
 * Tyler Spring
 * 3/30/2025
 * Chapter 5 Question 5
 * do-while Menu System
 * Write a do-while loop that presents the user with a menu:
 * 
 * 1. Say Hello
 * 2. Say Goodbye
 * 3. Exit
 * The loop continues until the user enters 3. Validate input and print the
 * appropriate message each time.
 */

import java.util.Scanner;

public class chpt5_5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int opt = 0;
        do {
            System.out.println("Select: ");
            System.out.print("1. Say Hello.\n" +
                    "2. Say Goodbye.\n" +
                    "3. Exit.\n");

            if (!input.hasNextInt()) {
                System.out.println("Please enter an integer.");
                input.next();
                continue;
            }
            opt = input.nextInt();
            if (opt == 1) {
                System.out.println("Hello");

            } else if (opt == 2) {
                System.out.println("Goodbye");

            } else if (opt == 3) {
                System.out.println("Exiting");
            } else {
                System.out.println("Invalid input.");
            }

        } while (opt != 3);
        input.close();
    }
}
/**
 * Space complexity is O(1). We do not create create anything except for fixed size variables/
 * Time complexity is O(n) for worst case. If the user makes the program continue to iterate with invalid input or chooses all options.
 */