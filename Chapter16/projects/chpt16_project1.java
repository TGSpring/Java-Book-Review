
import java.util.ArrayList;
import java.util.List;

/**
 * Tyler Spring 12/28/2025 Chapter 16 Project 1 The actual List interface in
 * java.util package has several methods beyond the ones implemented in this
 * chapter. Write a version of LinkedList<E> that adds some or all of these
 * methods. The methods to add are the following (some headers are slightly
 * modified; see Java API Specification for descriptions of each method):
 *
 * public void addAll(int index, List<E> list)
 *
 * public boolean containsAll(List<E> list)
 *
 * public boolean equals(Object o)
 *
 * public int lastIndexOf(Object o)
 *
 * public boolean remove(Object o)
 *
 * public void removeAll(List<E> list)
 *
 * public void retainAll(List<E> list)
 *
 * public Object[] toArray()
 */
public class chpt16_project1 {

    public static void main(String[] args) {
        System.out.println("containsAll test.");
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();

        list1.add(1);
        list1.add(2);
        list2.add(2);
        list2.add(1);

        System.out.println(list1.containsAll(list2) + "\n");

        System.out.println("lastIndexOf Test.");
        LinkedList<Integer> list3 = new LinkedList<>();
        list3.add(1);
        list3.add(2);
        list3.add(3);
        list3.add(2);
        list3.add(4);

        System.out.println("List size: " + list3.size()); // should be 5.

        System.out.println("Last index of 2: " + list3.lastIndexOf(2));
        System.out.println("Last index of 3: " + list3.lastIndexOf(3));
        System.out.println("Last index of 5: " + list3.lastIndexOf(5) + "\n");

        System.out.println("remove Test.");

        LinkedList<Integer> list4 = new LinkedList<>();
        list4.add(1);
        list4.add(2);

        System.out.println("Before Remove: " + list4);

        list4.remove(2);

        System.out.println("After Remove: " + list4 + "\n");

        System.out.println("removeAll Test.");

        LinkedList<Integer> list5 = new LinkedList<>();
        list5.add(1);
        list5.add(2);
        list5.add(3);
        list5.add(2);
        list5.add(4);

        LinkedList<Integer> removeList = new LinkedList<>();
        removeList.add(2);
        removeList.add(4);

        System.out.println("Before removeAll: " + list5);
        list5.removeAll(removeList);
        System.out.println("After removeAll:  " + list5);
        System.out.println("Size: " + list5.size() + "\n");

        System.out.println("\nretainAll Test.");

        LinkedList<Integer> list6 = new LinkedList<>();
        list6.add(1);
        list6.add(2);
        list6.add(3);
        list6.add(2);
        list6.add(4);

        LinkedList<Integer> retainList = new LinkedList<>();
        retainList.add(2);
        retainList.add(4);

        System.out.println("Before retainAll: " + list6);
        list6.retainAll(retainList);
        System.out.println("After retainAll:  " + list6);
        System.out.println("Size: " + list6.size() + "\n");

    }
}
