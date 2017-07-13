import java.util.NoSuchElementException;

public class OrderedMaxPQ<Item extends Comparable<Item>> extends PQ<Item> {
    private Item[] data = (Item[]) new Comparable[1];

    // O(N)
    @Override
    public void insert(Item item) {
        if (size == data.length) data = Util.resizeArray(data, size * 2);

        data[size++] = item;

        if (!isEmpty()) {
            int curIndex = size - 1;
            while (curIndex > 0 && Util.less(data[curIndex], data[curIndex - 1])) {
                Util.exch(data, curIndex, curIndex - 1);
                curIndex--;
            }
        }
    }

    // O(1)
    @Override
    public Item del() {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = data[size - 1];
        data[size - 1] = null;
        size--;
        return item;
    }
}