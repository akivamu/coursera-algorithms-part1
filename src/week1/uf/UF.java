package week1.uf;

abstract class UF {
    protected final int n;

    UF(int n) {
        this.n = n;
    }

    abstract void union(int p, int q);

    abstract boolean isConnected(int p, int q);
}
