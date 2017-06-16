package week1.uf;

class QuickFindUF extends UF {
    private final int[] ids;

    QuickFindUF(int n) {
        super(n);

        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    @Override
    void union(int p, int q) {
        int pid = ids[p];
        int qid = ids[q];

        for (int i = 0; i < n; i++) {
            if (ids[i] == pid) {
                ids[i] = qid;
            }
        }
    }

    @Override
    boolean isConnected(int p, int q) {
        return ids[p] == ids[q];
    }
}
