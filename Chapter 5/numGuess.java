
/**
 * Tyler Spring
 * 3/19/2025A
 * 
 * Case Study number guessing game
 * 
 * Simple case study to help reinforce concepts covered in chapter 5
 * 
 * 
 */

import java.util.Random;
import java.util.Scanner;

public class numGuess {
    public static void main(String[] args) {

        Random rand = new Random();
        int target = rand.nextInt(100) + 1;
        Scanner sc = new Scanner(System.in);
        int guess = -1; // Starting value.
        int tries = 0;

        while (guess != target) {
            System.out.println("Guess the number (1 - 100): ");
            guess = sc.nextInt();
            tries++;

            if (guess < 1 || guess > 100) {
                System.out.println("Please guess a number between 1 and 100.");
            } else if (guess < target) {
                System.out.println("Too low! Try again!");
            } else if (guess > target) {
                System.out.println("Too high! Guess again!");
            } else {
                System.out.println("Correct!!! It took you " + tries + " tries.");
            }
        }
    }
}
