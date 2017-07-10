import java.util.NoSuchElementException;

public class StackLinkedList<Item> implements Stack<Item> {

    /**
     * Space cost: (Inner class Node overhead) * N
     */
    private Node head;
    private int size;

    /**
     * Time cost: constant
     */
    @Override
    public void push(Item item) throws IllegalArgumentException {
        if (item == null) throw new IllegalArgumentException();

        Node newHead = new Node(item);
        newHead.next = head;
        head = newHead;
        size++;
    }

    /**
     * Time cost: constant
     */
    @Override
    public Item pop() throws NoSuchElementException {
        if (head == null) throw new NoSuchElementException();

        Item item = head.item;
        head = head.next;
        size--;

        return item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        private Item item;
        private Node next;

        Node(Item item) {
            this.item = item;
        }
    }
}
