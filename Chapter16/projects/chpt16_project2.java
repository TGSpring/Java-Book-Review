
import java.util.NoSuchElementException;

/**
 * Tyler Spring 1/9/2025 Chapter 16 project 2 The java.util package has an
 * interface called ListIterator that extends the Iterator interface and
 * includes additional methods that are specific to iterating through the
 * elements of lists forward or backward. Write a class called
 * LinkedListIterator2 that adds some or all of these methods for iterating over
 * a doubly linked list. The methods to add are the following(see the Java API
 * Specs for descriptions of each method.)
 *
 * public void add(E value)
 *
 * public boolean hasPrevious()
 *
 * public int nextIndex()
 *
 * public E previous()
 *
 * public int previousIndex()
 *
 * public void set(E value)
 */
public class chpt16_project2 {

    //Trust me when I say I tried to make a helper method to make the testing easier. 
    public static void main(String[] args) {

        // -------------------------
        // Set up the list
        // -------------------------
        LinkedListIterator2<String> list1 = new LinkedListIterator2<>(null);

        // Node creation
        LinkedListIterator2<String>.Node a = list1.new Node();
        LinkedListIterator2<String>.Node b = list1.new Node();
        LinkedListIterator2<String>.Node c = list1.new Node();

        // Adding data
        a.data = "A";
        b.data = "B";
        c.data = "C";

        // Linking nodes
        a.next = b;
        b.prev = a;
        b.next = c;
        c.prev = b;

        // Reinitialize iterator with head
        list1 = new LinkedListIterator2<>(a);

        System.out.println("=== FORWARD ITERATION ===");
        while (true) {
            try {
                String value = list1.next();
                System.out.println("next() returned: " + value);
                System.out.println("nextIndex() = " + list1.nextIndex());
                System.out.println("previousIndex() = " + list1.previousIndex());
                System.out.println("hasPrevious() = " + list1.hasPrevious());
                System.out.println("-----");
            } catch (NoSuchElementException e) {
                System.out.println("Reached the end of the list.");
                break;
            }
        }
        System.out.println("\n=== BACKWARD ITERATION ===");
        while (true) {
            try {
                String value = list1.previous();
                System.out.println("previous() returned: " + value);
                System.out.println("nextIndex() = " + list1.nextIndex());
                System.out.println("previousIndex() = " + list1.previousIndex());
                System.out.println("hasPrevious() = " + list1.hasPrevious());
                System.out.println("-----");
            } catch (NoSuchElementException e) {
                System.out.println("Reached the start of the list.");
                break;
            }
        }

        System.out.println("\n=== EDGE CASE / FAIL TEST ===");
        // Reinitialize iterator with head for fail test
        list1 = new LinkedListIterator2<>(a);

        try {
            // Call next() more times than there are elements to force exception
            for (int i = 0; i < 5; i++) { // list has 3 elements
                System.out.println("next() returned: " + list1.next());
            }
        } catch (NoSuchElementException e) {
            System.out.println("Caught NoSuchElementException as expected when moving past the end!");
        }

    }
}
