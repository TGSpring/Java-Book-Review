/*
 * Tyler Spring
 * 4/23/2025
 * Chapter 6 Question 2:
 * Write a method called evenNumbers that accepts a Scanner reading input from a file with a series of integers,
 * and report various statistics about the integers to the console. Report the total number of numbers, the sum of
 * the numbers, the count of even numbers and the percent of even numbers. 
 * For example if the input file contains:
 * 5, 7, 2, 8, 9, 10, 12, 98, 7, 14, 20, 22
 * 
 * Then the method should produce the following console output:
 * 12 numbers, sum = 214
 * 8 evens (66.67%)
 * 
 */

import java.util.Scanner;
import java.io.*;

public class Chpt6_2 {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner reader = new Scanner(new File("numbers.txt"));
        evenNumbers(reader);
        reader.close();
    }

    public static void evenNumbers(Scanner reader) {
        int counter = 0;
        int eCount = 0;
        int sum = 0;
        double ePercent = 0.0;
        reader.useDelimiter(",\\s*");
        while (reader.hasNextInt()) {
            int num = reader.nextInt();
            sum += num;

            if (num % 2 == 0) {
                eCount++;
            }

            counter++;

        }
        ePercent = ((double) eCount / counter) * 100;
        System.out.printf("%d numbers, sum = %d%n%d evens (%.2f%%)%n", counter, sum, eCount, ePercent);

    }
}
/*
 * Space complexity is constant O(1), we do not create any structures and use fixed variables.
 * Time complexity is linear O(n), where n is the size of the file being read.
 */