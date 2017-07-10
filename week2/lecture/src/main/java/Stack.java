import java.util.NoSuchElementException;

public interface Stack<Item> {

    void push(Item item) throws IllegalArgumentException;

    Item pop() throws NoSuchElementException;

    int size();

    boolean isEmpty();
}
