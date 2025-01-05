/**
 * Tyler Spring
 * 1/5/2025
 * 2.5
 * Fib Sequence up to n
 */
public class chpt2_5 {

    public static int fib(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public static void main(String[] args) {
        int n = 10;
        for (int i = 0; i <= n; i++) {
            System.out.println("Fib number at " + n + " is " + fib(i));
        }

    }
}
