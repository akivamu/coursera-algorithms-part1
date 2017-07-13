import java.util.NoSuchElementException;

public abstract class PQ<Item extends Comparable<Item>> {
    protected int size = 0;

    public abstract void insert(Item item);

    public abstract Item del() throws NoSuchElementException;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
