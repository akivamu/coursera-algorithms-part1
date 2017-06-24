package week1.uf;

import org.junit.Assert;
import org.junit.Test;

public class UFClient {

    private int[][] operations = {
            {4, 3},
            {3, 8},
            {6, 5},
            {9, 4},
            {2, 1},
            {8, 9},
            {5, 0},
            {7, 2},
            {6, 1},
            {1, 0},
            {6, 7}
    };

    private void helper(UF uf) {
        for (int i = 0; i < operations.length; i++) {
            int p = operations[i][0];
            int q = operations[i][1];
            if (!uf.isConnected(p, q)) {
                System.out.println("Op#" + i + ": " + p + " and " + q + " are not connected. Now merge their components.");
                uf.union(p, q);
            } else {
                System.out.println("Op#" + i + ": " + p + " and " + q + " are connected.");
            }
            Assert.assertTrue("Op#" + i + ": " + p + " and " + q + " should connected", uf.isConnected(p, q));
        }
    }

    @Test
    public void testQuickFind() {
        helper(new QuickFindUF(10));
    }

    @Test
    public void testQuickUnion() {
        helper(new QuickUnionUF(10));
    }

    @Test
    public void testWeightedQuickUnion() {
        helper(new WeightedQuickUnionUF(10));
    }
}
