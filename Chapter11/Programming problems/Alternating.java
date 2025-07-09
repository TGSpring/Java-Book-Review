
/**
 * Tyler Spring
 * 7/9/2025
 * Chapter 11 Question 2
 * Write a method called alternate that accepts two Lists as its parameters and
 * returns a new List containing alternating elements from
 * the two lists, in the following order.
 * First element from first list
 * First element from second list
 * Second element from first list
 * Second element from second list
 * Third element from first list
 * Third element from second list
 * If the lists do not contain the same number of elements, the remaining
 * elements from the longer list should be placed consecutively at the end.
 * For example for a first list of [1, 2, 3, 4, 5] and a second list of [6, 7,
 * 8, 9, 10, 11, 12], a call of alternate (list1, list2) should return a list
 * containing [1, 6, 2, 7, 3, 8, 4, 9, 5, 10, 11, 12].
 */

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

class Alternating {
    public static void main(String[] args) {
        // Using asList to add due to laziness, do not do this if you are still learning
        // methods like .add.
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(6, 7, 8, 9, 10, 11, 12);

        System.out.println(alternate(list1, list2));

    }

    public static List alternate(List<Integer> list1, List<Integer> list2) {
        // List to return alternated values.
        List<Integer> res = new ArrayList<>();

        // Used to compare which list is bigger, then iterate between both lists.
        int max = Math.max(list1.size(), list2.size());

        /**
         * To iterate, we will loop up the length of the longer list, at each step,
         * check if each list has an element at that index before adding it.
         * By checking it with Math.max, we ensure the for loop runs enough times to
         * cover all elements of both lists.
         * Alternate elements from both lists into a new list, adding leftovers from
         * the longer list at the end.
         */
        for (int i = 0; i < max; i++) {
            // We alternate elements as long as both lists have them, and if one list is
            // longer, you keep adding its remaining elements after the other runs out.
            if (i < list1.size()) {
                res.add(list1.get(i));
            }
            if (i < list2.size()) {
                res.add(list2.get(i));
            }
        }
        return res;
    }
}
/**
 * Time Complexity: O(n), n is equal to the length of the longer list and runs
 * exactly n times. On each iteration we do at most two .get and .add
 * operations,
 * which are O(1). Each element is visited once with no revisiting or shifting.
 * 
 * Space Complexity: O(n + m), We create a new list res that holds all the
 * elements from both input lists, so the total
 * number of elements added is Math.min(n, m) * 2 from interleaving + the extra
 * elements from the longer list (|n - m|).
 */