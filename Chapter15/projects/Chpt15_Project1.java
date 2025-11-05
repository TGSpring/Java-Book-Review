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

        System.out.println("After addAll: " + addList1.toString());
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
 */
