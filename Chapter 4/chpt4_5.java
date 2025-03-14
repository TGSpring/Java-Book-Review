/**
 * Tyler Spring
 * 3/14/2025
 * Chapter 4 question 5
 * Count Vowels
 * 
 * Write a method countVowels(String str) that returns the number of vowels (a,
 * e, i, o, u) in a string.
 * Example: countVowels("hello") → 2
 */
public class chpt4_5 {
    public static void main(String[] args) {
        System.out.println(countVowels("Hello"));
    }

    public static int countVowels(String str) {
        int vowels = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = Character.toLowerCase(str.charAt(i));

            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowels++;
            }

        }
        return vowels;
    }
}
