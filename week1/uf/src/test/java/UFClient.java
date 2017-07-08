import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

public class UFClient {
    private static final boolean VERBOSE = false;

    private class TestSpec {
        private class Operation {
            int p;
            int q;

            Operation(int p, int q) {
                this.p = p;
                this.q = q;
            }
        }

        private final int n;
        private final ArrayList<Operation> operations;

        TestSpec(String inputFileName) throws FileNotFoundException {
            System.out.println("Reading input from " + inputFileName);
            Scanner scanner = new Scanner(new File(getClass().getResource(inputFileName).getFile()));
            n = scanner.nextInt();
            operations = new ArrayList<>();

            while (scanner.hasNextInt()) {
                operations.add(new Operation(scanner.nextInt(), scanner.nextInt()));
            }

            scanner.close();
        }

        private void perform(Class clazz) {
            System.out.println("==== " + clazz.getSimpleName() + " ====");

            // Construct UF
            UF uf;
            try {
                Constructor constructor = clazz.getDeclaredConstructor(int.class);
                uf = (UF) constructor.newInstance(n);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
                return;
            }

            long startTime = System.currentTimeMillis();

            // Perform operations
            for (int i = 0; i < operations.size(); i++) {
                int p = operations.get(i).p;
                int q = operations.get(i).q;
                if (!uf.isConnected(p, q)) {
                    log("Op#" + i + ": " + p + " and " + q + " are not connected. Now merge their components.");
                    uf.union(p, q);
                } else {
                    log("Op#" + i + ": " + p + " and " + q + " are connected.");
                }
                Assert.assertTrue("Op#" + i + ": " + p + " and " + q + " should connected", uf.isConnected(p, q));
            }

            System.out.println("     Execute time: " + (System.currentTimeMillis() - startTime) + "ms");
        }
    }


    private void log(String msg) {
        if (VERBOSE) {
            System.out.println(msg);
        }
    }

    private void performAllAlgorithms(String inputFileName) throws FileNotFoundException {
        TestSpec testSpec = new TestSpec(inputFileName);
        testSpec.perform(WeightedQuickUnionWithPathCompressionUF.class);
        testSpec.perform(PathCompressionQuickUnionUF.class);
        testSpec.perform(WeightedQuickUnionUF.class);
        testSpec.perform(QuickUnionUF.class);
        testSpec.perform(QuickFindUF.class);
    }

    @Test
    public void testAllTiny() throws FileNotFoundException {
        performAllAlgorithms("tinyUF.txt");
    }

    @Test
    public void testAllMedium() throws FileNotFoundException {
        performAllAlgorithms("mediumUF.txt");
    }

    @Ignore
    @Test
    public void testAllLarge() throws FileNotFoundException {
        performAllAlgorithms("largeUF.txt");
    }
}
