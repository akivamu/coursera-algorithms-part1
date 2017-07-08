/**
 * An abstract class defines operations in Union-Find problem
 */
abstract class UF {

    /**
     * Number of objects in set
     */
    protected final int n;

    UF(int n) {
        this.n = n;
    }

    /**
     * Connect 2 objects p and q
     *
     * @param p object in set
     * @param q object in set
     */
    abstract void union(int p, int q);

    /**
     * Is there a path connecting the two objects?
     *
     * @param p object in set
     * @param q object in set
     * @return true/false
     */
    abstract boolean isConnected(int p, int q);
}
