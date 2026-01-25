
import java.util.Iterator;

public abstract class AbstractList<E> implements Iterable<E> {

    // Abstract methods(structure dependent)
    public abstract int size();

    public abstract E get(int index);

    public abstract void add(int index, E value);

    public abstract E remove(int index);

    // Concrete methods(shared, structure agnostic).
    // Checks if the list is empty.
    public boolean isEmpty() {
        return size() == 0;
    }

    // Checks if the list contains a value.
    public boolean contains(E value) {
        for (E item : this) {
            if (item.equals(value)) {
                return true;
            }
        }
        return false;
    }

    // Returns a string representation of the list.
    // I understand using SB is slightly cheating, I am at the point I just don't care.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Concrete classes must provide their own iterator.
    @Override
    public abstract Iterator<E> iterator();
}
