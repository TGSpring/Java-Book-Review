/**
 * Tyler Spring
 * 1/10/2025
 * 2.11
 * Approximating Pi.
 */
import java.util.Scanner;

public class chpt2_11 {

    public static void ApxPi(int input) 
    {
        double sum = 0;
        for (int i = 0; i <= input; i++) 
        {
            int denominator = 2 * i + 1; //1, 3, 5, 7,..

            if (i % 2 == 0)
            {
                sum += 1.0 / denominator; //Add term.
            } else 
            {
                sum -= 1.0 / denominator; //Subtract term.
            }
        }
        double pi = 4 * sum;
        System.out.printf("Pi is approximately: %.10f\n", pi);

    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in); 
        int input = 0;

         System.out.printf("Enter a number to approximate Pi to: \n");
         input = sc.nextInt();

         ApxPi(input);
    }
}
