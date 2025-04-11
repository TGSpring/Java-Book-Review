
/**
 * Tyler Spring
 * 4/11/2025
 * Chapter 5 Question 10
 * Boolean Flags with Loop Termination
 * You’re simulating a dice game. Generate random numbers (1–6) until you roll a
 * 6. Use a boolean flag rolledSix to control the loop, then print how many
 * rolls it took.
 */

import java.util.Random;

public class chpt5_10 {
    public static void main(String[] args) {
        boolean rolledSix = false;
        Random dice = new Random();
        int roll = dice.nextInt(6) + 1;
        int rolls = 0;

        while (!rolledSix) {
            if (roll == 6) {
                System.out.println("You rolled a " + roll + " Game over. You had " + rolls + " rolls.");
                rolledSix = true;
                break;
            }
            System.out.println("You rolled a " + roll);
            roll = dice.nextInt(6) + 1;
            rolls++;

        }
    }
}
/**
 * Space complexity is constant O(1), only a few variables are needed. The
 * amount of rolls does not contribute to the space.
 * 
 * Time complexity in the worst case is O(n), that is assuming either 6 is never
 * "rolled" or it occurs after n iterations.
 */