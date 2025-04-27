/*
 * Tyler Spring
 * 4/25/2025
 * Chapter 6 Question 4
 * Write a method called readEntireFile that accepts a Scanner representing an input file as its parameter, then reads
 * that entire file and returns its entire text contents as a String.
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Chpt6_4 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(new File("stringer.txt"));
        // Method returns String, easier to do call in print method.
        System.out.println(readEntireFile(reader));
        // Remember to close your Scanners.
        reader.close();
    }

    public static String readEntireFile(Scanner reader) {
        // Used concatenation for result string, yes StringBuilder is better but that is
        // not the goal here.
        String res = "";

        // Reads file until nextLine is false.
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            // nextLine does NOT auto include a new line char, you need to add it yourself
            // to maintain formatting. This format adds a new line at the end I just did not
            // care.
            res = res + line + "\n";

        }
        return res;
    }
}
/*
 * Space complexity is constant O(1), does not use structures, uses fixed
 * variables. Keep in mind using String concatenating can change this by
 * magnitude, here I just used a small text file.
 * Time complexity is linear O(n), n is the size of the file, we are just
 * reading it, no conditions.
 */