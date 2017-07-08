import edu.princeton.cs.algs4.StdIn;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PercolationTest {

    @Test
    public void testAll() throws IOException {
        testEach("input1.txt", 1, true);
        testEach("input1-no.txt", 0, false);
        testEach("greeting57.txt", 2522, false);
        testEach("input10-no.txt", 55, false);
        testEach("input50.txt", 1412, true);
    }

    private void resyncStdIn() {
        try {
            Method m = StdIn.class.getDeclaredMethod("resync");
            m.setAccessible(true);
            m.invoke(null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void testEach(String fileName, int numberOfOpenSites, boolean percolates) throws IOException {
        System.setIn(getClass().getResourceAsStream(fileName));
        resyncStdIn();

        int n = StdIn.readInt();
        Percolation percolation = new Percolation(n);
        while (!StdIn.isEmpty()) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            percolation.open(x, y);
        }

        Assert.assertEquals(numberOfOpenSites, percolation.numberOfOpenSites());
        Assert.assertEquals(percolates, percolation.percolates());
    }
}
