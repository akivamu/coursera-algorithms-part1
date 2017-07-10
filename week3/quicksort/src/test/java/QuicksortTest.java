import org.junit.Assert;
import org.junit.Test;

public class QuicksortTest {

    private Integer[] generateRandomArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;

        Util.shuffle(arr);

        Integer[] ret = new Integer[arr.length];
        for (int i = 0; i < n; i++) ret[i] = arr[i];

        return ret;
    }

    @Test
    public void test1() {
        Integer[] arr = new Integer[]{0, 1, 4, 2, 3};

        Quick.sort(arr);

        for (int i = 0; i < arr.length - 1; i++) {
            Assert.assertTrue(arr[i] + " - " + arr[i + 1], arr[i] <= arr[i + 1]);
        }
    }

    @Test
    public void testRandomization() {
        final int trials = 20;

        for (int i = 0; i < trials; i++) runRandom(10);
    }

    private void runRandom(int n) {
        Integer[] arr = generateRandomArray(n);
        for (int val : arr) {
            System.out.print(val + " ");
        }
        Quick.sort(arr);
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