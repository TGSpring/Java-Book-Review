
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
        System.out.println("=== SETUP LIST ===");
        LinkedListIterator2<String> list1 = new LinkedListIterator2<>(null);

        // Node creation
        LinkedListIterator2<String>.Node a = list1.new Node();
        LinkedListIterator2<String>.Node b = list1.new Node();
        LinkedListIterator2<String>.Node c = list1.new Node();

        // Add data
        a.data = "A";
        b.data = "B";
        c.data = "C";

        // Link nodes
        a.next = b;
        b.prev = a;
        b.next = c;
        c.prev = b;

        // Initialize iterator at head
        list1 = new LinkedListIterator2<>(a);

        // -------------------------
        // Forward iteration
        // -------------------------
        System.out.println("\n=== FORWARD ITERATION ===");
        while (true) {
            try {
                String value = list1.next();
                System.out.println("next() = " + value);
                System.out.println("nextIndex() = " + list1.nextIndex());
                System.out.println("previousIndex() = " + list1.previousIndex());
                System.out.println("hasPrevious() = " + list1.hasPrevious());
                System.out.println("-----");
            } catch (NoSuchElementException e) {
                System.out.println("Reached the end of the list.");
                break;
            }
        }

        // -------------------------
        // Backward iteration
        // -------------------------
        System.out.println("\n=== BACKWARD ITERATION ===");
        while (true) {
            try {
                String value = list1.previous();
                System.out.println("previous() = " + value);
                System.out.println("nextIndex() = " + list1.nextIndex());
                System.out.println("previousIndex() = " + list1.previousIndex());
                System.out.println("hasPrevious() = " + list1.hasPrevious());
                System.out.println("-----");
            } catch (NoSuchElementException e) {
                System.out.println("Reached the start of the list.");
                break;
            }
        }

        System.out.println("\n=== ADD TESTS ===");

        // Reinitialize iterator
        list1 = new LinkedListIterator2<>(a);

        // --- Add at head ---
        list1.add("X");
        System.out.println("Added 'X' at head");
        System.out.println("next() after add = " + list1.next()); // should return X
        System.out.println("previous() after next = " + list1.previous()); // now safe, returns X

        // --- Add in middle ---
        list1.next(); // move past X
        list1.next(); // move past A
        list1.add("Y"); // add before B
        System.out.println("Inserted 'Y' before B");
        System.out.println("previous() = " + list1.previous()); // should return Y
        System.out.println("next() = " + list1.next()); // should return Y

        // --- Add at tail ---
        while (list1.nextIndex() < 3) { // move to C
            list1.next();
        }
        list1.add("Z"); // add after C
        System.out.println("Inserted 'Z' at tail");
        System.out.println("previous() = " + list1.previous()); // should return Z
        System.out.println("next() = " + list1.next()); // should return Z

        // -------------------------
        // SET TESTS
        // -------------------------
        System.out.println("\n=== SET TESTS ===");

        // Reset iterator
        list1 = new LinkedListIterator2<>(a);

        // Move to B and update
        list1.next(); // A
        list1.next(); // B
        list1.set("B-updated");
        System.out.println("Updated B to: " + list1.previous()); // should return B-updated

        // Move to C and update
        list1.next(); // B-updated
        list1.next(); // C
        list1.set("C-updated");
        System.out.println("Updated C to: " + list1.previous()); // should return C-updated

        // -------------------------
        // EDGE CASES
        // -------------------------
        System.out.println("\n=== EDGE CASE / FAIL TEST ===");
        list1 = new LinkedListIterator2<>(a);

        try {
            for (int i = 0; i < 10; i++) { // more than list size
                System.out.println("next() = " + list1.next());
            }
        } catch (NoSuchElementException e) {
            System.out.println("Caught NoSuchElementException as expected when moving past the end!");
        }

        try {
            list1 = new LinkedListIterator2<>(a);
            list1.previous(); // immediately, should fail
        } catch (NoSuchElementException e) {
            System.out.println("Caught NoSuchElementException as expected when moving before start!");
        }

    }
}
