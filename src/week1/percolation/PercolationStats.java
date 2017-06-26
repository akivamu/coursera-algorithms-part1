package week1.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int n;
    private final int trials;
    private final double[] x;
    private double mean;
    private double stddev;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) throw new IllegalArgumentException("n should be larger than 0");
        if (trials <= 0) throw new IllegalArgumentException("trials should be larger than 0");
        this.n = n;
        this.trials = trials;

        this.x = new double[trials];
        runPercolation();
    }

    private void runPercolation() {
        for (int t = 0; t < trials; t++) {
            Percolation percolation = new Percolation(n);
            int numberOfOpenSites = 0;
            while (!percolation.percolates()) {
                int[] randomBlockedSite = pickRandomBlockedSite(percolation);
                percolation.open(randomBlockedSite[0], randomBlockedSite[1]);
                numberOfOpenSites++;
            }
            x[t] = (double) numberOfOpenSites / (n * n);
        }
        mean = StdStats.mean(x);
        stddev = StdStats.stddev(x);
    }

    private int[] pickRandomBlockedSite(Percolation percolation) {
        int[] coords = new int[2];
        while (true) {
            int rand = StdRandom.uniform(n * n);
            coords[0] = rand / n + 1;
            coords[1] = rand % n + 1;

            if (!percolation.isOpen(coords[0], coords[1])) break;
        }
        return coords;
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trials);
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}
