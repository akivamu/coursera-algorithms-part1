import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class SolverTest {
    private static final String FILE_PREFIX = "puzzle";
    private static final String FILE_POSTFIX = ".txt";

    @Test(expected = IllegalArgumentException.class)
    public void testCornerCase() {
        new Solver(null);
    }

    @Test
    public void test1() {
        int[][] initialBlocks = new int[][]{
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Board initialBoard = new Board(initialBlocks);

        Solver solver = new Solver(initialBoard);

        Assert.assertTrue(solver.isSolvable());
        Assert.assertEquals(4, solver.moves());

        for (Board board : solver.solution()) {
            System.out.println(board);
        }
    }

    @Ignore
    @Test
    public void testSingleSolvableFile() {
        runFile("puzzle27.txt", "27");
    }

    @Test
    public void testSingleUnsolvableFile() {
        runFile("puzzle2x2-unsolvable1.txt", null);
        runFile("puzzle2x2-unsolvable2.txt", null);
        runFile("puzzle2x2-unsolvable3.txt", null);
        runFile("puzzle3x3-unsolvable.txt", null);
        runFile("puzzle3x3-unsolvable1.txt", null);
        runFile("puzzle3x3-unsolvable2.txt", null);
        runFile("puzzle4x4-unsolvable.txt", null);
    }

    @Test
    public void testSolvableFiles() {
        for (int i = 0; i <= 26; i++) {
            String movesStr = String.format("%02d", i);
            runFile(FILE_PREFIX + movesStr + FILE_POSTFIX, movesStr);
        }
    }

    private void runFile(String fileName, String movesStr) {
        System.out.println("Testing " + (movesStr != null ? "solvable" : "unsolvable") + " file: " + fileName);

        // create initial board from file
        In in = new In(fileName);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        if (movesStr != null) {
            Assert.assertTrue(solver.isSolvable());
            Assert.assertEquals(Integer.parseInt(movesStr), solver.moves());

            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        } else {
            Assert.assertFalse(solver.isSolvable());
        }
    }
}
