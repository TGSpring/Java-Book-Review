/**
 * Tyler Spring
 * 3/14/2025
 * Chapter 4 question 6
 * Reverse a String (Without StringBuilder)
 * 
 * Write a method reverseString(String str) that returns the string in reverse
 * order using a loop.
 * Example: reverseString("java") → "avaj"
 */
public class chpt4_6 {
    public static void main(String[] args) {
        System.out.println(reverseString("Testing TrackcartZ"));

    }

    /*
     * I wanted to use a new approach than just reversing a for loop.
     * By creating a char array and setting it to the same length as the input,
     * I was able to fill array by copying characters from the original starting
     * form the end.
     * Then construct a new string from the array and convert it to uppercase.
     * This avoids teh inefficiency of repeatedly concatenating immutable strings in
     * a loop.
     */
    public static String reverseString(String str) {
        int len = str.length();
        char[] rev = new char[len];
        for (int i = 0; i < len; i++) {
            rev[i] = str.charAt(len - 1 - i);
        }
        return new String(rev).toUpperCase();

    }
}
