
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
        // Stop at second-to-node so current.next is always valid.
        while (current != null && current.next != null) {
            if (Math.abs(current.data - current.next.data) == 1) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * stretch method
     *
     * Replaces each node in the list with n copies of itself.
     *
     * If n <= 0, the list becomes empty. Traverses the list using a current
     * pointer. For each node, inserts n-1 copies immediately after it. Uses a
     * temp pointer to remember the original next node. so traversal continues
     * correctly after inserting duplicates.
     *
     * 1. Only the data values are duplicated; no other node links are modded.
     * 2. Insert happens "in-place" without using add() to maintain order. 3.
     * Handles all nodes and respects the factor n correctly.
     *
     * @param n The factor by which to duplicate each element.
     */
    public void stretch(int n) {
        if (n <= 0) {
            front = null;
            return;
        }
        ListNode current = front;
        while (current.next != null) {
            ListNode temp = current.next;

            // Insertion loop.
            for (int i = n - 1; i > 0; i--) {
                // Creates a new node with the same data and links it to the old current.next.
                current.next = new ListNode(current.data, current.next);

                // Moves pointer into newly created copy so the next iteration adds after it.
                current = current.next;
            }
            // temp holds the original next node so we don't get stuck in duplicates.
            current = temp;
        }
    }

    /**
     * transferFrom method.
     *
     * Attaches all nodes form the given other list to the end of this list.
     *
     * Does not create any new nodes; only mods the next pointers. After the
     * operation, the other list is emptied (front set to null). Handles all
     * edge cases: 1. This list is empty -> simply points front to the other
     * list's front. 2. Other list is empty -> no changes to this list.
     * Traverses this list to find the last node before linking. Preserves the
     * original order of both lists.
     *
     * @param other The linked list whose elements are transferred to the end of
     * this list.
     */
    public void transferFrom(linkedIntList other) {
        if (this.front == null) {
            this.front = other.front;
            return;
        }

        if (other.front == null) {
            return;
        }

        ListNode current = this.front;
        while (current.next != null) {
            current = current.next;
        }
        current.next = other.front;
        other.front = null;
    }

    /**
     * Removes all occurrences of the given value from the list.
     *
     * This method mods the list in place by updating node links. No new nodes
     * are created.
     *
     * First, any matching values at the front of the list are removed by
     * advancing the front reference. Then the rest of the list is traversed,
     * and whenever a node's next value matches teh target, that node is skipped
     * by relinking current.next to current.next.next.
     *
     * If the list is empty of no values match, the list remains unchanged.
     *
     * @param value the value to remove from the list.
     */
    void removeAll(int value) {
        // Edge case testing. 
        while (front != null && front.data == value) {
            front = front.next;
        }

        ListNode current = front;
        while (current.next != null) {
            if (current.next.data == value) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }

        }

    }

    /**
     * Removes all nodes at even-number indexes from this list and returns them
     * in a new list. The original list is modified to contain only the nodes at
     * odd-number indexes. The relative order of nodes is preserved in both
     * lists.
     *
     * For example: given list1 storing [8, 13, 17, 4, 9, 12]: - After calling
     * linkedIntList list2 = list1.removeEvens(); - list1 stores [13, 4, 9]
     * (odd-indexed nodes). - list2 stores [8, 17, 12] (even-indexed nodes).
     *
     * No new nodes are created; the method rearranges the existing links.
     *
     * @return a new linkedIntList containing the even-indexed nodes.
     */
    linkedIntList removeEvens() {
        linkedIntList result = new linkedIntList();

        // Empty list and 1 index check.
        if (front == null || front.next == null) {
            result.front = front; // Safer even if the front is null.
            front = null;         // Original list becomes empty.
            return result;
        }

        // First node is always even-indexed so it is pre-set.
        result.front = this.front;

        ListNode tail = result.front;

        // Original list skips the first node.
        ListNode current = this.front.next;
        this.front = current;

        while (current != null && current.next != null) {

            // Save next even-indexed node.
            ListNode nextEven = current.next.next;

            // Move the even-indexed node to result.
            tail.next = current.next;
            tail = tail.next;

            // Skip the moved node in the original list.
            current.next = nextEven;

            // Advance current in original list.
            current = nextEven;
        }

        // Nullify current from original.
        tail.next = null;

        return result;

    }

    /**
     * Removes range of nodes from the list, from index 'Start' to 'end'
     * (inclusive)
     *
     * @param start the starting index of the range to remove.
     * @param end the ending index of the range to remove.
     * @throws IllegalArgumentException if start or end is negative.
     *
     * The method works by keeping two pointers: - pre: the node immediately
     * before the start of the range. - endNode: the node immediately after the
     * end of the range. After locating these nodes, the method unlinks the
     * range from the list.
     */
    void removeRange(int start, int end) {
        if (start < 0 || end < 0) {
            throw new IllegalArgumentException("invalid range.");
        }

        ListNode prev = null;   // Node before start.
        ListNode endNode = front; // Node after end, will advance.

        // Advance prev and endNode to correct positions.
        for (int i = 0; i <= end; i++) {
            if (i < start) {
                prev = endNode; // Last node before start.
            }
            endNode = endNode.next; // Move towards node after end.
        }

        // If removing from the front, update front.
        if (start == 0) {
            front = endNode;    // Remove from front.
        } else {
            prev.next = endNode;    // Unlink range.
        }
    }

    void rotate() {
        // Edge validation first.
        if (front == null || front.next == null) {
            return;
        }

        // Node being rotated.
        ListNode oldFront = front;

        // Node to walk to tail.
        ListNode current = front;

        // Traverse the list until end.
        while (current.next != null) {
            current = current.next;
        }

        // Updating new front to second node in list.
        front = front.next;

        // Link current tail node to the old front node.
        current.next = oldFront;

        // Sever link from old front node to the rest of the list.
        // Prevents a cycle forming after appending.
        oldFront.next = null;

    }

    void reverse() {
        // Pointer to the previous node (starts as null.)
        ListNode prev = null;

        // Pointer used to traverse the list.
        ListNode current = front;

        // Traverse the list, reversing links as we go.
        // IMPORTANT, normally it is current.next != null, not the case when reversing.
        while (current != null) {
            // Save the next node before breaking the link.
            ListNode next = current.next;

            // Reverse the current node's next reference. First run is null, picturing this helps.
            current.next = prev;

            // Advance prev to the current node. 
            prev = current;

            // Advance current to the saved next node;
            current = next;
        }

        // Update front to the new head of the reversed list.
        front = prev;

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
