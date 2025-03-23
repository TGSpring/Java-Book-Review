
/**
 * Tyler Spring
 * 3/23/2025
 * 
 * Chapter 5 Question 3
 * Boolean Control Flow
 * You’re writing a login system. A boolean isAuthenticated starts as false. The
 * user has 3 attempts to enter the correct password "secret".
 * Use a while loop with a counter and boolean to simulate this system and print
 * "Access granted" or "Access denied" accordingly.1 directly).
 */

import java.util.Scanner;

public class Chpt5_3 {

    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
        int attempts = 0;
        String password = "secret";
        String input = "";
        System.out.println("Guess the password. You have 3 attempts.");
        while (attempts < 3) {

            if (user.hasNextLine()) {

                input = user.nextLine();

                if (input.equals(password)) {
                    System.out.println("Access Granted");
                    break;
                } else {
                    System.out.println("Access Denied. Try again.");
                    attempts++;
                }

            }
        }
        user.close();

    }
}
/**
 * Space complexity is O(1) given that their are no created structures and a few
 * variables.
 * 
 * Time complexity is O(1) given that their are at most 3 iterations in the
 * worst case. If you wanted to consider
 * that processing a single line of input with the .equals operation you could
 * argue O(n).
 */