import java.util.NoSuchElementException;

public class UnorderedMaxPQ<Item extends Comparable<Item>> extends PQ<Item> {
    private Item[] data = (Item[]) new Comparable[1];

    // O(1)
    @Override
    public void insert(Item item) {
        if (size == data.length) data = Util.resizeArray(data, size * 2);
        data[size++] = item;
    }

    // O(N)
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
}