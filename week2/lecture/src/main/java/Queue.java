import java.util.NoSuchElementException;

public interface Queue<Item> {

    void enqueue(Item item) throws IllegalArgumentException;

    Item dequeue() throws NoSuchElementException;

    int size();

    boolean isEmpty();
}
