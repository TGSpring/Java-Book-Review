/**
 * Tyler Spring
 * 1/8/2025
 * 2.8
 * Write a program that asks the user to input a series of integers.
 * The program should count and print how many of the entered values match a specific target value. 
 * The program ends when the user enters a sentinel value (e.g., -1).
 */

import java.util.Scanner;

public class chpt2_8 {

    public static void main(String[] args) {
    
    int count = 0;
    int input = 0;

    Scanner sc = new Scanner(System.in);
    

    //User inputting target value.
    System.out.println("Enter the target value: ");
    int target = sc.nextInt();
   
    //Prompting user to enter integers.
    System.out.println("Enter a series of integers. Enter -1 to quit: ");

    
    //While loop to compare input to target value.
    while (input != -1) {
        input = sc.nextInt();
        if (target == input) 
        {
            count++;

        }

    }
    System.out.println("The number of times the target value was entered is: " + count);

    sc.close();

    }
}
