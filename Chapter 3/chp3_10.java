/**
 * Tyler Spring
 * 1/29/2025
 * 
 * Chapter 3 Exercise 10
 * 
 * 10. Combining Strings and Parameters
 * Problem:
 * Write a method encryptString that takes two parameters:
 * 
 * A string to encrypt.
 * An integer shift value for a Caesar cipher.
 * The method should return a new string where each letter in the original
 * string is shifted by the given value (e.g., 'A' becomes 'C' for a shift of
 * 2). Keep uppercase and lowercase letters intact, and wrap around the alphabet
 * (e.g., 'Z' becomes 'B' for a shift of 2). Non-alphabetic characters should
 * remain unchanged.
 */
public class chp3_10 {

    public static String encryptString(String str, int shift) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char encryptedChar = (char) (base + ((c - base + shift) % 26 + 26) % 26);
                res.append(encryptedChar);
            } else {
                res.append(c);

            }

        }
        return res.toString();
    }

    public static void main(String[] args) {
        String str = "is anyone actually looking at my code?";
        int shift = 2;

        System.out.println("Original: " + str);
        System.out.println(encryptString(str, shift) + " (Shifted by " + shift + ")");
    }
}
