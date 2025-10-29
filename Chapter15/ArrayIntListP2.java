/*
 * Tyler Spring
 * 10/29/25
 * Chapter 15 Problem 2
 * Write a method called indexOfSubList that accepts another list L as  parameter and returns the starting index of where L first appears 
 * in the list, or -1 if it is not found. All elements of L must appear in sequence and in the same order.
 * For example, if variables called list1 and list2 store [11, -7, 3, 42, 0, 14] and [3, 42, 0], respectively, 
 * the call of list1.indexOfSubList(list2) should return 2.
 */

public class ArrayIntListP2 {

    public static void main(String[] args) {
        ArrayIntList a = makeList(11, -7, 3, 42, 0, 14);
        ArrayIntList b = makeList(3, 42, 0);
        System.out.println(a.indexOfSubList(b)); // Expected 2.

        ArrayIntList c = makeList(1, 2, 3);
        ArrayIntList d = makeList(4, 5);
        System.out.println(c.indexOfSubList(d)); // Expect -1.

        ArrayIntList e = makeList(1, 2, 3);
        ArrayIntList f = makeList();
        System.out.println(e.indexOfSubList(f)); // Expect 0

    }

    private static ArrayIntList makeList(int... vals) {
        ArrayIntList list = new ArrayIntList();
        for (int v : vals) {
            list.add(v);
        }
        return list;
    }
}
