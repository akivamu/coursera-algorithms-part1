import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final boolean[][] sites;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufTopSite;
    private int openSites;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n should be larger than 0");

        this.n = n;
        openSites = 0;
        sites = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufTopSite = new WeightedQuickUnionUF(n * n + 2);
    }

    private int xyTo1D(int row, int col) {
        return 2 + (row - 1) * n + (col - 1);
    }

    private void validateIndex(int row, int col) {
        if (row < 1 || n < row) throw new IllegalArgumentException("Invalid row number");
        if (col < 1 || n < col) throw new IllegalArgumentException("Invalid col number");
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validateIndex(row, col);
        if (sites[row - 1][col - 1]) return;

        // Connect to virtual sites
        if (row == 1) {
            uf.union(0, xyTo1D(row, col));
            ufTopSite.union(0, xyTo1D(row, col));
        }
        if (row == n) {
            uf.union(1, xyTo1D(row, col));
        }

        // Connect to top site
        if (row > 1 && sites[row - 1 - 1][col - 1]) {
            uf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            ufTopSite.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }

        // Connect to bottom site
        if (row < n && sites[row][col - 1]) {
            uf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            ufTopSite.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }

        // Connect to left site
        if (col > 1 && sites[row - 1][col - 1 - 1]) {
            uf.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            ufTopSite.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }

        // Connect to right site
        if (col < n && sites[row - 1][col]) {
            uf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            ufTopSite.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }

        sites[row - 1][col - 1] = true;
        openSites++;
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateIndex(row, col);
        return sites[row - 1][col - 1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validateIndex(row, col);
        return sites[row - 1][col - 1] && ufTopSite.connected(0, xyTo1D(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0, 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation percolation = new Percolation(n);
        while (!StdIn.isEmpty()) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            percolation.open(x, y);
        }

        StdOut.println(percolation.numberOfOpenSites() + " open sites");
        StdOut.println(percolation.percolates() ? "percolates" : "does not percolate");
    }
}
