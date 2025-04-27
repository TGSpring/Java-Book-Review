/*
 * Tyler Spring
 * 4/27/2025
 * Chapter 6 Question 7
 * 
 * Write a method called stripComments that accepts a Scanner representing an input file containing
 * a Java program as its parameter, reads that file, and prints the file's text with all comments removed.
 * A comments is any text on a line from // to the end of the line, and any text between the characters that made this literal comment.
 * See the file program.java. 
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Chpt6_7 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("program.java"));
        System.out.println(stripComments(input));
        input.close();
    }

    public static String stripComments(Scanner input) {
        // Reads each line from a Scanner and removes both Single line and block
        // comments.
        String output = "";
        // Boolean flag used for when we're inside a block comment.
        boolean inBlock = false;

        // Reading file line by line.
        while (input.hasNextLine()) {
            String remove = input.nextLine();
            // If in block comment, checks current line for having */
            if (inBlock) {
                int end = remove.indexOf("*/");
                // If found, remove everything up to and including it and reset the boolean
                // flag.
                if (end != -1) {

                    remove = remove.substring(end + 2);
                    inBlock = false;
                } else {
                    // Skip if overwise.
                    continue;
                }
            }
            // Repeatedly checks for comment markers within the current line.
            while (true) {
                // Used to locate a block comment start and a line comment.
                int blockStart = remove.indexOf("/*");
                // Find start of single line comment.
                int lineComment = remove.indexOf("//");
                // If a block comment start is found before any line comment, we extract what
                // appears before the comment, this is assigned to beforeBlock, then look for
                // the block comment's end marker.
                if (blockStart != -1 && (lineComment == -1 || blockStart < lineComment)) {
                    // Extract text before the block comment.
                    String beforeBlock = remove.substring(0, blockStart);
                    int blockEnd = remove.indexOf("*/", blockStart + 2);
                    // If not found we remove from the start of the comment onward and set the flag
                    // so that subsequent lines will be processed as part of the block comment.
                    if (blockEnd != -1) {
                        remove = beforeBlock + remove.substring(blockEnd + 2);

                        continue;
                    } else {

                        remove = beforeBlock;
                        inBlock = true;
                        break;
                    }
                } else if (lineComment != -1) {
                    // Otherwise if a line comment is found, remove everything from that marker
                    // forward.
                    remove = remove.substring(0, lineComment);
                    break;
                } else {

                    break;
                }
            }
            // Append string.
            output += remove + "\n";
        }

        return output;
    }
}
/*
 * Space complexity is constant O(1). No structures, uses temp variables, does
 * not store in actual memory.
 * Time complexity is linear O(n). N is the number of lines in the file.
 */