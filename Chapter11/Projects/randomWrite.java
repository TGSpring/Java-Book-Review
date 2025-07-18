/*
 * Tyler Spring
 * 7/16/2025
 * Chapter 11 Project 3
 * Write a program that solves the classic "Random Writer" problem. This problem deals with reading input files of text and examining 
 * the frequencies of characters. On the basis of those frequencies, you can generate randomized output that appears to match 
 * the writing style of the original document. The longer the chains you link together, the more accurate the random text will sound.
 * For example a level 4 text (text with chains of 4 letters long) generated from Tom Sawyer might look like this: "en themself, Mr. Welshman, but him awoke, the balmy shore. I'll give him 
 * that he couple overy because in the slated snufflindeed structure's kind was rath. She said that hte wound the door a fever eyes that WITH him." Level 10 random text from the same source 
 * might like like this: "you understanding that they don't come around in the cave should get the word beauteous was over-fondled, and that together and decided that he might as we used to do--it's nobby 
 * fun. I'll learn you." Search the Internet for "Random Writer" to learn more about this problem, such as the specification posed by computer scientist Joseph Zachary.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class randomWrite {
    public static void main(String[] args) {
        String word = "";
        try (Scanner reader = new Scanner(new File("input.txt"))) {
            while (reader.hasNextLine()) {
                word = word + reader.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        int k = 4;
        int lengthToGenerate = 200;

        // Build the model.
        Map<String, List<Character>> model = buildModel(word, k);

        // Pick starting k-gram (we'll use the first k letters of the input)
        if (word.length() < k) {
            System.out.println("Input text is too short for the chosen k.");
            return;
        }

        String start = word.substring(0, k);

        // Generate random text.
        String genWord = generateText(model, start, lengthToGenerate);

        System.out.println("\n--- Random Generated Text ---\n");
        System.out.println(genWord);
    }

    /*
     * The goal with this method is build a Markov model, frequency graph, of
     * characters based on
     * "k-grams", substrings of length k.
     * For every position:
     * - You take k characters, the k-gram, the prefix.
     * - You look at the character that came after that, the next character, the
     * next possibility.
     * - You build a map that says,
     * "Every time I see this k-gram, what are the possible next characters that followed it in the input?"
     * 
     * This is how predictive text, or AI-like generators work, they rely on
     * frequency-based memory of past input.
     */
    public static Map<String, List<Character>> buildModel(String text, int k) {

        /*
         * This model is the graph.
         * Each String key is a k-gram.
         * The value is a list of characters that came right after that k-gram in the
         * original text.
         * That list may contain duplicates, this keeps frequency information.
         */
        Map<String, List<Character>> model = new HashMap<>();

        /*
         * This is the sliding window of size k across the string, stopping before the
         * end so you do not go out of bounds when looking ahead by 1.
         */
        for (int i = 0; i < text.length() - k; i++) {

            // Grabs a k-length slice of the input string starting at index i.
            String kgram = text.substring(i, i + k);

            // This grabs the character right after the k-gram, index i + k.
            char next = text.charAt(i + k);

            /*
             * First time using this method, basically, if the map does not already have a
             * list for this kgram,
             * create a new empty ArrayList and associate it with kgram. Then return the
             * list, new or existing, so I can work
             * with it. .add(next) just adds next to that list, whether it's the first time
             * seeing that kgram or not.
             */
            model.computeIfAbsent(kgram, key -> new ArrayList<>()).add(next);

        }
        return model;
    }

    public static String generateText(Map<String, List<Character>> model, String start, int length) {
        /*
         * Start with the initial k-gram. This is our seed for generating the text.
         * We will gradually add characters to this string to build the final result.
         */
        String result = start;
        /*
         * Track the current sliding window (k-gram) we're generating characters from.
         * It will keep getting updated as we generate new characters.
         */
        String currentKgram = start;

        // Used to randomly select characters from possible next options.
        Random rand = new Random();

        /*
         * Generate the rest of the text, one character at a time.
         * We already have `start.length()` characters in `result`, so we loop for the
         * remaining length.
         */
        for (int i = 0; i < length - start.length(); i++) {

            // Look up the list of possible next characters that follow the current k-gram.
            List<Character> nextChars = model.get(currentKgram);

            // If there are no characters that follow this k-gram in the model (edge case),
            // stop early.
            if (nextChars == null || nextChars.isEmpty()) {
                break;
            }

            /*
             * Randomly select one character from the list.
             * The more frequent a character was in the original text, the more likely it is
             * in this list
             * so the random selection reflects those real frequencies.
             */
            char next = nextChars.get(rand.nextInt(nextChars.size()));

            // Append the selected character to the result string.
            result += next;

            /*
             * Slide the window forward:
             * Take the last k-1 characters of the current k-gram, and add the new character
             * at the end.
             * This becomes the new current k-gram for the next loop iteration.
             */
            currentKgram = currentKgram.substring(1) + next;
        }
        // Return the completed randomly generated text.
        return result;
    }
}
/*
 * Complexity Analysis of Building the Model.
 * 
 * This part processes the input string and constructs a Map<String,
 * List<Character>>
 * We are looping through the string and extracting k-grams and their next
 * character.
 * 
 * Time Complexity:
 * For input string of length n and order k, you loop from 0 to n - k once.
 * Each iteration:
 * - Extracts a substring of length k -> O(k).
 * - Gets or creates the list -> O(1) (amortized hash map access)
 * - Appends to the list -> O(1) (amortized)
 * 
 * Overall complexity of Time = O(n * k), but given k is small and constant,
 * O(n).
 * 
 * Space complexity:
 * - The map holds up to n - k unique k-grams (in worst case if all substrings
 * are unique).
 * - Each entry maps to a list of characters (frequencies of what followed that
 * k-gram).
 * - Worst case: O(n) keys, with a short list of next characters.
 * 
 * Overall complexity of Space = O(n).
 * 
 * Complexity Analysis of Generating the Text.
 * 
 * This part walks the model using random choices.
 * 
 * Time Complexity:
 * Loop runs length - k times.
 * At each step:
 * - Map lookup: O(1).
 * - Random choice from list: O(1).
 * - String concatenation: O(i) for each result += next.
 * 
 * ---- Because String is immutable in Java, each += creates a new copy.
 * So concatenating m characters costs:
 * O(1 + 2 + 3 + .... + m) = O(m^2).
 * Overall complexity of Time = O(m^2), m = length - k.
 * --- Could have been fixed with Stringbuilder, but was not the point of this.
 * 
 * Space Complexity:
 * Storing the result string of m characters -> O(m).
 * No other data structures used.
 * 
 * Overall complexity of Space = O(m).
 */