package week1.uf;

/**
 * Implementation of UF problem using Quick Union algorithm (lazy approach).
 * Cons:
 * - Find operation is too expensive
 */
class QuickUnionUF extends UF {

    /**
     * Internal structure to store parent ID of one object. Making it become a tree structure.
     */
    protected final int[] ids;

    /**
     * Construct and initialize ID array.
     * Cost: N
     *
     * @param n number of objects
     */
    QuickUnionUF(int n) {
        super(n);

        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    /**
     * Helper to find root parent of one object.
     * Cost: N
     *
     * @param p
     * @return
     */
    protected int findRootIndex(int p) {
        int index = ids[p];
        while (ids[index] != index) {
            index = ids[index];
        }
        return index;
    }

    /**
     * Merge two components, attach root of p as child of q's root.
     * Cost: N
     *
     * @param p object in set
     * @param q object in set
     */
    @Override
    void union(int p, int q) {
        int rootPIndex = findRootIndex(p);
        int rootQIndex = findRootIndex(q);
        ids[rootPIndex] = rootQIndex;
    }

    /**
     * Check if p and q have the same root
     * Cost: N
     *
     * @param p object in set
     * @param q object in set
     * @return
     */
    @Override
    boolean isConnected(int p, int q) {
        return ids[findRootIndex(p)] == ids[findRootIndex(q)];
    }
}
