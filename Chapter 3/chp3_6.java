/**
 * Tyler Spring
 * 1/26/2025
 * Chapter 3, Exercise 6
 * 
 * 6. String Manipulation
 * Problem:
 * Write a method reverseWords that takes a sentence as a parameter and returns
 * the sentence with the order of the words reversed.
 * For example:
 * Input: "Java programming is fun"
 * Output: "fun is programming Java"
 * DO NOT USE STRINGBUILDER
 */
public class chp3_6 {
    public static String reverseString(String str) {
        String[] words = str.split(" ");
        String reversed = "";
        for (int i = words.length - 1; i >= 0; i--) {
            reversed += words[i] + " ";
        }
        return reversed.trim();
    }

    public static void main(String[] args) {
        String sentence = "Java programming is fun";
        System.out.println(reverseString(sentence));
    }
}
