import org.junit.Assert;
import org.junit.Test;

public class BSTTest {
    @Test
    public void testSimple() {
        BST<Integer, Integer> bst = new BST<>();

        bst.put(1, 1);
        Assert.assertEquals(1, bst.size());
        bst.put(2, 2);
        Assert.assertEquals(2, bst.size());
        bst.put(3, 3);
        Assert.assertEquals(3, bst.size());
        bst.put(4, 4);
        Assert.assertEquals(4, bst.size());
        bst.put(5, 5);
        Assert.assertEquals(5, bst.size());
        bst.put(6, 6);
        Assert.assertEquals(6, bst.size());
        bst.put(7, 7);
        Assert.assertEquals(7, bst.size());
        bst.put(8, 8);
        Assert.assertEquals(8, bst.size());

        Assert.assertTrue(1 == bst.min());
        Assert.assertTrue(8 == bst.max());
        Assert.assertTrue(5 == bst.get(5));
        Assert.assertTrue(4 == bst.floor(4));
        Assert.assertTrue(3 == bst.rank(4));
    }
}

