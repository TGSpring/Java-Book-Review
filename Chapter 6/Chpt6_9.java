/*
 * Tyler Spring
 * 4/30/2025
 * Chapter 6 Question 9
 * Write a method called plusScores that accepts a Scanner representing an input file containing a series of lines 
 * that represent student records. Each student record takes up two lines of input. The first line has the student's name
 * and the second line has a series of plus and minus characters. Below is a sample input.
 * Kane, Erica
 * --+-+
 * Chandler, Adam
 * ++-+
 * Martin, Jake
 * +++++++
 * 
 * For each student you should produce a line of output with the student's name followed by a colon followed by the percent 
 * of plus characters. For the input above, your method should produce the following.
 * Kane, Erica: 40.0% plus
 * Chandler, Adam: 75.0% plus
 * Martin, Jake: 100.0% plus
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Chpt6_9 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(new File("plusScore.txt"));
        System.out.println(plusScores(reader));
        reader.close();

    }

    public static String plusScores(Scanner reader) {
        // String to return output.
        String output = "";
        String names = ""; // Storing the names.
        String symbols = ""; // Storing symbols.
        int plus = 0; // Tracks number of + symbols.
        double percentage = 0.0; // Displaying scores as double %.

        // Reading first line for names.
        while (reader.hasNextLine()) {
            // Reads line with names.
            names = reader.nextLine();
            // Resetting plus values after reading name to correctly calculate.
            plus = 0;
            // Reads line with symbols. Miles easier than using nested while loop.
            symbols = reader.nextLine() + "\n";

            for (int i = 0; i < symbols.length(); i++) {
                char c = symbols.charAt(i);
                if (c == '+') {
                    // Increments when + is found.
                    plus++;

                }

            }

            percentage = ((double) plus / symbols.trim().length()) * 100;
            output += names + ": " + percentage + "% plus\n";

        }
        return output;
    }
}

/*
 * Time complexity is linear O(n), n is the number of lines in the file. Each
 * line involves scanning over its characters, worst case a lot of + symbols for
 * comparisons with the conditional .charAt().
 * Space complexity is constant O(1), fixed variables, only 1 while loop with
 * one conditional.
 */