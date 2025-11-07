
/*
 * Tyler Spring
 * 11/2/25
 * Chapter 15 Problem 19
 * Write a method called compress that replaces every pair of elements in the list with a single element equal 
 * to the sum of the pair. If the list is of odd size, leave the last element unchanged. For example, if the list stores
 * [1, 7, 3, 9, 4, 6, 5], your method should change it to store [8, 12, 10, 5].
 */
public class ArrayIntListP19 {

    public static void main(String[] args) {
        ArrayIntList test19 = new ArrayIntList();

        test19.add(1);
        test19.add(7);
        test19.add(3);
        test19.add(9);
        test19.add(4);
        test19.add(6);
        test19.add(5);

        test19.compress();
        System.out.println(test19.toString());
    }
}
/*
 * Time Complexity: O(n) constant.
 *                  - The method makes a single pass through the list, stepping by 2.
 *                      Each step does constant-time work.
 * 
 * Space Complexity: O(1) linear.
 *                   - All changes occur directly in the existing elementData array; no extra 
 *                      structures are created.
 * 
 * Concept Summary:
 *      The key idea is pairwise aggregation: combining two consecutive elements into one.
 *      The algorithm maintains a separate "write" index (newIndex) so it doesn't overwrite unread elements.
 *      The logic if(size % 2 != 0) ensures the last unpaired element is retained.
 *      After processing, size is updated, not the physical array, which means unused array slots 
 * at the end are ignored.
 */
