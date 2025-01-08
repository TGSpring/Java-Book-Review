/**
 * Tyler Spring
 * 1/8/2025
 * 2.7
 * generates a specified number of random integers within a given range.
 */

import java.util.Random;
import java.util.Scanner;

public class chpt2_7 {

    public static void randInt(int n, int min, int max) {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            System.out.println( rand.nextInt( (max - min) + 1) + min);
        }
    }
 public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter the number of random integers to generate:");
    int n = sc.nextInt();

    System.out.println("Enter a minimum value: ");
    int min = sc.nextInt();

    System.out.println("Enter a maximum value: ");
    int max = sc.nextInt();

    randInt(n, min, max);

    sc.close();
 }   
}
