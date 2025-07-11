/*
 * Tyler Spring
 * 7/11/2025
 * Chapter 11 Question 11
 * Write a method called SymmetricSetDifference that accepts two Sets as parameters and returns a new Set containing their symmetric set difference (that is, th e set of elements contained in either of the two sets, not both).
 * For example, the symmetric difference between the sets [1, 4, 7, 9] and [2, 4, 5, 6, 7] is [1, 2, 5, 6, 9].
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SymDif {
    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        set1.addAll(Arrays.asList(1, 4, 7, 9));
        set2.addAll(Arrays.asList(2, 4, 5, 6, 7));

        System.out.println(SymmetricSetDifference(set1, set2));

    }

    public static Set<Integer> SymmetricSetDifference(Set<Integer> set1, Set<Integer> set2) {
        // Creating result set that contains all elements from both sets.
        Set<Integer> result = new HashSet<>(set1);
        result.addAll(set2);

        // Creating intersection set with the elements common to both.
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        // Remove the intersection from the result.
        result.removeAll(intersection);

        // Return result.
        return result;
    }
}
/*
 * Time complexity is linear O(n), where n is the total number of elements
 * across both sets.
 * addAll, retainAll, and removeAll each run in linear time relative to the
 * number of elements.
 * We're doing a constant number of passes over the input sets.
 * 
 * Space Complexity is linear O(n), where n is the total number of unique
 * elements from both sets.
 * We create new sets, result and intersection, that at most hold all elements
 * from set1 and set2 combined.
 */