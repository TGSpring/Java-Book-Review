/*
 * Tyler Spring
 * 5/7/2025
 * Chapter 7 Question 4
 * Write a method called isSorted that accepts an array of real numbers as parameter and returns true if the list is in
 * sorted (nondecreasing) order and false if otherwise.
 * For Example:
 * if arrays named list1 and list2 store 
 * [16.1, 12.3, 22.2, 14.4] and [1.5, 4.3, 7.0, 19.5, 25.1, 46.2] 
 * respectively, the calls isSorted(list1) and isSorted(list2) should return false and true respectively. Assume the array has at least one element.
 * A one-element array is considered to be sorted.
 */
public class Chpt7_4 {
    public static void main(String[] args) {
        double[] list1 = { 16.1, 12.3, 22.2, 14.4 };
        double[] list2 = { 1.5, 4.3, 7.0, 19.5, 25.1, 46.2 };

        System.out.print("list1 is: " + isSorted(list1) + "\n" +
                "list2 is: " + isSorted(list2));
    }

    public static Boolean isSorted(double[] list) {
        // Iterates through array to check if it is in ascending order.
        for (int i = 0; i < list.length - 1; i++) {
            // Checks current element against proceeding one.
            if (list[i] > list[i + 1]) {
                return false;
            }

        }
        return true;
    }
}
/*
 * Time complexity is linear O(n), n is the number of elements in the array,
 * worst case is the array is sorted.
 * Space complexity is constant O(1), fixed size arrays, no variables, 1 loop, 1
 * conditional.
 */