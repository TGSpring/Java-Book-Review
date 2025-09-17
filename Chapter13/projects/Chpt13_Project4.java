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
public class Chpt13_Project4 {
    public static void main(String[] args) {

        /**
         * NOTES FOR IMPLEMENTATION
         * 
         * Step 1: Canonical Form of a Word.
         * - Definition: Rearrange the letters of a word in sorted order.
         * - If two words have the same canonical form, they are anagrams.
         * 
         * function canWords(wor):
         * letters = convert word to char array
         * sort letters alphabetical
         * return string made from sorted letters
         * 
         * HINTS
         * - use word.toCharArray() to get characters
         * - use Arrays.sort(charArray) to sort them
         * - Convert back with new String(charArray)
         * 
         * 
         * 
         * Step 2: Comparator for Sorting by Canonical Form.
         * - A Comparator<String> compares two words by their canonical forms.
         * - This allows you to sort the dictionary so that anagrams appear next to each
         * other.
         * 
         * function compare(word1, word2):
         * return canonicalForm(word1) compared to canonicalForm(word2)
         * 
         * Step 3: Read Dictionary and Sort.
         * - Read the file line by line into a List<String> or ArrayList<String>.
         * - Sort the list using your canonical comparator.
         * 
         * words = read all lines from file
         * sort(words, canonicalComparator)
         * 
         * Step 4: Group Anagrams.
         * - Since sorting puts anagrams together, you can just iterate through the
         * sorted list and group consecutive
         * words with the same canonical form.
         * - A Map<String, List<String>> works well here.
         * Key = canonical form.
         * Value = list of words with canonical form.
         * 
         * for each word in words:
         * canon = canonicalForm(word)
         * if canon not in map:
         * map[canon] = new list
         * map[canon].add(word)
         * 
         * Step 5: Print output.
         * - Print all the lists in the map that have more than one word(actual
         * anagrams).
         * 
         * for each list in map.values():
         * if list.size() > 1:
         * print list
         */
    }
}
