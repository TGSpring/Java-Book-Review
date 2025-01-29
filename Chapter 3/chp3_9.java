
/**
 * Tyler Spring
 * 1/29/2025
 * 
 * Chapter 3 Exercise 9
 * 
 * 9. Validating Input with Scanner
 * Problem:
 * Write a program that prompts the user to input a positive integer. If the
 * user enters anything invalid (like a negative number, zero, or non-integer),
 * prompt them again until valid input is received. Return the valid input and
 * display it.
 * 
 */
import java.util.Scanner;

public class chp3_9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a positive integer: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a positive integer.");
                sc.next();
                continue;
            }

            int num = sc.nextInt();

            if (num > 0) {
                System.out.println("Valid input: " + num);
                break;
            } else {
                System.out.println("Please enter a number greater than 0.");
            }

        }
    }
}
