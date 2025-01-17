/*
 * Tyler Spring
 * 1/15/2025
 * Number Cruncher 
 * This is a project to help solidify my understanding of the concepts 
 * covered in chapter 1 and 2 of the book "Java: A Beginner's Guide".
 */


import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class numberCruncher {
    private static final Scanner user = new Scanner(System.in);
    private static final Map<Integer, Runnable> menuActions = new HashMap<>();

    public static void main(String[] args) {
        setupMenu();
        boolean run = true;

        while (run) {
            displayMenu();
            int choice = getUserChoice();

            if (choice == 8) {
                System.out.println("Exiting Number Cruncher.");
                run = false;
            } else {
                menuActions.getOrDefault(choice, () -> System.out.println("Invalid choice. Please try again.")).run();
            }
        }
        user.close();
    }

    private static void setupMenu() {
        menuActions.put(1, numberCruncher::powerCalc);
        menuActions.put(2, numberCruncher::sumEvenOdd);
        menuActions.put(3, numberCruncher::randNum);
        menuActions.put(4, numberCruncher::checkPrime);
        menuActions.put(5, numberCruncher::reverseNum);
        menuActions.put(6, numberCruncher::printPattern);
        menuActions.put(7, numberCruncher::approximatePi);
    }

    private static void displayMenu() {
        System.out.println("\n=== Number Cruncher Menu ===");
        System.out.println("1. Calculate power of a number");
        System.out.println("2. Sum of even and odd numbers up to a limit");
        System.out.println("3. Generate a random number in a range");
        System.out.println("4. Check if a number is prime");
        System.out.println("5. Reverse a number");
        System.out.println("6. Print a triangle or rectangle pattern");
        System.out.println("7. Approximate the value of Pi");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }
    private static int getUserChoice() {
        String input = user.nextLine(); // Use nextLine to capture entire line of input

        try {
            // If the input is numeric, parse it to an integer
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // If it's not a number, return an invalid choice
            return -1;
        }
    }

    //Menu Action 
    private static void powerCalc() {
        System.out.print("Enter base: ");
        double base = Double.parseDouble(user.nextLine());
        System.out.print("Enter exponent: ");
        double exponent = Double.parseDouble(user.nextLine());
        toolbox.powerCalc(base, exponent);
    }

    private static void sumEvenOdd() {
        System.out.print("Enter the limit: ");
        int limit = Integer.parseInt(user.nextLine());
        toolbox.sumEvenOdd(limit);
    }

    private static void randNum() {
        System.out.print("Enter minimum value: ");
        int min = Integer.parseInt(user.nextLine());
        System.out.print("Enter max value: ");
        int max = Integer.parseInt(user.nextLine());
        toolbox.randNum(min, max);
    }

    private static void checkPrime() {
        System.out.print("Enter a number to check: ");
        int num = Integer.parseInt(user.nextLine());
        if(toolbox.isPrime(num)) {
            System.out.println(num + " is a prime number");
        } else {
            System.out.println(num + " is not a prime number");
        }
    }

    private static void reverseNum() {
        System.out.print("Enter a number to reverse: ");
        int number = Integer.parseInt(user.nextLine());
        toolbox.revNum(number);
    }

    private static void printPattern() {
        System.out.print("Enter number of rows: ");
        int rows = Integer.parseInt(user.nextLine());
        toolbox.printRow(rows);
    }

    private static void approximatePi() {
        System.out.print("Enter the number of terms for approximation: ");
        int terms = Integer.parseInt(user.nextLine());
        toolbox.ApxPi(terms);
    }
}