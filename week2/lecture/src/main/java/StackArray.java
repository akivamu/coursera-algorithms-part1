import java.util.NoSuchElementException;

public class StackArray<Item> implements Stack<Item> {
    private Item[] data = (Item[]) new Object[1];
    private int n = 0;

    @Override
    public void push(Item item) throws IllegalArgumentException {
        if (item == null) throw new IllegalArgumentException();

        if (data.length == n) {
            resize(data.length * 2);
        }

        data[n] = item;
        n++;
    }

    @Override
    public Item pop() throws NoSuchElementException {
        if (n == 0) throw new NoSuchElementException();

        Item item = data[n - 1];
        data[n - 1] = null;
        n--;

        if (n > 0 && n == data.length / 4) {
            resize(data.length / 2);
        }

        return item;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    private void resize(int newSize) {
        Item[] newArray = (Item[]) new Object[newSize > 1 ? newSize : 1];
        for (int i = 0; i < n; i++) {
            newArray[i] = data[i];
        }
        data = newArray;
    }

}
