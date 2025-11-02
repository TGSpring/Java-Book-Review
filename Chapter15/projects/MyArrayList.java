

/*My implementation of ArrayList<E> */
public class MyArrayList<E> {

    private Object[] elementData; //generic array storage.
    private int size; // number of elements in the list.

    private static final int DEFAULT_CAPACITY = 10;

    // constructs an empty list with DEFAULT_CAPACITY.
    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // Constructs an empty list with specified capacity.
    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must be >= 0: " + capacity);
        }
        this.elementData = new Object[capacity];
        this.size = 0;
    }

    // Returns the current number of elements.
    public int size() {
        return size;
    }

    // Checks if the list is empty.
    public boolean isEmpty() {
        return size == 0;
    }

    // Adds element to the end.
    public void add(E value) {
        ensureCapacity(size + 1);
        elementData[size++] = value;
    }

    // Ensures the array has enough capacity.
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            int newCapacity = Math.max(minCapacity, elementData.length * 2);
            Object[] newData = new Object[newCapacity];
            System.arraycopy(elementData, 0, newData, 0, size);
            elementData = newData;
        }
    }

    // Retrieves element at a given index.
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elementData[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }
}
