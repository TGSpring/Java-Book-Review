
/**
 * Tyler Spring
 * 4/4/2025
 * Chapter 5 Question 6
 * Complex Boolean Logic
 * Write a method that takes two integers and prints "Valid pair" if:
 * 
 * Both numbers are positive and divisible by 3, OR
 * 
 * One of the numbers is exactly 100.
 * 
 * Use boolean expressions effectively.
 */
public class chpt5_6 {
    public static void main(String[] args) {
        System.out.println(validPair(1, 10));
        System.out.println(validPair(3, 6));
        System.out.println(validPair(4, 9));
        System.out.println(validPair(100, 47));
        System.out.println(validPair(33, 0));
        System.out.println(validPair(-3, -9));
    }

    public static String validPair(int x, int y) {
        if ((x % 3 == 0) && (y % 3 == 0) && (x > -1 && y > -1) || (x == 100 || y == 100)) {
            return "Valid pair";
        }
        return "Not valid";
    }
}
/**
 * Space && Time Complexities here are constant. We do not create any structures
 * and the variables are hardcoded in.
 */