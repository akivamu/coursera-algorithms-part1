import java.util.NoSuchElementException;

public class StackLinkedList<Item> implements Stack<Item> {

    private Node head;
    private int size;

    @Override
    public void push(Item item) throws IllegalArgumentException {
        if (item == null) throw new IllegalArgumentException();

        Node newHead = new Node(item);
        newHead.next = head;
        head = newHead;
        size++;
    }

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
