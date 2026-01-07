
import java.util.List;
import java.util.Objects;

public class LinkedList<E> {

    private Node front; // reference to the first node.
    private int size;   // tracks number of elements.

    // Node class inside LinkedList.
    private class Node {

        E data;
        Node next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    // Constructor.
    public LinkedList() {
        front = null;
        size = 0;
    }

    // Basic linkedList methods.
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds.");
        }
        Node current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public void add(E value) {
        Node newNode = new Node(value);
        if (front == null) {
            front = newNode;
        } else {
            Node current = front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        size++;
    }

    public boolean contains(E value) {
        Node current = front;
        while (current != null) {
            if (current.data.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node current = front;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // Project asked methods.
    /**
     * Checks if this list contains all elements from another list.
     *
     * @param list2 the LinkedList of elements to check for.
     * @return true if every element in list2 is also in this list; false
     * otherwise
     *
     * @implNote This method iterates over each element in list2 and calls
     * contains(E) on this list. Runs in O(n*m) time where n is this.size() and
     * m is list2.size().
     */
    public boolean containsAll(LinkedList<E> list2) {
        for (int i = 0; i < list2.size(); i++) {
            E temp = list2.get(i);
            if (!this.contains(temp)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this
     * list.
     *
     * @param o the element to search for.
     * @return the index of the last occurrence of o, or -1 if not found.
     *
     * @implNote Traverse the list from front to end, keeping track of both the
     * current index and the last index where the element was found. Runs in
     * O(n) time where n is the size of the list.
     */
    public int lastIndexOf(Object o) {
        int index = 0;
        int last = -1; // last occurrence.
        Node current = front;
        while (current != null) {
            if (current.data.equals(o)) {
                last = index;
            }
            current = current.next;
            index++;
        }
        return last;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if
     * it is present.
     *
     * @param o the element to remove.
     * @return true if an element was removed; false if the element was not
     * found.
     *
     * @implNote - Checks if the front node contains the element. If so, updates
     * 'front' and decrements 'size'. - Otherwise, traverses the list using two
     * pointers: 'previous' and 'current'. - When 'current.data.equals(o)',
     * unlinks 'current' from the list by setting 'previous.next =
     * current.next'. - Only the first occurrence is removed. Runs in O(n) time,
     * where n is the size of the list.
     */
    public boolean remove(Object o) {
        if (front == null) {
            return false;
        }
        if (front.data.equals(o)) {
            front = front.next;
            size--;
            return true;
        }
        Node previous = front;
        Node current = front.next;

        while (current != null) {
            if (current.data.equals(o)) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;

    }

    /**
     * Removes all elements from this list that are also contained in the
     * specified list.
     *
     * @param otherList the list of elements to remove from this list.
     * @impNote - First, repeatedly removes matching nodes at the front of the
     * list, updating 'front' and decrementing 'size' for each removal. - Then
     * traverses the rest of the list using two pointers: 'previous' and
     * 'current'. and decrements 'size'. - If current.data is not in otherList,
     * moves 'previous' forward. - This ensures all occurrences of elements in
     * otherList are removed in a single pass. - Runs in O(n * m) time, where n
     * is the size of this list and m is otherList.size().
     */
    public void removeAll(LinkedList<E> otherList) {
        while (front != null && otherList.contains(front.data)) {
            front = front.next;
            size--;
        }

        Node previous = front;
        Node current = front.next;
        while (current != null) {
            if (otherList.contains(current.data)) {
                previous.next = current.next;
                size--;
                current = previous.next;
            } else {
                previous = current;
                current = current.next;
            }

        }
    }

    /**
     * Retains only the elements in this list that are also contained in the
     * specified list. In other words, removes all elements from this list that
     * are NOT present in the given list.
     *
     * @param list the list containing elements to be retained.
     *
     * @implNote - Traverses this list and removes any node whose data is not
     * contained in list. - The front of the list is handled as a special case,
     * since removing the front node requires updating the 'front' reference. -
     * After the front is valid, the list is traversed using two pointers:
     * 'previous' (the last retained node) and 'current' (the node being
     * examined). - When current.data is NOT found in list, the node is unlinked
     * by setting previous.next = current.next. - When a node is removed, only
     * 'current' is advanced; 'previous' remains unchanged to preserve the
     * chain. - When a node is retained, both pointers advance normally. - The
     * 'size' field is decremented for each removed element. - Runs in O(n * m)
     * time, where n is this.size() and m is list.size(), due to repeated calls
     * to list.contains().
     */
    public void retainAll(LinkedList<E> list) {
        while (front != null && !list.contains(front.data)) {
            front = front.next;
            size--;
        }
        Node previous = front;
        Node current = front.next;

        while (current != null) {
            if (!list.contains(current.data)) {
                previous.next = current.next;
                size--;
                current = previous.next;
            } else {
                previous = current;
                current = current.next;
            }
        }

    }

    /**
     * Equals method. Compares the specified object with this list for equality.
     * Returns true if and only if the specified object is also a list, both
     * lists have the same size, and all corresponding pairs of elements in the
     * two lists are equal and in the same order.
     *
     * @param o the object to be compared for equality with this list.
     * @return true if the specified object is equal to this list.
     *
     * @implNote If o is not an instance of List, return false. if the sizes of
     * the two lists differ, return false. Traverse both lists simultaneously,
     * comparing each pair of elements using equals(). If any pair of elements
     * is not equal, return false immediately. If all elements match and
     * traversal completes, return true. Runs in O(n) time were n is the size of
     * the list.
     */
    public boolean equals(Object o) {

        if (!(o instanceof List)) {
            return false;
        }
        // Casting to usable type.
        List<?> other = (List<?>) o;

        if (this.size != other.size()) {
            return false;
        }

        Node current = front;
        int index = 0;

        while (current != null) {
            if (!Objects.equals(current.data, other.get(index))) {
                return false;

            }
            current = current.next;
            index++;
        }

        return true;
    }

    /**
     * Appends all elements from the given list to the end of this list.
     *
     * @param other the LinkedList whose elements are added to the end of this
     * list.
     *
     * @implNote - If this list is empty, simply set 'front' to point to the
     * first node of 'other'. - Otherwise, traverse to the last node of this
     * list and link its 'next' reference to the first node of 'other'. - The
     * 'size' of this list is incremented by the number of elements in 'other'.
     * - This operation runs in O(n + m) time, where n is this.size() and m is
     * other.size(). - No new nodes are created; the method reuses the existing
     * nodes from 'other'.
     */
    public void addAll(int index, List<E> list) {

        if (list.isEmpty()) {
            return;
        }
        if (index == 0) {
            Node newFront = new Node(list.get(0));
            Node lastNew = newFront;

            for (int i = 1; i < list.size(); i++) {
                lastNew.next = new Node(list.get(i));
                lastNew = lastNew.next;
            }

            lastNew.next = front;
            front = newFront;
            size += list.size();
            return;

        }

        Node current = front;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        Node after = current.next;
        Node firstNew = new Node(list.get(0));
        Node lastNew = firstNew;

        for (int i = 1; i < list.size(); i++) {
            lastNew.next = new Node(list.get(i));
            lastNew = lastNew.next;
        }

        current.next = firstNew;
        lastNew.next = after;

        size += list.size();

    }

}
