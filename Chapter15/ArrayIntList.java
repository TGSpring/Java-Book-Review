
public class ArrayIntList {

    private int[] elementData; // list of integers
    private int size; // current number of elements in the list

    public static final int DEFAULT_CAPACITY = 100;

    // post: constructs an empty list of default capacity
    public ArrayIntList() {
        this(DEFAULT_CAPACITY);
    }

    // pre : capacity >= 0 (throws IllegalArgumentException if not)
    // post: constructs an empty list with the given capacity
    public ArrayIntList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        elementData = new int[capacity];
        size = 0;
    }

    // post: returns the current number of elements in the list
    public int size() {
        return size;
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: returns the integer at the given index in the list
    public int get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    // post: creates a comma-separated, bracketed version of the list
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + elementData[0];
            for (int i = 1; i < size; i++) {
                result += ", " + elementData[i];
            }
            result += "]";
            return result;
        }
    }

    // post : returns the position of the first occurrence of the given
    // value (-1 if not found)
    public int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * #### PROBLEM 1 ###### Find and return the last index at which the
     * specified integer appears, or -1 if absent.
     *
     * If the value occurs multiple times, the index of the last occurrence is
     * returned.
     *
     * @param value the integer value to search for.
     * @return the index of the last matching element, or -1 if the value is not
     * found.
     * @implNote Runs in O(n) time in the worst case by scanning the list end to
     * front.
     */
    public int lastIndexOf(int value) {
        for (int i = size - 1; i >= 0; i--) {
            if (elementData[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * ###### PROBLEM 2 ######
     *
     * Return the index of the last occurrence of the specified value in this
     * list.
     *
     * If the value occurs more than once, the highest index is returned.
     *
     * @param value the integer value to search for.
     * @return the index of the last matching element, or -1 if the value is not
     * found
     * @implNote Runs in O(n) time in the worst case by scanning the underlying
     * array from the end toward the front.
     */
    public int indexOfSubList(ArrayIntList other) {
        if (other == null) {
            return -1;
        }

        int n = this.size();
        int m = other.size();

        //Empty sublist.
        if (m == 0) {
            return 0;
        }
        //Empty main and sublist.
        if (m > n) {
            return -1;
        }

        //i is the candidate start index in the main list.
        //j is the offset inside the sublist.
        //i + j points at the element in the main list that should match sub.get(i).
        for (int i = 0; i <= n - m; i++) {
            boolean match = true;
            for (int j = 0; j < m; j++) {
                //Comparing element at  i + j with other's j.
                if (this.elementData[i + j] != other.get(j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return i;
            }
        }
        return -1;

    }

    /**
     * ###### PROBLEM 3 ######
     *
     * Replaces all occurrences of a specified integer value in the list with
     * another value.
     *
     * For example, if a variable called list stores [11, -7, 3, 42, 3, 0, 14,
     * 3], calling list.replaceAll(3, 999) will change the list to [11, -7, 999,
     * 42, 999, 0, 14, 999].
     *
     * @param oldVal the integer value to be replaced
     * @param newVal the integer value to replace oldVal with
     * @implNote Runs in O(n) time by scanning the list once from front to back.
     * Modifies the list in place; does not return a new list.
     */
    public void replaceAll(int oldVal, int newVal) {
        for (int i = 0; i < size; i++) {
            if (this.elementData[i] == oldVal) {
                set(i, newVal);
            }
        }
    }

    /*
     * ####### PROBLEM 10 ######### 
     * Returns the length of the longest consecutive non-decreasing sequence 
     * of integers in the list. 
     *
     * For example, if the list stores [1, 3, 5, 2, 9, 7, -3, 0, 42, 308, 17], 
     * the longest sorted sequence is [ -3, 0, 42, 308 ] with length 4.
     *
     * @return the length of the longest non-decreasing consecutive sequence.
     *         Returns 0 if the list is empty.
     * @implNote Runs in O(n) time by scanning the list once from front to back.
     *          Does not modify the underlying list.
     */
    public int longestSortedSequence() {
        if (isEmpty()) {
            return 0;
        }

        int maxLength = 1;
        int curLength = 1;

        for (int i = 1; i < size; i++) {
            if (elementData[i] >= elementData[i - 1]) {
                curLength++;
            } else {
                curLength = 1;
            }
            if (curLength > maxLength) {
                maxLength = curLength;
            }
        }

        return maxLength;
    }

    // post: returns true if list is empty, false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    // post: returns true if the given value is contained in the list,
    // false otherwise
    public boolean contains(int value) {
        return indexOf(value) >= 0;
    }

    // pre : size() < capacity (throws IllegalStateException if not)
    // post: appends the given value to the end of the list
    public void add(int value) {
        checkCapacity(size + 1);
        elementData[size] = value;
        size++;
    }

    // pre : size() < capacity (throws IllegalStateException if not) &&
    // 0 <= index <= size() (throws IndexOutOfBoundsException if not)
    // post: inserts the given value at the given index, shifting subsequent
    // values right
    public void add(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
        checkCapacity(size + 1);
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: removes value at the given index, shifting subsequent values left
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: replaces the integer at the given index with the given value
    public void set(int index, int value) {
        checkIndex(index);
        elementData[index] = value;
    }

    // post: list is empty
    public void clear() {
        size = 0;
    }

    // pre: size() + other.size() <= capacity (throws IllegalStateException
    // if not)
    // post: appends all values in the given list to the end of this list
    public void addAll(ArrayIntList other) {
        checkCapacity(size + other.size);
        for (int i = 0; i < other.size; i++) {
            add(other.elementData[i]);
        }
    }

    // post: checks that the underlying array has the given capacity,
    // throwing an IllegalStateException if it does not
    private void checkCapacity(int capacity) {
        if (capacity > elementData.length) {
            throw new IllegalStateException("would exceed list capacity");
        }
    }

    // post: throws an IndexOutOfBoundsException if the given index is
    // not a legal index of the current list
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }
}
