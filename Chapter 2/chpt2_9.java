
/**
 * Tyler Spring
 * 1/8/2025
 * 2.9
 * write a program that checks whether a number is prime.
 */

import java.util.Scanner;

public class chpt2_9 {

    public static void isPrime(int pNum) {
        if (pNum <= 1) {
            System.out.println("Not prime");
            return;
        }

        for (int i = 2; i * i < pNum; i++) {
            if (pNum % i == 0) {
                System.out.println("Not prime");
                return;
            }
        }
        System.out.println("Prime");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a number to check if it is prime:");
        int pNum = sc.nextInt();

        isPrime(pNum);

    }
}
