import java.util.NoSuchElementException;

public interface PQ<Item> {
    public void insert(Item item);

    public Item del() throws NoSuchElementException;

    public boolean isEmpty();

    public int size();
}
