/**
 * Tyler Spring
 * 1/3/2025
 * computes the factorial of a number, n, where n is a positive integer.
 * Used recursion for fun.
 */
public class chpt2_2 {

    public static int calcFact(int n) 
    {
        if (n == 1 || n == 0) 
        {
            return 1;
        } else {
            return n * calcFact(n - 1);
        }
        }
    
    public static void main(String[] args) {
        int n = 5; //Example number for factorial
        int res = calcFact(n); //Call the factorial function
        System.out.println("factorial of " + n + " is " + res);
}
}