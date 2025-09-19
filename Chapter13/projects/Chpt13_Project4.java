
/**
 * Tyler Spring
 * 9/17/2025
 * Chapter 13 Project 4
 * Write a program that discovers all anagrams of all words listed in an input
 * file that stores the entries
 * in a large dictionary. An anagram of a word is a rearrangement of its letters
 * into a new legal word.
 * For example, the anagrams of "share" include "shear", "hears", and "hares".
 * Assume that you have a file available
 * to you that lists many words, one per line. Your program should first read in
 * the dictionary file and
 * sort it, but instead of sorting in alphabetical order it should sort
 * according to each word's canonical form.
 * The canonical form of a word contains the same letters as the original, but
 * in sorted order. Thus, the canonical
 * form of "computer" is "cemoprtu", and the canonical form of "program" is
 * "agmoprr". When your dictionary is sorted,
 * the word "program" would be placed before the word "computer", because its
 * canonical form comes first in alphabetical
 * order. Write code to retrieve word's canonical form and a Comparator that
 * compares words by using their canonical forms.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

public class Chpt13_Project4 {

    /*
     * Comparator to sort words by their canonical form.
     * The canonical form of a word is its letters sorted alphabetically.
     * Collections.sort(words, new CanonComparator()) will group anagrams together.
     * 
     * compare(a, b) returns:
     * - negative if a comes before b.
     * - positive if a comes after b.
     * - zero if they are equal.
     */
    static class CanonComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return canonForm(a).compareTo(canonForm(b));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("=== Canonical Form Tests ===");
        for (String testWord : new String[] { "program", "computer", "share", "sheer" }) {
            System.out.println(testWord + " -> " + canonForm(testWord));
        }
        System.out.println("============================\n");

        /*
         * // TESTING STEP 2
         * Map<String, List<String>> groups = new HashMap<>();
         * Scanner in = new Scanner(new File("dictionary.txt"));
         * 
         * // Building map.
         * while (in.hasNextLine()) {
         * String word = in.nextLine().trim();
         * if (word.isEmpty())
         * continue;
         * String canon = canonForm(word);
         * groups.computeIfAbsent(canon, k -> new ArrayList<>()).add(word);
         * }
         * 
         * for (List<String> anagrams : groups.values()) {
         * if (anagrams.size() > 1) {
         * System.out.println(anagrams);
         * }
         * }
         */

        /*
         * Read the dictionary file into a List<String>.
         * - Each line is trimmed to remove extra spaces.
         * - Empty lines are skipped.
         * - Using List<String> allows sorting with a Comparator.
         */
        List<String> words = new ArrayList<>();
        Scanner in = new Scanner(new File("dictionary.txt"));
        while (in.hasNextLine()) {
            String word = in.nextLine().trim();
            if (!word.isEmpty()) {
                words.add(word);
            }
        }

        /*
         * Sort the list of words using CanonComparator.
         * After sorting, all words with the same canonical form (anagrams)
         * appear consecutively in the list.
         * This allows grouping anagrams without using a HashMap.
         */
        Collections.sort(words, new CanonComparator());

        // Print sorted dictionary.
        for (String w : words) {
            System.out.println(w + " -> " + canonForm(w));
        }

        /*
         * Group consecutive words with the same canonical form.
         * - currentGroup stores words that are anagrams.
         * - prevCanon tracks the canonical form of the previous word.
         * - Whenever the canonical form changes, we print the group if it has >1 word.
         */
        List<String> currentGroup = new ArrayList<>();
        String prevCanon = "";

        for (String word : words) {
            String canon = canonForm(word);
            if (canon.equals(prevCanon)) {
                currentGroup.add(word);
            } else {
                if (currentGroup.size() > 1) {
                    System.out.println(currentGroup);
                }
                currentGroup = new ArrayList<>();
                currentGroup.add(word);
                prevCanon = canon;
            }
        }

        if (currentGroup.size() > 1) {
            System.out.println(currentGroup);
        }

    }

    /**
     * Returns the canonical form of a word: letters sorted alphabetically.
     * - Removes non-alphabetical characters.
     * - Converts to lowercase.
     * - Sorts the letters.
     * 
     * Complexity: O(k log k) for a word of length k (sorting the characters).
     */
    public static String canonForm(String word) {
        // Note to self, make sure to use word = word. notation ...
        // Strings are immutable, so we must reassign after replace, trim, and
        // toLowerCase.

        word = word.replace("[^a-zA-Z]", "");
        word = word.trim().toLowerCase();
        char[] letters = word.toCharArray();
        Arrays.sort(letters); // To sort alphabetical.
        return new String(letters);
    }
}

/**
 * NOTES FOR IMPLEMENTATION
 * 
 * 1. Canonical Form:
 * - Sort letters of each word alphabetically.
 * - Words with the same canonical form are anagrams.
 * 
 * 2. Comparator:
 * - Compare words by their canonical forms.
 * - Allows Collections.sort() to group anagrams together.
 * 
 * 3. Read and Sort:
 * - Read all dictionary words into a List<String>.
 * - Trim whitespace and skip empty lines.
 * - Sort list with CanonComparator.
 * 
 * 4. Group Anagrams:
 * - Iterate sorted list, grouping consecutive words with the same canonical
 * form.
 * - Print groups with more than one word.
 * 
 * Complexity:
 * - Canonical form per word: O(k log k) for word length k.
 * - Sorting n words: O(n * k log k)
 * - Grouping: O(n)
 * - Total: O(n * k log k)
 */