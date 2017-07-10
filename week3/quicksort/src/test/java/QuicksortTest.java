import org.junit.Assert;
import org.junit.Test;

public class QuicksortTest {

    private int[] generateRandomArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;

        Util.shuffle(arr);
        return arr;
    }

    @Test
    public void test1() {
        int[] arr = new int[]{0, 1, 4, 2, 3};

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
        int[] arr = generateRandomArray(n);
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