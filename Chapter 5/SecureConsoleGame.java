
/**
 * Tyler Spring
 * 6/28/2026
 * SecureConsoleGame
 * 
 * PROJECT: Secure Console Game System (Chapter 5)
 * 🎯 Goal
 * 
 * Build a console-based Java program that acts as a mini game hub. The program
 * must heavily use loops, input validation, boolean logic, and fencepost
 * formatting.
 * 
 * 🟦 MAIN MENU (REQUIRED)
 * 
 * Use a do-while loop to display a menu repeatedly:
 * 
 * Options:
 * 
 * Number Guessing Game
 * Dice Roll Challenge
 * Password Lock System
 * Statistics Tool
 * Exit
 * Requirements:
 * Must use do-while
 * Must validate user input using hasNextInt()
 * Must reject invalid menu selections
 * Must loop until user selects Exit
 * 🟨 NUMBER GUESSING GAME
 * Random number 1–100
 * Loop until correct guess
 * Track attempts
 * Validate input:
 * must be integer
 * must be within range
 * Must use a loop condition or boolean flag
 * 🟩 DICE ROLL CHALLENGE
 * Roll a die until you get a 6
 * Track number of rolls
 * Print every roll
 * Must use Random.nextInt(6) + 1
 * Must use a boolean-controlled loop
 * Add extension: stop if rolls exceed 15
 * 🟥 PASSWORD LOCK SYSTEM
 * Fixed password
 * 3 attempts max
 * Must use:
 * counter
 * .equals() for string comparison
 * Must handle:
 * empty input
 * incorrect attempts
 * 🟪 STATISTICS TOOL (FENCEPOST REQUIRED)
 * 
 * User enters N integers, then program outputs:
 * 
 * sum
 * average
 * min
 * max
 * formatted list (NO trailing comma)
 * Requirements:
 * Must use fencepost algorithm somewhere
 * Must validate input using hasNextInt()
 * Must use loop for accumulation
 * ⚙️ REQUIRED CONCEPTS (CHECKLIST)
 * 
 * Your solution must include:
 * 
 * Loops
 * while loop (unknown iteration count)
 * do-while loop (menu system)
 * at least one loop using break
 * Input Validation
 * hasNextInt()
 * buffer clearing (next() or nextLine())
 * retry loop for invalid input
 * Boolean Logic
 * at least one boolean flag loop
 * at least one && or || compound condition
 * Fencepost Algorithm
 * at least one output with NO trailing separator
 * Assertions
 * at least one assert used correctly for a programmer error (NOT user input
 * validation)
 */
import java.util.Random;
import java.util.Scanner;

