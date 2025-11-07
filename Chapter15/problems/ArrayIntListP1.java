
/*
 * Tyler Spring
 * 10/26/25
 * Chapter 15 Problem 1
 * Write a method called lastIndexOf that accepts an integer as a parameter and returns the index in the list of the last occurrence of that value, or -1 if the value is not found in the list.
 * For example, if the list stores [1, 18, 2, 7, 18, 39, 18, 40], then the last index of 18 is 6 and the last index of 3 is -1
 */
public class ArrayIntListP1 {

    public static void main(String[] args) {
        ArrayIntList test1 = new ArrayIntList();
        int[] vals = {1, 18, 2, 7, 18, 39, 18, 40};

        for (int v : vals) {
            test1.add(v);
        }

        System.out.println(test1); // Should print {1, 18, 2, 7, 18, 39, 18, 40};
        System.out.println(test1.lastIndexOf(18)); // Expected: 6
        System.out.println(test1.lastIndexOf(3)); // Expected: -1

    }
}
