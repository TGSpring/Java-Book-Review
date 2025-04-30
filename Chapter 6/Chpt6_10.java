/*
 * Tyler Spring
 * 4/30/2025
 * Chapter 6 Question 10
 * Write a method called leetSpeak that accepts two parameters: a Scanner representing an input file, and a PrintStream representing an output file. Convert
 * the input file's text to "leet speak", where various letters are replaced by other letters/numbers, and output the new text to the given output file. Replace "o"
 * with "0", "1" lowercase "L" with "l" the number one, "e" with "3", "a" with "4", "t" with "7", and an "s" at the end of a word with "z". Preserve 
 * the original line breaks from the input. Also wrap each word of input in parentheses.
 * For example:
 * four score and
 * seven years ago our
 * fathers brought forth on this continent
 * a new nation
 * 
 * For the above input your method should produce the following in the output file.
 * 
 * (f0ur) (sc0r3) (4nd)
 * (s3v3n) (y34rZ) (4g0) (0ur)
 * (f47h3rZ) (br0ugh7) (f0r7h) (0n) (7hiZ) (c0n7in3n7)
 * (4) (n3w) (n47i0n)
 */

import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class Chpt6_10 {
    public static void main(String[] args) throws FileNotFoundException {
        // Scanner for input file to be read.
        Scanner input = new Scanner(new File("leetSpeak.txt"));
        // PrintStream to create and write to output file.
        PrintStream output = new PrintStream(new File("leetSpoke.txt"));
        leetSpeak(input, output);
        input.close();
        output.close();
    }

    public static void leetSpeak(Scanner input, PrintStream output) {
        // Process the file line by line.
        while (input.hasNextLine()) {
            // Reads current line.
            String line = input.nextLine();
            // Create mini Scanner to read tokens for current line.
            Scanner lineScanner = new Scanner(line);
            // Temp string for alterations.
            String newerLeeted = "";

            // Processing loop.
            while (lineScanner.hasNext()) {
                String token = lineScanner.next();

                String leeted = token.replace("o", "0")
                        .replace("e", "3")
                        .replace("a", "4")
                        .replace("t", "7");

                // If letter ends with s.
                if (leeted.charAt(leeted.length() - 1) == 's') {
                    leeted = leeted.substring(0, leeted.length() - 1) + 'z';
                }

                // Append to newerLeeted, added parentheses.
                newerLeeted += "(" + leeted + ") ";
            }
            // Print to output file, move to next line.
            output.println(newerLeeted);
            lineScanner.close();
        }

    }
}
/*
 * Time complexity is linear O(n), n is number of lines and words per line.
 * Worst case is every word in the file ends with 's'
 * Space complexity is constant O(1), fixed variables, two scanners, two while
 * loops with one conditional.
 */