import java.util.NoSuchElementException;

public class QueueArray<Item> implements Queue<Item> {
    /**
     * Space cost: (pointer size) * 2*N
     */
    private Item[] data = (Item[]) new Object[1];
    private int firstIndex, lastIndex;

    /**
     * Time cost: amortized constant
     */
    @Override
    public void enqueue(Item item) throws IllegalArgumentException {
        if (item == null) throw new IllegalArgumentException();

        if (data[lastIndex] != null) {
            if (lastIndex + 1 > data.length - 1) resize(data.length * 2);

            lastIndex++;
        }

        data[lastIndex] = item;
    }

    /**
     * Time cost: amortized constant
     */
    @Override
    public Item dequeue() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = data[firstIndex];
        data[firstIndex] = null;
        firstIndex++;

        if (size() > 0 && size() == data.length / 4) resize(data.length / 2);

        return item;
    }

    @Override
    public int size() {
        if (lastIndex == firstIndex) {
            return data[firstIndex] == null ? 0 : 1;
        } else {
            return lastIndex - firstIndex + 1;
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Time cost: N
     */
    private void resize(int newSize) {
        Item[] newArray = (Item[]) new Object[newSize > 1 ? newSize : 1];

        int size = size();

        for (int i = 0; i < size; i++) {
            newArray[i] = data[i + firstIndex];
        }
        data = newArray;
        firstIndex = 0;
        lastIndex = size - 1;
    }
}
