
/**
 * Tyler Spring
 * 1/23/2026
 * Chapter 16 project 3
 * The implementation of several methods is or can be the same between our ArrayList and LinkedList. Write a
 * common abstract superclass called AbstractList that implements the common behavior and is extended by both ArrayList
 * and LinkedList. Factor out the common code from the two list classes into this abstract superclass so no code
 * duplication occurs between the two. Use iterators wherever possible in the abstract code to ensure that the
 * implementation is efficient for both types of lists.
 */
public class chpt16_project3 {

    public static void main(String[] args) {

        //Basics
        System.out.println("TESTING BASICS");
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        list.add(1, 15);
        System.out.println(list);

        System.out.println(list.get(1));
        System.out.println(list.size());
        System.out.println();

        //Iterator test.
        System.out.println("TESTING ITERATOR");
        for (Integer val : list) {
            System.out.println(val + " ");
        }
        System.out.println();

        //Remove test.
        System.out.println("TESTING REMOVE");
        list.remove(1);
        list.remove(Integer.valueOf(20)); //remove by object.
        System.out.println(list);
        System.out.println();

        //containsAll/removeAll/retainAll
        System.out.println("TESTING CONTAINSALL / REMOVEALL / RETAINALL");
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(10);
        System.out.println(list.containsAll(list2));

        list2.add(99);
        System.out.println(list.containsAll(list2));

        list.add(20);
        list.add(30);
        list2.clear();
        list2.add(20);
        list.retainAll(list2);
        System.out.println(list);
        System.out.println();

        //lastIndexOf/equals/addAll/toArray
        System.out.println("TESTING LASTINDEXOF / EQUALS / ADDALL / TOARRAY");
        list.add(20); //duplicate.
        System.out.println(list.lastIndexOf(20));

        LinkedList<Integer> copy = new LinkedList<>();
        copy.add(20);
        copy.add(20);
        System.out.println(list.equals(copy));

        Object[] arr = list.toArray();
        System.out.println(java.util.Arrays.toString(arr));

        list.addAll(1, java.util.List.of(99, 100));
        System.out.println(list);
    }
}
