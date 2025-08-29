
/**
 * Tyler Spring
 * 8/29/2025
 * Chapter 12 Project 7
 * Write a program that uses recursive backtracking to find all ancestors and descendants of a person given a file of familial
 * relationships. For ancestors it must show all parents, all grandparents, all great grandparents, etc. For descendants it must show 
 * all children, all grandchildren, all great grandchildren, etc. The program also must use indentation to make it clear who is a parent of whom 
 * and who is a child of whom. Write two recursive methods that use backtracking to explore for ancestors and descendants respectively. Here is a possible
 * example:
 * 
 * Input file? tudor.dat
 * Whose info? Margaret 
 * 
 * Ancestors:
 *      Margaret
 *          Elizabeth of York
 *          Henry VII
 * 
 * Descendants:
 *      Margaret
 *          James V
 *              Mary, Queen of Scots 
 *                  James VI & I 
 *          Margaret Stuart 
 *              Henry, Lord Darnley
 *                  James VI & I
 */

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class chpt12_Project7 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(new File("family.txt"));

        Map<String, List<String>> parentMap = new HashMap<>();
        Map<String, List<String>> childMap = new HashMap<>();

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] parts = line.split(" ");
            String parent = parts[0];
            String child = parts[1];
            parentMap.computeIfAbsent(child, k -> new ArrayList<>()).add(parent);
            childMap.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
            /**
             * The parentMap maps each child to a list of their parents.
             * Checks if child is already a key in the map.
             * If not, it puts the child in the map with a new empty ArrayList as its value.
             * If yes, it just returns the existing list for that child.
             * Adds the current parent to the list of parents for that child.
             * 
             * "If this child isn't in the map yet, add them with an empty list. Then, add
             * this parent to their list of parents".
             * 
             * 
             * At this point we now have a Map of Strings and Lists, parentMap.
             * This maps each child's name, String, to a list of their parents,
             * List<String>.
             * 
             * Each value in the map is an ArrayList<String>. This is the list of parents
             * for each child.
             * 
             * The childMap maps each parent to a list of their children.
             * Both maps are built in the same loop for efficiency.
             * These maps will be used in recursive methods to find all ancestors and
             * descendants.
             * 
             */

        }
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the family members name: ");
        String name = input.nextLine();

        System.out.println("\nAncestors");
        printAncestors(name, parentMap, 1, new HashSet<>());

        System.out.println("\nDescendants");
        printDescendants(name, childMap, 1, new HashSet<>());
    }

    /*
     * Print the current person, indented by indent spaces.
     * If already visited, return (preventing inf loop).
     * Mark this person as visited.
     * For each parent of this person (from the map), recursively call the method,
     * increasing the indentation.
     */
    public static void printAncestors(String name, Map<String, List<String>> parentMap, int indent,
            Set<String> visited) {
        for (int i = 0; i < indent; i++) {
            System.out.print("    "); // 4 spaces per indent level.
        }
        System.out.println(name);

        // Avoiding cycles.
        if (visited.contains(name)) {
            return;
        }
        visited.add(name);

        // Recurse for each parent
        List<String> parents = parentMap.get(name);
        if (parents != null) {
            for (String parent : parents) {
                printAncestors(parent, parentMap, indent + 1, visited);
            }
        }
    }

    // Child Version.
    // You very well could just combine the two recursive methods to just one by
    // taking a map as a parameter and call it with either the parent or child maps,
    // but the book asked for two
    public static void printDescendants(String name, Map<String, List<String>> childMap, int indent,
            Set<String> visited) {
        for (int i = 0; i < indent; i++) {
            System.out.print("    ");
        }
        System.out.println(name);

        if (visited.contains(name)) {
            return;
        }
        visited.add(name);

        List<String> childs = childMap.get(name);
        if (childs != null) {
            for (String child : childs) {
                printDescendants(child, childMap, indent + 1, visited);
            }
        }
    }
}
/**
 * Space Complexity is just O(n), where n is the depth of the deepest ancestor
 * or descendant tree, longest chain in the family tree.
 * This is because the recursion stack will never be deeper than the longest
 * path from the starting person.
 * 
 * Time Complexity is O(V + E), where:
 * V is the number of unique people, nodes, in the relevant tree, ancestors or
 * descendants.
 * E is teh number of relationships, edges traversed.
 * In the worst case, if you print all ancestors or all descendants, you may
 * visit every person and every relationship in the tree once.
 */