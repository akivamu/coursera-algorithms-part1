package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size = 0;
    private int maxIndex = 0;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void doubleArraySize() {
        Item[] newArr = (Item[]) new Object[size * 2];
        int count = 0;
        for (Item item : items) {
            if (item != null) newArr[count++] = item;
        }
        items = newArr;
        maxIndex = size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (size == items.length) doubleArraySize();

        items[maxIndex] = item;
        size++;
        maxIndex++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        while (true) {
            int index = StdRandom.uniform(maxIndex);
            Item item = items[index];

            if (item != null) {
                items[index] = null;
                size--;
                return item;
            }
        }
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        while (true) {
            int index = StdRandom.uniform(maxIndex);
            Item item = items[index];

            if (item != null) {
                return item;
            }
        }
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] data;
        private int curIndex = 0;

        RandomizedQueueIterator() {
            data = (Item[]) new Object[size];
            int count = 0;
            for (Item item : items) {
                if (item != null) data[count++] = item;
            }
            StdRandom.shuffle(data);
        }

        @Override
        public boolean hasNext() {
            return curIndex < data.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Item item = data[curIndex];
            curIndex++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);

        for (Integer item : queue) {
            System.out.println(item);
        }
    }
}
