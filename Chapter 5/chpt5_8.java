
/**
 * Tyler Spring
 * 4/11/2025
 * Chapter 5 Question 8
 * Assertion and Loop Integration
 * You have an int[] scores array. Write a loop to print all scores, but first
 * assert that the array’s length is at least 5. If the array is smaller, the
 * program should fail immediately.
 * 
 * 
 * NOTE: Run the command java -ea chpt5_8 to trigger assert.
 */
public class chpt5_8 {
    public static void main(String[] args) {
        int[] scores = { 100, 50, 25 };

        assert scores.length >= 5 : "Array must have at least 5 values.";

        for (int i = 0; i < scores.length; i++) {
            System.out.println(scores[i]);
        }
    }
}

/**
 * Space and time complex are both constant O(1). Only the array is created and
 * hardcoded.
 * If the array length was inputted or variable dependent, it would be O(n).
 * 
 */