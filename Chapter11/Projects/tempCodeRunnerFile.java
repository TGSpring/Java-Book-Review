
/*
 * Tyler Spring
 * 7/13/2025
 * Chapter 11 Project 1
 * Write a program that computes the edit distance (also called the Levenshtein distance) between two words.
 * The edit distance between two strings is the minimum number of operations that are needed to transform 
 * one string into the other. For this program, an operation is a substitution of a single character, such as 
 * from "brisk" to "brick". The edit distance between the words "dog" and "cat" is 3, following the chain 
 * of "dot", "cot", and "cat" to transform "dog" into "cat". When you compute the edit distance between two words,
 * each intermediate word must be an actual valid word. Edit distances are useful in applications that need
 * to determine how similar two strings are, such as spelling checkers.
 * 
 * Read your input from a dictionary text file. From this file, compute a map from every word to its
 * immediate neighbors, that is, the words that have an edit distance of 1 from it. Once this map is built, 
 * you can walk it to find paths from one word to another.
 * 
 * A good way to process paths to walk the neighbor map is to use a linked list of words to visit, starting 
 * with the beginning word, such as "dog". Your algorithm should repeatedly remove the front word of the list
 * and add all of its neighbors to the end of the list, until the ending word (such as "cat") is found or until 
 * the list becomes empty, which indicates that no path exists between the two words.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Queue;

public class Leven {
    public static void main(String[] args) {
        /*
         * Load all dictionary words into a Set to allow O(1) membership checks.
         * Using a Set avoids duplicates and speeds up lookups during neighbor searches.
         */
        Set<String> dictionary = readWords();

        /*
         * Build a map where each word keys to a Set of its immediate neighbors.
         * Neighbors are words differing by exactly one character.
         * Precomputing neighbors helps BFS run efficiently.
         */
        Map<String, Set<String>> neighborsMap = new HashMap<>();
        for (String word : dictionary) {
            neighborsMap.put(word, getNeighbors(word, dictionary));
        }

        String start = "dog";
        String end = "cat";

        // Use BFS on the neighbor graph to find the shortest transformation path.
        List<String> path = findPath(start, end, neighborsMap);

        // Output the path if it exists, else inform no path found.
        if (path != null) {
            System.out.println("Edit path from '" + start + "' to '" + end + "': " + path);
        } else {
            System.out.println("No path found between '" + start + "' and '" + end + "'.");
        }

    }

    public static Set<String> readWords() {
        /*
         * Reads words from a file and stores them in a HashSet for fast lookups.
         * Using try-with-reasources ensures Scanner closes properly, avoiding resource
         * leaks.
         */

        Set<String> dict = new HashSet<>();

        try (Scanner input = new Scanner(new File("Dict.txt"))) {
            while (input.hasNextLine()) {
                String word = input.nextLine().trim().toLowerCase();
                dict.add(word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        return dict; // Return the complete dictionary for later use.
    }

    /*
     * Finds all words in the dictionary that differ by exactly one letter from the
     * given word.
     * This builds the edges in the word graph.
     */
    public static Set<String> getNeighbors(String word, Set<String> dictionary) {

        Set<String> neighbors = new HashSet<>();

        /*
         * Iterate over all words in the dictionary to find valid neighbors.
         * Though this is O(n) per call, precomputing neighbors once amortizes this
         * cost.
         */
        for (String cur : dictionary) {
            if (cur.length() != word.length()) {
                continue; // Skip words of different lengths since only same-length words can be
                          // one-letter apart.
            }

            // Count character differences to determine if candidate is exactly one letter
            // different.
            int diff = 0;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != cur.charAt(i)) {
                    diff++;
                    if (diff > 1) // Early exit if more than one difference found.
                        break;
                }
            }

            if (diff == 1) {
                neighbors.add(cur);
            }
        }
        return neighbors;
    }

    /*
     * Performs a breadth-first search to find the shortest sequence of
     * transformations from start to end.
     * Each word is a node, edges connect words that differ by one letter.
     */
    public static List<String> findPath(String start, String end, Map<String, Set<String>> neighborMap) {
        // Queue holds paths (lists of words) to explore; BFS ensures shortest path
        // found.
        Queue<List<String>> queue = new LinkedList<>();

        // Track visited words to avoid revisiting and infinite loops.
        Set<String> visited = new HashSet<>();

        // Initialize BFS with the start word as the first path.
        List<String> initialPath = new ArrayList<>();
        initialPath.add(start);
        queue.add(initialPath);
        visited.add(start);

        while (!queue.isEmpty()) {
            List<String> path = queue.remove();
            String lastWord = path.get(path.size() - 1);

            // If last word in path is the target, we've found the shortest transformation.
            if (lastWord.equals(end)) {
                return path;
            }

            // Explore all neighbors of lastWord that haven't been visited yet.
            for (String neighbor : neighborMap.getOrDefault(lastWord, Collections.emptySet())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);

                    // Create a new path with this neighbor appended and enqueue it.
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }

        // No path found is BFS exhausts all possibilities.
        return null;
    }
}
