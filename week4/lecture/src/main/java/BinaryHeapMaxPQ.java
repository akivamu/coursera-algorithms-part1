import java.util.NoSuchElementException;

public class BinaryHeapMaxPQ<Item extends Comparable<Item>> extends PQ<Item> {
    Item[] data = (Item[]) new Comparable[1];

    @Override
    public void insert(Item item) {
        if (size == data.length) data = Util.resizeArray(data, size * 2);

        data[size] = item;
        swimUp(size);
        size++;
    }

    @Override
    public Item del() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = data[0];
        Util.exch(data, 0, size - 1);
        size--;
        sinkDown(0);

        return item;
    }

    private void swimUp(int index) {
        while (index > 0 && Util.less(data[index / 2], data[index])) {
            Util.exch(data, index / 2, index);
            index /= 2;
        }
    }

    private void sinkDown(int index) {
        while (2 * index <= size - 1) {

            int indexOfFirstChild = 2 * index;
            int swapIndex = indexOfFirstChild;

            // 2nd child
            if (indexOfFirstChild + 1 <= size - 1) {
                if (Util.less(data[indexOfFirstChild], data[indexOfFirstChild + 1])) {
                    swapIndex = indexOfFirstChild + 1;
                }
            }

            if (!Util.less(data[index], data[swapIndex])) break;
            Util.exch(data, index, swapIndex);
            index = swapIndex;
        }
    }
}
