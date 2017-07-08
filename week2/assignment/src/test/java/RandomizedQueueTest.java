import org.junit.Test;

public class RandomizedQueueTest {

    @Test
    public void test1() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(1);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(3);

        for (Integer item : queue) {
            System.out.println(item);
        }
    }
}
