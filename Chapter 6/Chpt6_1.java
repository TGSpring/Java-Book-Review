/*
 * Tyler Spring
 * 4/20/2025
 * Chapter 6 Question 1
 * 
 * Write  a method called boyGirl that accepts a Scanner that is reading its input
 * from a file containing a series of names followed by integers. The names alternate
 * between boys names and girls names. Your method should compute the absolute difference
 * between the sum of boys' integers and the sum of girls' integers. The input could end with
 * either a boy or girl; you many not assume that it contains an even number of names.
 * Then the method should produce the following console output, since the boys's sum
 * is 27 and the girls' is 32
 * 
 * 4 boys, 3 girls
 * Difference between boys' and girls' sums: 5 
 */

import java.util.Scanner;
import java.io.*;

public class Chpt6_1 {

    public static void main(String[] args) throws FileNotFoundException {

        // Scanner to be passed to method
        Scanner reader = new Scanner(new File("input.txt"));
        boyGirl(reader);
        reader.close();
    }

    public static void boyGirl(Scanner reader) {
        int counter = 1;
        int boySum = 0, girlSum = 0;
        int boyCount = 0, girlCount = 0;

        // Reads file line by line to prevent noSuchElement error.
        while (reader.hasNextLine()) {

            String line = reader.nextLine();

            // Creates mini scanner to extract tokens
            Scanner lineRead = new Scanner(line);
            if (lineRead.hasNext()) {
                String name = lineRead.next();
            }

            // Assumes file starts with boy, then alternates for count.
            if (lineRead.hasNextInt()) {
                int num = lineRead.nextInt();
                // counter starts at 1, assuming it starts with boy.
                if (counter % 2 == 0) {
                    girlSum += num;
                    girlCount++;
                } else {
                    boySum += num;
                    boyCount++;
                }
            }
            counter++;

        }
        int absDiff = Math.abs(boySum - girlSum);
        System.out.println(boyCount + " boys " + girlCount + " girls.");
        System.out.println("Difference between boys' and girls' sums:" + absDiff);

    }
}
/*
 * Time complexity is Linear O(n). N is the size of the file.
 * Space complexity is constant O(1). Only few variables created and no
 * structures other than a while loop.
 */