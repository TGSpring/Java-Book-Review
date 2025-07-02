/*
 * Tyler Spring
 * 6/29/2025
 * Chapter 10 Question 17
 * Write a method called interleave that accepts two ArrayLists of integers a1 and a2 as parameters and inserts the elements of a2 into a1 at alternating indexes.
 * If the lists are of unequal length, the remaining elements of the longer list are left at the end of a1.
 * For example, if a1 stores [10, 20, 30] and a2 stores [4, 5, 6, 7, 8], the call of interleave (a1, a2); should change a1 to store [10, 4, 20, 5, 30, 6, 7, 8].
 * If a1 had stored [10, 20, 30, 40, 50] and a2 had stored [6, 7, 8], the call of interleave(a1, a2); would change a1 to store [10, 6, 20, 7, 30, 8, 40, 50].
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Chp10_Q2 {
    public static void main(String[] args) {
        ArrayList<Integer> a1 = new ArrayList<Integer>();
        ArrayList<Integer> a2 = new ArrayList<Integer>();

        // .add can only take one argument at a time, I am not typing that all out, so
        // you CAN use this, but just understand what you are doing.
        a1.addAll(Arrays.asList(10, 20, 30));
        a2.addAll(Arrays.asList(4, 5, 6, 7, 8));

        interLeave(a1, a2);
        System.out.println(a1);

    }

    public static void interLeave(ArrayList<Integer> a1, ArrayList<Integer> a2) {
        // Determining which is longer.
        int minSize = Math.min(a1.size(), a2.size());

        // Used to alternate indexes.
        int insertIndex = 1;

        for (int i = 0; i < minSize; i++) {
            a1.add(insertIndex, a2.get(i));
            // Increments index by 2 in order to alternate, not overwrite, elements.
            insertIndex += 2;
        }
        // If a2 is longer, add remaining elements to the end of a1
        for (int i = minSize; i < a2.size(); i++) {
            a1.add(a2.get(i));
        }
    }
}

// Q1: Why does the insertIndex need to be incremented by 2 after each
// insertion?
// Because it needs to alternate indexes with the current one in a1 and then
// insert between those indexes with a2's values. After each insertion, the list
// grows,
// so the next insertion point must skip over both the newly inserted and the
// original.

// Q2: What would happen if you inserted elements from a2 into a1 at index i
// instead of insertIndex?
// It would overwrite the elements in a1 at that current index, not intended
// outcome.

// Q4: What is the time complexity of this interLeave method? Can it be
// improved?
// This one is weird, the time complexity is O(n + m * k). N is the size of a1,
// m is the size of a2, and k is the number of insertions.
// Since each insertion into an ArrayList can take O(n) time in the worst case
// due to shifting. However for large lists this is not ideal.
// If you wanted, you could build a new list by merging both lists in order,
// then assign or return it, but that is not the scope here.