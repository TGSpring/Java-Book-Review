/**
 * Tyler Spring
 * 3/21/2025
 * Chapter 5 Question 2
 * Print the numbers 10 to 1, separated by " | " using no trailing separator.
 * Use a fencepost algorithm and no reverse loop (i.e., don’t loop from 10 down
 * to 1 directly).
 */
public class chpt5_2 {
    public static void main(String[] args) {
        
        System.out.print("10");
        for (int i = 1; i <  10; i++){
            System.out.print(" | ");
            int inx = 10 - i;
            System.out.print(inx + ""); 
        }
    }
}
/**
 * Space Complexity and Time Complexity are both O(1), constant.
 * We do not create any additional data structures and the output is always the same.
 * Exactly 10 iterations over a fixed input size.
 */