
import java.util.NoSuchElementException;

/*
Source code provided from text.
All programming questions will be methods added to this file.
 */
//Class LinkedIntList can be used to store a list of integers.
public class linkedIntList {

    // post: constructs an empty list.
    public linkedIntList() {
        front = null;
    }

    // post: returns the current number of elements in the list.
    public int size() {
        int count = 0;
        ListNode current = front;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }

    // pre: 0 <= index < size()
    // post: returns the integer at the given index in the list.
    public int get(int index) {
        return nodeAt(index).data;
    }

    // post: returns comma-separated, bracketed version of list.
    public String toString() {
        if (front == null) {
            return "[]";

        } else {
            String result = "[" + front.data;
            ListNode current = front.next;
            while (current != null) {
                result += ", " + current.data;
                current = current.next;
            }
            result += "]";
            return result;
        }
    }

    // post: returns the position of the first occurrence of the given value (-1 if not found).
    public int indexOf(int value) {
        int index = 0;
        ListNode current = front;
        while (current != null) {
            if (current.data == value) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    // post: appends the given value to the end of the list.
    public void add(int value) {
        if (front == null) {
            front = new ListNode(value);
        } else {
            ListNode current = front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode(value);
        }
    }

    // pre: 0 <= index <= size()
    // post: inserts the given value at the given index.
    public void add(int index, int value) {
        if (index == 0) {
            front = new ListNode(value, front);
        } else {
            ListNode current = nodeAt(index - 1);
            current.next = new ListNode(value, current.next);
        }
    }

    // pre: 0 <= index < size().
    // post: removes value at the given index.
    public void remove(int index) {
        if (index == 0) {
            front = front.next;
        } else {
            ListNode current = nodeAt(index - 1);
            current.next = current.next.next;
        }
    }

    // pre: 0 <= i < size.
    // post: returns a reference to the node at the given index.
    private ListNode nodeAt(int index) {
        ListNode current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    //Book did not provide ListNode code so this is my implementation.
    //Was declared static due to not needing a ref to outer LinkedIntList instance.
    private ListNode front; // first value in the list.

    /**
     * Replaces the value stored at the given index with the provided value.
     *
     * Precondition: 0 <= index < size() This method does NOT create new nodes
     * or modify links. It simply finds the existing node at the given index and
     * updates its data field. @param index @param value
     */
    void set(int index, int value) {
        ListNode current = nodeAt(index); // locate the node (reference to existing object).
        current.data = value;             // mutate the node's data.
    }

    /**
     * Returns the smallest integer stored in the list. Precondition: The list
     * is non-empty.
     *
     * This method traverses the list node by node using a reference to each
     * node. It does not modify the list or its nodes, only reads the data
     * values.
     *
     * @return the minimum integer in the list.
     * @throws NoSuchElementException if the list is empty.
     */
    public int min() {
        if (front == null) {
            throw new NoSuchElementException("List is empty.");
        }

        int min = front.data;
        ListNode current = front.next; // Already used in front.data for min.

        while (current != null) {
            if (current.data < min) {
                min = current.data; // update if smaller.
            }
            current = current.next; // move to next node.

        }
        return min;
    }

    /**
     * Returns true if the list contains any pair of adjacent nodes whose data
     * values differ by exactly 1. (consecutive).
     *
     * The list must have at least two nodes to contain a consecutive pair.
     * Traverses from the front toward the end, comparing each node with its
     * next node.
     *
     * @return true if any adjacent pair is consecutive; false otherwise.
     */
    public boolean hasTwoConsecutive() {
        // A list with 0 or 1 element cannot contain a consecutive pair.
        ListNode current = front;
        // Stop at second-to-last node so current.next is always valid.
        while (current != null && current.next != null) {
            if (Math.abs(current.data - current.next.data) == 1) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private static class ListNode {

        private int data;
        private ListNode next;

        // Constructor 1, next default to null.
        public ListNode(int data) {
            this.data = data;
            this.next = null;
        }

        // Constructor 2, data + next node ref, for insertions.
        public ListNode(int data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }
}
