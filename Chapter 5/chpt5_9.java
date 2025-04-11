
/**
 * Tyler Spring
 * 4/11/2025
 * Chapter 5 Question 9
 * Fencepost Algorithm with User Input
 * Prompt the user to enter 5 words. Print them all on one line, separated by
 * spaces, using a fencepost algorithm to avoid extra space at the end.
 */

import java.util.Scanner;

public class chpt5_9 {
    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
        int count = 0;
        String output = "";

        while (count < 5) {
            if (count > 0) {
                output += " ";
            }
            System.out.println("Please enter 5 words: ");
            output += user.nextLine();
            count++;
        }
        System.out.print(output);

        user.close();
    }
}
