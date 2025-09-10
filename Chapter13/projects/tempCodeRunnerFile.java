/*
 * Tyler Spring
 * 9/10/2025
 * Chapter 13 Project 1
 * 
 * Write a program that reads a series of input lines and sorts them into
 * alphabetical order, ignoring the case
 * of words. The program should use the merge sort algorithm so that it
 * efficiently sorts even a large file.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Chpt13_Project1 {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner reader = new Scanner(new File("lines.txt"));
        ArrayList<String> lines = new ArrayList<>();

        while (reader.hasNextLine()) {
            lines.add(reader.nextLine());
        }
        reader.close();

        String[] sortArr = lines.toArray(new String[0]);

        // Testing output.
        for (String s : sortArr) {
            System.out.println(s);
        }
    }
}
/*
 * NOTES
 * this will read in by a file.
 * an arraylist would be best given the dynamic sizing
 * if i did an array it would need to be predetermined for sizing
 * merge sort halves and sorts until it reaches a point it can not halve
 * anymore. then works back up.
 * since we are ignoring the casing it can merge step because that will be where
 * the comparisons are. we also
 * do not need to keep calling ignores case every recursive call.
 * we can just use compare ignore.
 * one per line for readability.
 * 
 * QUICK CHECK
 * lines.toArray(new String[0]) internally converts to array.
 * If you call lines.toArray() with no arguments, it returns an Object[], which
 * isn't type safe for Strings.
 * Passing new String[0] gives Java a template for the type, so it can safely
 * return a String[].
 * Java will size the new array automatically, so you don't have to loop or
 * guess the size.
 */