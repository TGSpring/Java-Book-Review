/*
 * Tyler Spring
 * 4/30/2025
 * Chapter 6 Question 8
 * Write a method called printDuplicates that takes as a parameter a Scanner containing a series of lines. Your method should
 * examine each line looking for consecutive occurrences of the same token on the same line and print each duplicated token, 
 * along with the number of times that it appears consecutively. Nonrepeated tokens are not printed. You may ignore the case of repetition
 * across multiple lines (such as if a line ends with a given token and the next lines tarts with the same token). 
 * You may assume that each line of the file contains at least 1 token of input.
 * For example:
 * 
 * hello how how are you you you you
 * I I I am Jack's Jack's smirking smirking smirking smirking revenge
 * now wow wow yippee yippee yo yippee yippee yay yay yay
 * one fish two fish red fish blue fish
 * It's the Muppet Show, wakka wakka wakka 
 * 
 * Your method should output the following:
 * how * 2 you * 4
 * I * 3 Jack's * 2 smirking * 4
 * wow * 2 yippee * 2 yippee * 2 yay * 3 
 * 
 * wakka * 3
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Chpt6_8 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(new File("dups.txt"));
        System.out.println(printDuplicates(reader));
        reader.close();
    }

    public static String printDuplicates(Scanner reader) {
        String prevToken = ""; // Holds token from previous iteration.
        String output = ""; // output to be returned.
        int count = 1; // Tracks duplicates.
        // First Scanner reading first line of file.
        while (reader.hasNextLine()) {
            // First line put into String.
            String line = reader.nextLine();
            // Current line being Scanned.
            Scanner curLine = new Scanner(line);
            // Setting first word of current line to prevToken for comparisons.
            prevToken = curLine.next();
            while (curLine.hasNext()) {
                // Token is current String, curline
                String token = curLine.next();
                // Token is current word, prevToken has not been updated yet to current word,
                // comparison for duplicates.
                if (token.equals(prevToken)) {
                    // If duplicate found, increment count.
                    count++;
                } else {
                    if (count > 1) {
                        // Add to output string.
                        output += prevToken + " * " + count + " ";
                    }
                    // prevToken updated with current string, when the loop iterates again, token
                    // will be the new current string, prevToken will be previous one.
                    prevToken = token;
                    count = 1;

                }

            }
            // Checking for dupilcate groups and appending them to string.
            if (count > 1) {
                output += prevToken + " * " + count + " ";
            }
            // Dumb lesson learned here. DO NOT USE PRINTLN WHEN FORMATTING CONSOLE OUTPUT
            // WHEN YOU ARE APPENDING STRINGS
            output += "\n";
            count = 1;

        }
        return output;
    }
}

/*
 * Time Complexity is linear O(n), where n is the number of lines in the file
 * and number of tokens per line, in the worst case each line has consecutive
 * duplicates.
 * Space Complexity is constant O(1), using fixed variables, avoided using an
 * Array and used Scanners and temp variables.
 */