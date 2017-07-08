import org.junit.Test;

import java.io.IOException;

public class PercolationStatsTest {

    @Test
    public void testAll() throws IOException {
        testEach(200, 100);
        testEach(200, 100);
        testEach(2, 10000);
        testEach(2, 100000);
    }

    private void testEach(int n, int trials) {
        System.out.println("---- n = " + n + ", trials = " + trials + " ----");
        PercolationStats percolationStats = new PercolationStats(n, trials);
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}
