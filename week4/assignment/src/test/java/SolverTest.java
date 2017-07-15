import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Assert;
import org.junit.Test;

public class SolverTest {
    private static final String FILE_PREFIX = "puzzle";
    private static final String FILE_POSTFIX = ".txt";

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

    @Test
    public void testSingleSolvableFile() {
        runSolvableFromFile("27");
    }

    @Test
    public void testSolvableFiles() {
        for (int i = 0; i <= 50; i++) {
            runSolvableFromFile(String.format("%02d", i));
        }
    }

    private void runSolvableFromFile(String T) {
        String fileName = FILE_PREFIX + T + FILE_POSTFIX;
        System.out.println("Testing solvable file: " + fileName);

        int moves = Integer.parseInt(T);

        // create initial board from file
        In in = new In(FILE_PREFIX + T + FILE_POSTFIX);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        Assert.assertTrue(solver.isSolvable());
        Assert.assertEquals(moves, solver.moves());
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }
}
