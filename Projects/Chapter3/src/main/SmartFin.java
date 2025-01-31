
/**
 * Tyler Spring
 * 1/31/2025
 * 
 * Smart Personal Finance Assistant
 * This is a project to reinforce the concepts of Chapter 3.
 * 
 * 1. Loan calculator with error handling
 * 2. Expense Tracker with Categorization & Sorting
 * 3. Savings goal estimator with adjustable contributions
 * 4. Budget Category Name analyzer
 * 5. Transaction date formatting & validation
 * 6. Encrypted password storage in a file.
 * 7. Report generation.
 */
import java.util.Scanner;

public class SmartFin {

    /**
     * validation for doubles
     * Handles input validation from user.
     * 
     */
    public double validation(Scanner sc, String prompt, boolean isInt) {
        double input = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.print(prompt);
                String inputStr = sc.next();

                if (isInt) {
                    if (!inputStr.matches("\\d+")) {
                        System.out.println("Please enter a valid positive integer.");
                    }
                    input = Integer.parseInt(inputStr);
                    if (input <= 0) {
                        System.out.println("Please enter a positive number.");
                        continue;
                    }
                } else {
                    if (!inputStr.matches("\\d+(\\.\\d+)?")) {
                        System.out.println("Please enter a valid number.");
                        continue;
                    }
                    input = Double.parseDouble(inputStr);
                    if (input <= 0) {
                        System.out.println("Please enter a positive number.");
                        continue;
                    }
                }
                isValid = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                sc.nextLine();
            }
        }
        return input;
    }

    /**
     * Loan Calculator.
     * prompts user for principal, rate, and term.
     * Returns double representing monthly payment.
     */
    public double loanCalc(double principal, double rate, int term) {

        rate = rate / 100.0 / 12;
        int months = term * 12;

        double monthlyPayment = (principal * rate * Math.pow(1 + rate, months))
                / (Math.pow(1 + rate, months) - 1);

        return monthlyPayment;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SmartFin sf = new SmartFin();

        System.out.println("Welcome to SmartFin!");
        System.out.println("1. Loan Calculator");
        System.out.println("2. Expense Tracker");
        System.out.println("3. Savings Goal Estimator");
        System.out.println("4. Budget Category Name Analyzer");
        System.out.println("5. Transaction Date Formatting & Validation");
        System.out.println("6. Encrypted Password Storage");
        System.out.println("7. Report Generation");
        System.out.println("8. Exit");

        int choice = 0;
        boolean isValidChoice = false;

        // Validate menu choice with switch statement.
        while (!isValidChoice) {
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                if (choice < 1 || choice > 8) {
                    System.out.println("Invalid choice. Please try again.");
                    continue;
                }
                isValidChoice = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                sc.nextLine();
            }
        }

        // Handle input.
        switch (choice) {
            case 1:
                // Loan calculator
                double principal = sf.validation(sc, "Enter the loan amount: ", false);
                double rate = sf.validation(sc, "Enter the interest rate: ", false);
                int term = (int) sf.validation(sc, "Enter the term in years: ", true);
                double monthlyPayment = sf.loanCalc(principal, rate, term);
                System.out.printf("Your monthly payment is: $%.2f\n", monthlyPayment);
                break;
            case 2:
                // Expense Tracker (placeholder for now)
                System.out.println("Expense Tracker feature coming soon...");
                break;

            case 3:
                // Savings Goal Estimator (placeholder for now)
                System.out.println("Savings Goal Estimator feature coming soon...");
                break;

            case 4:
                // Budget Category Analyzer (placeholder for now)
                System.out.println("Budget Category Analyzer feature coming soon...");
                break;

            case 5:
                // Transaction Date Formatter (placeholder for now)
                System.out.println("Transaction Date Formatter feature coming soon...");
                break;

            case 6:
                // Encrypted Password Storage (placeholder for now)
                System.out.println("Encrypted Password Storage feature coming soon...");
                break;

            case 7:
                // Report Generation (placeholder for now)
                System.out.println("Report Generation feature coming soon...");
                break;

            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }
}
