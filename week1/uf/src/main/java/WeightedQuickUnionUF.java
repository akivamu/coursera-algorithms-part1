/**
 * An improvement for QuickUnion approach: minimize tree height.
 * When union, choose smaller tree (less objects) to attach to another tree root.
 */
class WeightedQuickUnionUF extends QuickUnionUF {

    private final int[] sizes;

    WeightedQuickUnionUF(int n) {
        super(n);

        sizes = new int[n];
        for (int i = 0; i < n; i++) {
            sizes[i] = i;
        }
    }

    /**
     * Calculate and Attach smaller tree to larger tree.
     * findRootIndex's cost become logN
     *
     * @param p object in set
     * @param q object in set
     */
    @Override
    void union(int p, int q) {
        int rootPIndex = findRootIndex(p);
        int rootQIndex = findRootIndex(q);

        // Only attach if p and q not in the same tree
        if (rootPIndex != rootQIndex) {
            int smallerIndex = sizes[rootPIndex] < sizes[rootQIndex] ? rootPIndex : rootQIndex;
            int biggerIndex = sizes[rootPIndex] < sizes[rootQIndex] ? rootQIndex : rootPIndex;

            ids[smallerIndex] = biggerIndex;
            sizes[biggerIndex] += sizes[smallerIndex];
        }
    }
}
