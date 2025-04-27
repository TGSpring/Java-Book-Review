/*
 * Tyler Spring
 * 4/27/2025
 * Chapter 6 Question 5
 * Write a method called flipLines that accepts a Scanner for an input file and writes to the console the 
 * same file's contents with each pair of lines reversed in order. If the file contains an odd number of lines,
 * leave the last line unmodified. For example
 * 
 * Twas brillig and the slithy toves
 * did gyre and gimble in the wabe.
 * All mimsey were the borogroves,
 * and the mome raths outgrabe.
 * 
 * your method should produce the following output
 * 
 * did gyre and gimble in the wabe.
 * Twas brillig and the slithy toves
 * and the mome raths outgrabe.
 * All mimsey were the borogroves,
 */

//Still sticking to only importing what I need.
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Chpt6_5 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(new File("reverse.txt"));
        System.out.println(flipLines(reader));
        // Always close your Scanners.
        reader.close();
    }

    public static String flipLines(Scanner reader) {
        // Result string to be returned. I got tired of naming them all "res".
        String flip = "";

        // Can be confusing at first, but here it first checks if their is a line.
        while (reader.hasNextLine()) {
            // Stores first line in tempFlip.
            String tempFlip = reader.nextLine();
            // Before doing anything else, checks for another line.
            if (reader.hasNextLine()) {
                // If there is another line, creates another temp string variable to have a
                // "pair".
                String revFlip = reader.nextLine();
                // I added the extra \n because it was painful to read in the console. It has no
                // effect on the actual requirements.
                // flip is concatenated with temp string pairs, but reversed, flipping the
                // lines.
                flip += "\n" + revFlip + "\n" + tempFlip;
                // If their is not another line, the file has an odd numbers lines.
                // Whatever the current line is, is added to flip unmoved.
            } else {
                flip += "\n" + tempFlip;
            }

        }
        // Concatenated String returned.
        return flip;
    }
}

/*
 * Space Complexity is constant O(1). No structures used, at most create only 2
 * temporary strings with only one scanner. Uses same
 * amount of space no matter the size of the file.
 * 
 * Time Complexity is linear O(n). N is the number of lines in the file. The
 * extra iteration for the odd line is just constant overhead and doesn't change
 * the overall complexity. Big - O notation ignores constant factors and lower
 * order terms.
 */