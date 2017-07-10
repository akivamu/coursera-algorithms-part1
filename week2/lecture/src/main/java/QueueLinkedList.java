import java.util.NoSuchElementException;

public class QueueLinkedList<Item> implements Queue<Item> {
    /**
     * Space cost: (Inner class Node overhead) * N
     */
    private Node head;
    private Node tail;
    private int n;

    /**
     * Time cost: constant
     */
    @Override
    public void enqueue(Item item) throws IllegalArgumentException {
        if (item == null) throw new IllegalArgumentException();

        Node newTail = new Node(item);

        if (tail == null) head = newTail;
        else tail.next = newTail;

        tail = newTail;
        n++;
    }

    /**
     * Time cost: constant
     */
    @Override
    public Item dequeue() throws NoSuchElementException {
        if (head == null) throw new NoSuchElementException();

        Item item = head.item;
        head = head.next;

        if (head == null) tail = null;
        n--;
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

    private class Node {
        private Item item;
        private Node next;

        Node(Item item) {
            this.item = item;
        }
    }
}
