
/**
 * Tyler Spring
 * 1/26/2025
 * Chapter 3, Exercise 7
 * 
 * 7. Interactive Programs with Scanner
 * Problem:
 * Create a basic calculator program that allows the user to:
 * 
 * Enter two numbers.
 * Select an operation (addition, subtraction, multiplication, or division)
 * using a menu.
 * The program should validate the user’s input for the operation and prevent
 * division by zero.
 * 
 */

import java.util.Scanner;

public class chp3_7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            // Input
            System.out.println("Enter the first number: ");
            double num1 = sc.nextDouble();
            System.out.println("Enter the second number: ");
            double num2 = sc.nextDouble();

            // Validation
            if (num1 <= 0 || num2 <= 0) {
                System.out.println("Please enter a valid positive number.");
                continue;
            }

            // Menu
            System.out.println("Select an operation: ");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");

            int oper = sc.nextInt();
            double res = 0;

            // Perform operation
            switch (oper) {
                case 1: // Addition
                    res = num1 + num2;
                    break;
                case 2: // Subtraction
                    res = num1 - num2;
                    break;
                case 3: // Multiplication
                    res = num1 * num2;
                    break;
                case 4: // Division
                    res = num1 / num2;
                    break;
                default:
                    System.out.println("Invalid operation. Please try again.");
                    sc.nextLine();
                    continue; // Skip to next iteration
            }
            System.out.println("Result: " + res);

            // Ask if the user wants to continue
            System.out.println("Do you want to continue? (yes/no)");
            String userChoice = sc.next();
            if (userChoice.equalsIgnoreCase("no")) {
                keepRunning = false;
            }
        }
        sc.close();
        System.out.println("Exiting...");
    }
}
