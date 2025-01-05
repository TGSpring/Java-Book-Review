/**
 * Tyler Spring
 * 1/5/2025
 * 2.4
 * reversing numbers
 */
public class chpt2_4 {
    public static int revNum(int n) {
        int rev = 0;
        while (n != 0) {
            rev = rev * 10 + n % 10;
            n = n / 10;
        }
        return rev;
    }    

    public static void main(String[] args) {
        int n = 12345;
        int res = revNum(n);

        System.out.println("Reversed number of " + n + " is " + res);
    }
}
/* 
 * Explanation:
 * We initialize a rev variable to 0, which will hold the reversed number.
 * In the while loop, we use n % 10 to get the last digit of the number.
 * Multiply rev by the value of 10 to shift its digits and add the extracted digit.
 * Use n = n / 10 to remove the last digit of the original number.
 * Repeat the process until n becomes 0.
 */