
/*
 *Tyler Spring
 6/29/2025
 Chapter 10 Question 1
 Write a method called averageVowels that takes an ArrayList of strings as a parameter and returns the average number of vowel characters (a, e, i, o, u) in all Strings in the
 list. If your method is passed an empty ArrayList, it should return 0.0.  
 */

import java.util.ArrayList;

public class Chpt10_Q1 {
    // Q1: Why did you choose to use an ArrayList<String> instead of a regular array
    // for the input?
    // Aside from focusing on ArrayLists, its dynamic, if I wanted to test with
    // different sizes, I can just write it in different sizes.
    public static void main(String[] args) {
        ArrayList<String> vowels = new ArrayList<String>();

        vowels.add("testing");
        vowels.add("hello");
        vowels.add("goodbye");
        vowels.add("bingo");
        vowels.add("underway");

        System.out.println(averageVowels(vowels));
    }

    // Q5: What is the time complexity of your averageVowels method? Can it be
    // improved?
    // Constant O(n), n is the total number of characters in a String at the current
    // iteration.
    public static double averageVowels(ArrayList<String> vowels) {
        int totalVowels = 0;

        if (vowels.isEmpty()) {
            return 0.0;
        }
        for (String v : vowels) {
            for (int i = 0; i < v.length(); i++) {
                char ch = v.charAt(i);
                if ("aeiouAEIOU".indexOf(ch) >= 0) {
                    totalVowels++;
                }
            }

        }
        // Q3: Why is it important to cast totalVowels to double before dividing by
        // vowels.size()?
        // If you do integer division it will truncate a remainder. So if totalVowels is
        // 7, and vowel.size is 3, you want 2.333, not just 2.
        return (double) totalVowels / vowels.size();
    }
}