public class SecureConsoleGame {
    // Continuously prompts user until they exit.
    // Uses input validation to ensure safe menu selection.
    public static void main(String[] args) {

        int choice;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Console Game\nSelect which game to play, or exit.");
            System.out.println("1. guessing game\n2. dice game\n3. password strength\n4. stats tool\n5. exit.");

            choice = getInt(sc, 1, 5);

            switch (choice) {
                case 1:
                    // guessing game.
                    numberGuessingGame(sc);
                    break;

                case 2:
                    // dice game.
                    diceGame(sc);
                    break;
                case 3:
                    // password strength game.
                    passwordCheck(sc);
                    break;
                case 4:
                    // stats tool.
                    statsTool(sc);
                    break;
                default:
                    // invalid input.

            }
        } while (choice != 5);
        sc.close();
    }

    // Method for number guessing game.
    /**
     * - Generates random number between 1 and max.
     * - Uses loop until correct guess is entered.
     * - Tracks number of attempts.
     * - Validates input using getInt().
     * 
     */
    private static void numberGuessingGame(Scanner sc) {
        int attempts = 0;
        int max = 1000;
        Random random = new Random();
        int rand = random.nextInt(max) + 1;

        System.out.println("Number Guessing Game.");

        while (true) {
            System.out.println("Guess a number between 1 and " + max);

            int input = getInt(sc, 1, max);
            attempts++;
            if (input < rand) {
                System.out.println("Too low guess higher.");

            } else if (input > rand) {
                System.out.println("Too high guess lower.");

            } else {
                System.out.println("Match! Answer is " + rand + ". It took " + attempts + " attempts.\n");
                break;
            }

        }

    }

    // Method for Dice game.
    /**
     * - Simulates guessing a dice roll.
     * - Limited to 3 attempts.
     * - Uses boolean flag to track win state.
     * - Demonstrates loop termination with break and condition.
     * 
     */
    private static void diceGame(Scanner sc) {
        int attempts = 0;
        Random rand = new Random();
        int roll = rand.nextInt(6) + 1;

        boolean won = false;

        System.out.println("Dice Game. Guess the number in 3 attempts!");

        while (attempts < 3 && !won) {

            int guess = getInt(sc, 1, 6);

            if (guess == roll) {
                System.out.println("Correct! It was " + roll);
                won = true;
            } else {
                System.out.println("Wrong. Guess again.");
                attempts++;
            }
        }

        if (!won) {
            System.out.println("Out of attempts! The correct number was " + roll);
        }
    }

    // Method for PasswordCheck.
    /**
     * - Scans password character-by-character.
     * - Flags presence of uppercase, lowercase, digits, and symbols.
     * - Computes strength score based on criteria.
     */
    private static void passwordCheck(Scanner sc) {
        boolean upper = false;
        boolean lower = false;
        boolean digit = false;
        boolean special = false;

        System.out.println("Enter password to test its strength");
        String password = sc.nextLine();

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (c >= 'A' && c <= 'Z') {
                upper = true;
            } else if (c >= 'a' && c <= 'z') {
                lower = true;
            } else if (c >= '0' && c <= '9') {
                digit = true;
            } else {
                special = true;
            }
        }

        int score = 0;

        if (upper)
            score++;
        if (lower)
            score++;
        if (digit)
            score++;
        if (special)
            score++;
        if (password.length() >= 8)
            score++;

        if (score <= 2) {
            System.out.println("Weak password");
        } else if (score <= 4) {
            System.out.println("Medium password");
        } else {
            System.out.println("Strong password");
        }

    }

    // Method for stats tool.
    /**
     * - Collects user-defined number of integers.
     * - Calculates sum, min, max, and average.
     * - Uses fencepost algorithm for formatted output.
     * - Demonstrates array processing and accumulation.
     */
    private static void statsTool(Scanner sc) {

        int sum = 0;
        int min = 0;
        int max = 0;

        System.out.println("How many numbers would you like to enter?");
        int input = getInt(sc, 1, 10);
        assert input > 0 : "Input must be positive.";
        int[] arr = new int[input];

        for (int i = 0; i < arr.length; i++) {
            System.out.println("Enter the amount of integers declared: ");
            arr[i] = getInt(sc, Integer.MIN_VALUE, Integer.MAX_VALUE);

            sum = sum + arr[i];

            if (i == 0) {
                min = arr[i];
                max = arr[i];
            } else {
                if (arr[i] < min) {
                    min = arr[i];
                }
                if (arr[i] > max) {
                    max = arr[i];
                }
            }

        }
        double average = (double) sum / arr.length;

        System.out.println("Sum: " + sum);
        System.out.println("Min:" + min);
        System.out.println("Max: " + max);
        System.out.println("Average: " + average);

        System.out.println("\nElements printed with fencePost algorithm.\n");
        System.out.print(arr[0]);

        for (int j = 1; j < arr.length; j++) {
            System.out.print(", " + arr[j]);
        }
        System.out.println();

    }

    // Helper method for validating input.
    /**
     * - Ensures input is an integer.
     * - Enforces min/max range constraints.
     * - Prevents invalid input from crashing program.
     */
    private static int getInt(Scanner sc, int min, int max) {
        while (true) {
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Enter an integer.");
                sc.next();
                continue;
            }

            int input = sc.nextInt();

            if (input < min || input > max) {
                System.out.println("Out of range. Enter a value between " + min + " and " + max);
                continue;
            }
            return input;//
        }
    }

}
