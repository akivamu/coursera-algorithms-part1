package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        public Item item;
        public Node next;
        public Node prev;

        public Node(Item item) {
            this.item = item;
        }
    }

    private Node first;
    private Node last;
    private int size = 0;

    public Deque() {
    }

    public boolean isEmpty() {
        return first == null && last == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node node = new Node(item);

        if (first != null) {
            node.next = first;
            first.prev = node;
        }
        first = node;

        if (last == null) last = first;
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node node = new Node(item);

        if (last != null) {
            node.prev = last;
            last.next = node;
        }
        last = node;

        if (first == null) first = last;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = first.item;

        first = first.next;
        if (first != null) first.prev = null;

        size--;
        if (size == 0) last = null;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = last.item;

        last = last.prev;
        if (last != null) last.next = null;

        size--;
        if (size == 0) first = null;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator(first);
    }

    private class DequeIterator implements Iterator<Item> {
        private Node next;

        public DequeIterator(Node first) {
            next = first;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Item next() {
            if (next == null) throw new NoSuchElementException();
            Item item = next.item;
            next = next.next;

            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);

        for (Object item : deque) {
            System.out.println(item);
        }

        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.size());
    }
}
