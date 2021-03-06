import org.junit.Assert;
import org.junit.Test;

public class SortTest {
    @Test
    public void testSimple() {
        simpleTest(new SelectionSort());
        simpleTest(new InsertionSort());
        simpleTest(new BubbleSort());
        simpleTest(new ShellSort());
        simpleTest(new MergeSort());
        simpleTest(new BottomUpMergeSort());
        simpleTest(new QuickSort());
        simpleTest(new HeapSort());
    }

    @Test
    public void testRandom() {
        final int trials = 2000;

        for (int i = 0; i < trials; i++) runTestForThis(new SelectionSort(), 10);
        for (int i = 0; i < trials; i++) runTestForThis(new InsertionSort(), 10);
        for (int i = 0; i < trials; i++) runTestForThis(new ShellSort(), 10);
        for (int i = 0; i < trials; i++) runTestForThis(new MergeSort(), 10);
        for (int i = 0; i < trials; i++) runTestForThis(new QuickSort(), 10);
        for (int i = 0; i < trials; i++) runTestForThis(new HeapSort(), 10);
    }

    private void simpleTest(Sort sort) {
        Integer[] arr = new Integer[]{1, 0, 4, 2, 3};
        sort.sort(arr);
        Assert.assertTrue(Util.isSorted(arr));

        arr = new Integer[]{1, 1, 1, 1, 1};
        sort.sort(arr);
        Assert.assertTrue(Util.isSorted(arr));

        arr = new Integer[]{3, 4, 2, 1, 3};
        sort.sort(arr);
        Assert.assertTrue(Util.isSorted(arr));
    }

    private void runTestForThis(Sort sort, int n) {
        Integer[] arr = Util.generateRandomIntegerArray(n);
        for (int val : arr) {
            System.out.print(val + " ");
        }
        sort.sort(arr);
        System.out.print("-> ");
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();

        for (int i = 0; i < arr.length - 1; i++) {
            Assert.assertTrue(arr[i] <= arr[i + 1]);
        }
    }
}
