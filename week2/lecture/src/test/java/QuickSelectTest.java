import org.junit.Assert;
import org.junit.Test;

public class QuickSelectTest {
    @Test
    public void testSimple() {
        QuickSort quickSort = new QuickSort();
        Assert.assertTrue(9 == (Integer) quickSort.findKthSmallestItem(new Integer[]{7, 4, 2, 1, 9, 5, 0, 3, 8, 6}, 9));
        Assert.assertTrue(1 == (Integer) quickSort.findKthSmallestItem(new Integer[]{1, 1, 1, 1}, 3));
    }

    @Test
    public void testRandom() {
        QuickSort quickSort = new QuickSort();
        runTestForThis(quickSort, 0, 10);
        runTestForThis(quickSort, 1, 10);
        runTestForThis(quickSort, 2, 10);
        runTestForThis(quickSort, 3, 10);
        runTestForThis(quickSort, 4, 10);
        runTestForThis(quickSort, 5, 10);
        runTestForThis(quickSort, 6, 10);
        runTestForThis(quickSort, 7, 10);
        runTestForThis(quickSort, 8, 10);
        runTestForThis(quickSort, 9, 10);
    }

    private void runTestForThis(QuickSort quickSort, int k, int n) {
        Integer[] arr = Util.generateRandomIntegerArray(n);
        System.out.print(k + ": ");
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
        Assert.assertTrue(k == (Integer) quickSort.findKthSmallestItem(arr, k));
    }
}
