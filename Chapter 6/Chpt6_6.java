/*
 * Tyler Spring
 * 4/27/2025
 * Chapter 6 Question 6
 * 
 * Write a method called doubleSpace that accepts a Scanner for an input file and a PrintStream for an output file
 * as its parameters, writing into the output file a double-spaced version of the text in the input file.
 * You can achieve this task by inserting a blank line between each line of output.
 */

//Only importing what I need, first problem in the set to use PrintStream. Pretty handy so far.
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Chpt6_6 {
    public static void main(String[] args) throws FileNotFoundException {
        // Scanner and File used to find input file.
        Scanner input = new Scanner(new File("doubleSpace.txt"));
        // PrintStream used to create/write to output file.
        PrintStream output = new PrintStream(new File("OutputSpaced.txt"));
        doubleSpace(input, output);
        // Close your Scanners and PrintStreams.
        input.close();
        output.close();
    }

    public static void doubleSpace(Scanner input, PrintStream output) {
        // Checks if file has a line.
        while (input.hasNextLine()) {
            // Assigns current line to Temp String.
            String out = input.nextLine();
            // Writes current line to output file using println to automatically create new
            // line.
            output.println(out);
            // Uses conditional to check if there is another line to prevent errors and
            // properly add spaces.
            // I debated preventing a trailing space, but it did not ask for that and I was
            // not going to redo the entire structure to add a boolean flag so here we are.
            if (input.hasNextLine()) {
                output.println();
            }
        }
    }
}
/*
 * Space complexity is still constant O(1). Using fixed number of temporary
 * String variables with no additional data structures. Because we print
 * directly from the Scanner instead of storing all of them in memory, the extra
 * space remains constant regardless of the size of the file.
 * 
 * Time complexity is linear, O(n). N is the number of lines in the file.
 */