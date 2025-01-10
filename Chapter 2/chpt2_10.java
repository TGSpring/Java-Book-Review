
/**
 * Tyler Spring
 * 1/10/2025
 * 2.10
 * summing even and odd numbers.
 */
import java.util.Scanner;

public class chpt2_10 
{

    public static void numSum(int input) 
    {
        int evenSum = 0;
        int oddSum = 0;
        for (int i = 0; i <= input; i++) 
        {
            if (i % 2 == 0) 
            {
                evenSum += i;
            } else 
            {
               oddSum +=i;
            }
        }
        System.out.printf("Sum of even numbers: " + evenSum + " \n"
                        + "Sum of odd numbers: " + oddSum + "\n");

    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        
        do 
        {
            System.out.printf("Enter a number to sum even and odds numbers up to that number: \n");
            input = sc.nextInt();
            if (input != -1)
            {
            numSum(input);
            }
        }
        while (input != -1);
                
        sc.close();
    }
}
