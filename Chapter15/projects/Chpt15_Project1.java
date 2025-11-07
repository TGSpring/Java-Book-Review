/*
 * Tyler Spring
 * 11/2/25
 * Chapter 15 Project 1
 * The actual List integer in the java.util package has several methods beyond the ones implemented in this chapter.
 * Write a version of ArrayList<E> that adds some or all of these methods. The methods to add are as follows:
 * 
 * public void addAll(int index, ArrayList<E> list)
 * public boolean containsAll(ArrayList<E> list)
 * public boolean equals(Object o)
 * public int lastIndexOf(Object o)
 * public boolean remove(Object o)
 * public void removeAll(ArrayList<E> list)
 * public void retainAll(ArrayList<E>)
 * public Object[] toArray()
 */
public class Chpt15_Project1<E> {

    public static void main(String[] args) {
        MyArrayList<String> addList1 = new MyArrayList<>();

        //Testing addAll
        addList1.add("A");
        addList1.add("B");
        addList1.add("C");
        addList1.add("D");

        MyArrayList<String> addList2 = new MyArrayList<>();
        addList2.add("E");
        addList2.add("F");
        addList2.add("G");
        addList2.add("H");

        addList1.addAll(2, addList2);

        System.out.println("After addAll: " + addList1.toString() + "\n");

        // Testing containsAll
        MyArrayList<String> conList1 = new MyArrayList<>();
        conList1.add("B");
        conList1.add("Z");

        MyArrayList<String> conList2 = new MyArrayList<>();
        conList2.add("E");
        conList2.add("H");

        System.out.println("addList1 contains conList1? " + addList1.containsAll(conList1));
        System.out.println("addList2 contains conList1? " + addList2.containsAll(conList2) + "\n");

        //Testing equals
        MyArrayList<String> eList1 = new MyArrayList<>();

        eList1.add("A");
        eList1.add("B");
        eList1.add("C");
        eList1.add("D");

        MyArrayList<String> eList2 = new MyArrayList<>();
        eList2.add("E");
        eList2.add("F");
        eList2.add("G");
        eList2.add("H");

        MyArrayList<String> eList3 = new MyArrayList<>();

        eList3.add("A");
        eList3.add("B");
        eList3.add("C");
        eList3.add("D");

        MyArrayList<String> eList4 = new MyArrayList<>();
        eList4.add("E");
        eList4.add("F");
        eList4.add("G");
        eList4.add("H");

        MyArrayList<String> eList5 = new MyArrayList<>();
        eList5.add("E");
        eList5.add("Q");
        eList5.add("G");

        System.out.println("List 1 equals List 3? " + eList1.equals(eList3));
        System.out.println("List 2 equals List 4? " + eList2.equals(eList4));
        System.out.println("List 1 equals List 4? " + eList1.equals(eList4));
        System.out.println("List 1 equals a String? " + eList1.equals("ABCD")); // This is to test same type.
        System.out.println("List 2 equals List 5? " + eList2.equals(eList5) + "\n");
    }
}
/*
 * I WILL BE DOING COMPLEXITY ANALYSIS IN THIS FILE RATHER THAN THE GENERIC ONE JUST FOR CLUTTER SAKE.
 * 
 * - addAll
 *  TIME
 *      - n = current size of "this" list.
 *      - m = size of the "list" being added.
 * 
 *          - Capacity check   O(n) worst-case.     Only when resizing is needed.
 *          - Shift            O(n - index)         Proportional to how much needs to move.
 *          - Copy             O(m)                 Always happens.
 *          - Total            O(n - m)             On average.
 *                             O(n + m)             worst-case. This is because if the index is 0, everything shifts.
 * 
 *  SPACE
 *      - Using in-place modification, not creating new arrays(except during capacity expansion).
 *      - During expansion, allocate a new array of size n + m.
 *      Complexity in worst case: O(n + m) with a resize.
 *      Complexity amortized O(1).
 * 
 * 
 *  - containsAll
 * TIME
 *      - n = this.size() size of the list we are checking against.
 *      - m = list.size() size of the argument list passed to containsAll.
 * 
 *      - Outer loop for (i = 0; i < m; i++) = O(m).
 *      - Inner loop for (j = 0; j < n; j++) = O(n), in the worst case.
 *      - Total worst-case: O(m * n)
 * 
 *      Best case: if the first element of list is missing from this, it returns false immediately = O(n)
 *      Average case depends on number of elements found/missing, but worst-case dominates for Big-O analysis.
 * 
 * SPACE
 *      - Only a few extra variables (element, found, i, j) = O(1) constant space.
 *      - No additional arrays or lists are created.
 * 
 * 
 * - equals ## equals was very straight forward so this is not as in-depth.
 * TIME
 *      - Reference Check: O(1).
 *      - Type Check: O(1).
 *      - Size Check: O(1).
 *      - Loop over elements: O(n), where n = size of the list.
 *      - Overall is O(n).
 *      - Worst case all elements match, loop runs n times.
 *      - Best case is mismatch in first element, returns immediately.
 * 
 * SPACE
 *      - Uses only a few local variables i and other.
 *      - No additional arrays or data structures are created.
 *      - Space Complexity = O(1) constant.
 */
