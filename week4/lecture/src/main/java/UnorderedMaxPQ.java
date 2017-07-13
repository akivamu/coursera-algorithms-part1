import java.util.NoSuchElementException;

public class UnorderedMaxPQ<Item extends Comparable<Item>> implements PQ<Item> {
    private Item[] data = (Item[]) new Comparable[1];
    private int size = 0;

    @Override
    public void insert(Item item) {
        if (size == data.length) data = Util.resizeArray(data, size * 2);
        data[size++] = item;
    }

    @Override
    public Item del() {
        if (isEmpty()) throw new NoSuchElementException();

        int maxIndex = 0;
        for (int i = 1; i < size; i++) {
            if (Util.less(data[maxIndex], data[i])) maxIndex = i;
        }

        Util.exch(data, maxIndex, size - 1);
        Item item = data[size - 1];
        data[size - 1] = null;
        size--;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}