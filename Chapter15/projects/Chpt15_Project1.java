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
 * public void retainAll(ArrayList<E> list)
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

        // Testing lastIndexOf.
        MyArrayList<String> lastList1 = new MyArrayList<>();
        lastList1.add("A");
        lastList1.add("B");
        lastList1.add("A");
        lastList1.add("C");
        lastList1.add("A");

        System.out.println("Last index of A: " + lastList1.lastIndexOf("A"));
        System.out.println("Last index of A: " + lastList1.lastIndexOf("B"));
        System.out.println("Last index of A: " + lastList1.lastIndexOf("Z") + "\n");

        // Testing remove.
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        System.out.println("Original list: " + list);

        // Remove middle element
        System.out.println("Removing 'C': " + list.remove("C"));
        System.out.println("List after removing 'C': " + list);

        // Remove first element
        System.out.println("Removing 'A': " + list.remove("A"));
        System.out.println("List after removing 'A': " + list);

        // Remove last element
        System.out.println("Removing 'E': " + list.remove("E"));
        System.out.println("List after removing 'E': " + list);

        // Remove element that doesn't exist
        System.out.println("Removing 'Z': " + list.remove("Z"));
        System.out.println("List after attempting to remove 'Z': " + list + "\n");

        // Testing removeAll
        MyArrayList<String> rAList = new MyArrayList<>();
        rAList.add("2");
        rAList.add("5");
        rAList.add("3");
        rAList.add("2");
        rAList.add("1");

        MyArrayList<String> rAList2 = new MyArrayList<>();
        rAList2.add("2");
        rAList2.add("5");

        rAList.removeAll(rAList2);
        System.out.println("Calling removeAll " + rAList + "\n");

        // Testing retainAll
        MyArrayList<String> retainList = new MyArrayList<>();
        retainList.add("A");
        retainList.add("B");
        retainList.add("C");
        retainList.add("D");
        retainList.add("E");

        MyArrayList<String> toKeep = new MyArrayList<>();
        toKeep.add("B");
        toKeep.add("D");
        toKeep.add("X"); // X isn’t in retainList

        System.out.println("Original list: " + retainList);
        retainList.retainAll(toKeep);
        System.out.println("After retainAll(toKeep): " + retainList + "\n");

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
 * 
 * 
 * - lastIndexOf
 * TIME
 *      - Worst case: The element is not present or at the start of the list, loop runs through all n elements.
 *              O(n).
 *      - Best case: The element is at the last position, loop returns immediately.
 *              O(1).
 *      - Average case: On average, element appears in the middle, loop runs n/2 times.
 *              O(n).
 * 
 * SPACE
 *      - Only few variables are used, i.
 *      - No extra arrays or objects created.
 *      - Space Complexity = O(1).
 * 
 * - remove
 * TIME
 *          Finding element
 *      - In the worst case, o is not in the list or is at the last index, so you scan all size elements.
 *              O(n), n = size of the list.
 * 
 *          Shifting elements
 *      - In the worst case, you remove the first element, so you shift all remaining size - 1 elements.
 *              O(n).
 * 
 *      Worst case: O(n) + O(n) = O(n), still linear, constants ignored.
 *      Best case: removing the last element, no shifting, O(n) still for scanning.
 * 
 * SPACE
 *      - Only use few extra variables i, j.
 *      - No new arrays are allocated.
 *      - Space complexity = O(1), constant.
 * 
 *  - removeAll
 * TIME
 *      - Iterates over list.
 *      - m = list.size().
 *      - outer loop runs m times.
 * 
 *      - this.remove(elementToRemove) searches for the element in your list, size = n.
 *      In worst case:
 *           - remove does a linear scan through the list to find the element = O(n).
 *           - After finding it, it shifts all elements after it left by 1 = O(n).
 *           - So each remove call is  O(n).
 *      - If the element occurs k times in this list, the inner while loop calls remove k times.
 *          - Total cost for one element = O(n * k).
 * 
 *      - If list has m elements and each element occurs up to k_i times in your list of size n:
 *              O(i=1∑m​ki​⋅n)≤O(m⋅n2) (i am not learning how to format that for this, just figure it out.)
 * 
 *      - Worst case is every element in list occurs everywhere in your list.
 *      - Then removeAll - O(m * n^2).
 * 
 * SPACE
 *      - Only few variables i, elementToRemove, = O(1).
 * 
 *  - retainAll
 * TIME
 *      - outer loop:
 *          - iterates over each element in the main list.
 *          - size of main list = n.
 *          - O(n) iterations.
 * 
 *      - inner loop:
 *          - iterates over list parameter to check if elementToRetain exists.
 *          - size of param list = m.
 *          - Worst case, compares all m elements for each outer iteration O(m).
 * 
 *      - Remove operation:
 *          - remove(Object o) itself does a linear search and shifts elements left.
 *          - In worst case, removing an element at the start requires shifting n elements O(n).
 *          - only happens for elements not in the retain list.
 *          - Worst case: all elements are removed, the remove call dominates O(n) per removal.
 * 
 *       - Best: O(n * m)
 *       - Worst: O(n^2)
 * 
 * SPACE
 *      - Only using few extra variables flag, elementToRetain, i, j.
 *      - O(1).
 */
