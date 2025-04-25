/*
 * Tyler Spring
 * 4/25/2025
 * Chapter 6 Question 3
 * Writer a method called negativeSum that accepts a Scanner reading input from a file containing
 * a series of integers, and print a message to the console indicating whether the sum starting from the first number
 * is ever negative. You should also return true if a negative sum can be reached and false if not.
 * For example, suppose the file contains:
 * 38 4 19 -27 -15 -3 4 19 38
 * Your method would consider the sum of just one number 38, the first two numbers 38 + 4, the first tree number 38 + 4 + 19
 * and so on. None of these sums is negative, so the method would return false and this:
 * no negative sum
 * If the file contains the following numbers:
 * 14 7 -10 9 -18 -10 17 42 98
 * The method finds that a negative sum of -8 is reached after adding the first six numbers. It should output true and this:
 * sum of - 8 after 6 steps.
 */

//Trying to avoid importing entire libraries, old/expensive habit
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Chpt6_3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(new File("input2.txt"));
        negativeSum(reader);
        reader.close();
    }

    public static boolean negativeSum(Scanner reader) {
        // counter for steps, sum for negative sum if met.
        int counter = 0;
        int sum = 0;
        // Decided to go with multi line file, so needed second scanner, do not do this
        // if you just want to focus on anything other than reading more lines.
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            // Mini scanner to read int tokens.
            Scanner lineNum = new Scanner(line);
            if (lineNum.hasNextInt()) {
                int num = lineNum.nextInt();
                sum += num;
                counter++;
                // While I do NOT agree that one negative value is the same as a negative sum,
                // the wording in this problem states it wants this.
                if (sum < 0) {
                    System.out.println("Sum of " + sum + " after " + counter + " steps.");
                    return true;

                }
            }
        }
        System.out.println("No negative sum.");
        return false;
    }
}
/*
 * Space complexity is constant O(1), fixed variables, no structures.
 * Time complexity is linear O(n), n is the file size in worst case with no
 * negative sum found.
 */