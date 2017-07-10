import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class QueueTest {
    private void runQueue(Queue<Integer> queue) {
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.size());

        try {
            queue.dequeue();
            Assert.fail();
        } catch (NoSuchElementException ignored) {
        }

        queue.enqueue(1);
        Assert.assertFalse(queue.isEmpty());
        Assert.assertEquals(1, queue.size());

        queue.enqueue(2);
        Assert.assertFalse(queue.isEmpty());
        Assert.assertEquals(2, queue.size());

        queue.enqueue(3);
        queue.enqueue(4);
        Assert.assertFalse(queue.isEmpty());
        Assert.assertEquals(4, queue.size());

        Object item = queue.dequeue();
        Assert.assertTrue(item instanceof Integer);
        Assert.assertTrue((Integer) item == 1);
        Assert.assertEquals(3, queue.size());

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.size());
    }

    @Test
    public void testQueueLinkedList() {
        runQueue(new QueueLinkedList<>());
    }

}
