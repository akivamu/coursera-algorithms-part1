import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class StackTest {
    @Test
    public void testStackLinkedList() {
        Stack<Integer> stack = new StackLinkedList<>();

        Assert.assertTrue(stack.isEmpty());
        Assert.assertEquals(0, stack.size());

        try {
            stack.pop();
            Assert.fail();
        } catch (NoSuchElementException ignored) {
        }

        stack.push(1);
        Assert.assertFalse(stack.isEmpty());
        Assert.assertEquals(1, stack.size());

        stack.push(2);
        Assert.assertFalse(stack.isEmpty());
        Assert.assertEquals(2, stack.size());

        stack.push(3);
        stack.push(4);
        Assert.assertFalse(stack.isEmpty());
        Assert.assertEquals(4, stack.size());

        Object item = stack.pop();
        Assert.assertTrue(item instanceof Integer);
        Assert.assertTrue((Integer) item == 4);
        Assert.assertEquals(3, stack.size());

        stack.pop();
        stack.pop();
        stack.pop();

        Assert.assertTrue(stack.isEmpty());
        Assert.assertEquals(0, stack.size());
    }
}
