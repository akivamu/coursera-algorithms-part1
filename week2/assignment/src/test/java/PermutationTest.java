import org.junit.Test;

public class PermutationTest {

    @Test
    public void testAll() {
        testEach(3, "distinct.txt");
        testEach(3, "distinct.txt");
        testEach(5, "mediumTale.txt");
    }

    private void testEach(int k, String fileName) {
        System.setIn(getClass().getResourceAsStream(fileName));
        Util.resyncStdIn();
        Permutation.main(new String[]{String.valueOf(k)});
    }
}
