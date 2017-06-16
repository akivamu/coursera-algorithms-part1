package week1.uf;

class QuickUnionUF extends UF {
    private final int[] ids;

    QuickUnionUF(int n) {
        super(n);

        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    private int findRootIndex(int p) {
        int index = ids[p];
        while (ids[index] != index) {
            index = ids[index];
        }
        return index;
    }

    @Override
    void union(int p, int q) {
        int rootPIndex = findRootIndex(p);
        int rootQIndex = findRootIndex(q);
        ids[rootPIndex] = rootQIndex;
    }

    @Override
    boolean isConnected(int p, int q) {
        return ids[findRootIndex(p)] == ids[findRootIndex(q)];
    }
}
