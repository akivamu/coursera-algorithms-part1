package week1.uf;

/**
 * An improvement for QuickUnion approach: keep tree flat, by making descendants become children of root.
 * While finding root, change current object parent to grandparent.
 */
class PathCompressionQuickUnionUF extends QuickUnionUF {

    PathCompressionQuickUnionUF(int n) {
        super(n);
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
}
