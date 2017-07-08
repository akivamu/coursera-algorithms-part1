/**
 * Implementation of UF problem using Quick Find algorithm (eager approach).
 * Cons:
 * - Union operation is too expensive
 */
class QuickFindUF extends UF {

    /**
     * Internal structure to store ID of objects
     */
    private final int[] ids;

    /**
     * Construct and initialize ID array.
     * Cost: N
     *
     * @param n number of objects
     */
    QuickFindUF(int n) {
        super(n);

        // Initialize ID array
        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    /**
     * Merge components containing p and q.
     * Change all entries whose id equal ids[p] to ids[q].
     * Cost: N
     *
     * @param p object in set
     * @param q object in set
     */
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

    /**
     * Check if p and q have the same id.
     * Cost: 1
     *
     * @param p object in set
     * @param q object in set
     * @return true/false
     */
    @Override
    boolean isConnected(int p, int q) {
        return ids[p] == ids[q];
    }
}
