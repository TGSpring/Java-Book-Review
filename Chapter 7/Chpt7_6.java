/*
 * Tyler Spring
 * 5/7/2025
 * Chapter 7 Question 14
 * Write a method called contains that accepts two arrays of integers a1 and a2 as parameters and returns a boolean value indicating whether or not
 * the sequence of elements in a2 appears in a1 (true for yes, false for no). 
 * The sequence must appear consecutively and in the same order.
 * For example:
 * int[] list1 = {1, 6, 2, 1, 4, 1, 2, 1, 8};
 * int[] list2 = {1, 2, 1};
 * The call of contains (list1, list2) should return true because the sequence of values [1, 2, 1] is contained in list1 starting at index 5. 
 * If list2 had stored the values [2, 1, 2], the call of contains(list1, list2) would return false. Any two lists with identical elements are considered to 
 * contain each other. Every array contains the empty array, and the empty array does not contain any arrays other than the empty array itself.
 */
public class Chpt7_6 {
    public static void main(String[] args) {
        int[] list1 = { 1, 6, 2, 1, 4, 1, 2, 1, 8 };
        int[] list2 = { 1, 2, 1 };
        System.out.println(contains(list1, list2));

    }

    public static boolean contains(int[] a1, int[] a2) {
        // Check edge cases, if a2 is empty, return true.
        if (a2.length == 0) {
            return true;
        }
        // If a1 is shorter than a2 their can not be a match.
        if (a1.length < a2.length) {
            return false;
        }
        // Iterate over arrays to find match. Starting in a1.
        for (int i = 0; i <= a1.length - a2.length; i++) {
            // Using inner loop, check if a2's sequence matches a1 starting at i.
            boolean match = true;
            for (int j = 0; j < a2.length; j++) {
                if (a1[i + j] != a2[j]) {
                    match = false;
                    break; // no match found.
                }
            }
            // Match found.
            if (match) {
                return true;
            }
        }

        // If no match is found.
        return false;
    }
}
/*
 * Time complexity is linear O(n), we are checking for a sequence of length m,
 * a2.length at each candidate position. The worst case time is O((n - m + 1) *
 * m).
 * Space complexity is constant O(1), fixed size arrays, no structures used.
 */