package week1.uf;

/**
 * Best improvement for QuickUnion approach: combine WeightedQuickUnion and PathCompressionQuickUnion.
 */
class WeightedQuickUnionWithPathCompressionUF extends QuickUnionUF {
    private final int[] sizes;

    WeightedQuickUnionWithPathCompressionUF(int n) {
        super(n);

        sizes = new int[n];
        for (int i = 0; i < n; i++) {
            sizes[i] = i;
        }
    }

    @Override
    protected int findRootIndex(int p) {
        int index = ids[p];
        while (ids[index] != index) {
            int parent = ids[index];
            int grandParent = ids[parent];
            ids[index] = grandParent;
            index = ids[index];
        }
        return index;
    }

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
