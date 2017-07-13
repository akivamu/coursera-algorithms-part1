import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;

public class MaxPQTest {
    @Test
    public void testSimpleUnorderedMaxPQ() {
        PQ<Integer> maxPQ = new UnorderedMaxPQ<>();
        int M = 2;

        Integer[] stream = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        StdRandom.shuffle(stream);
        Util.printArray(stream);

        // Add to queue
        for (Integer item : stream) {
            maxPQ.insert(item);
            if (maxPQ.size() > M) maxPQ.del();
        }

        // Assert
        for (int i = 0; i < M; i++) {
            Assert.assertTrue(maxPQ.del() < M);
        }
    }

    @Test
    public void testRandom() {
        randomStreamAndSmallestMItems(new UnorderedMaxPQ<>(), 10);
        randomStreamAndSmallestMItems(new UnorderedMaxPQ<>(), 20);
        randomStreamAndSmallestMItems(new UnorderedMaxPQ<>(), 30);

        randomStreamAndSmallestMItems(new OrderedMaxPQ<>(), 10);
        randomStreamAndSmallestMItems(new OrderedMaxPQ<>(), 20);
        randomStreamAndSmallestMItems(new OrderedMaxPQ<>(), 30);
    }

    private void randomStreamAndSmallestMItems(PQ<Integer> maxPQ, int M) {
        // Make random stream, length = 2M
        Integer[] stream = Util.generateRandomIntegerArray(2 * M);
        Util.printArray(stream);

        // Add to queue
        for (int i = 0; i < stream.length; i++) {
            maxPQ.insert(stream[i]);
            if (maxPQ.size() > M) maxPQ.del();
        }

        // Assert
        System.out.print("Smallest " + M + " items: ");
        for (int i = 0; i < M; i++) {
            Integer item = maxPQ.del();
            System.out.print(item + " ");
            Assert.assertTrue(item < M);
        }
        System.out.println();
    }

}
