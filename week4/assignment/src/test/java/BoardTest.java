import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

    @Test
    public void testGoalBoard() {
        // Board 1
        Board board = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        });

        Assert.assertEquals(3, board.dimension());
        Assert.assertEquals(0, board.hamming());
        Assert.assertEquals(0, board.manhattan());
        Assert.assertTrue(board.isGoal());
        System.out.println(board);

        // Board 2
        board = new Board(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 0}
        });

        Assert.assertEquals(4, board.dimension());
        Assert.assertEquals(0, board.hamming());
        Assert.assertEquals(0, board.manhattan());
        Assert.assertTrue(board.isGoal());
        System.out.println(board);
    }

    @Test
    public void testUnfinishedBoard() {
        // Board 1
        Board board = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 8}
        });

        Assert.assertEquals(1, board.hamming());
        Assert.assertEquals(1, board.manhattan());
        Assert.assertFalse(board.isGoal());
        System.out.println(board);

        // Board 2
        board = new Board(new int[][]{
                {3, 2, 1},
                {7, 8, 5},
                {6, 0, 4}
        });

        Assert.assertEquals(7, board.hamming());
        Assert.assertEquals(13, board.manhattan());
        Assert.assertFalse(board.isGoal());
        System.out.println(board);

        // Board 3
        board = new Board(new int[][]{
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        });

        Assert.assertEquals(5, board.hamming());
        Assert.assertEquals(10, board.manhattan());
        Assert.assertFalse(board.isGoal());
        System.out.println(board);
    }

    @Test
    public void testCompareBoard() {
        Board board1 = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 8}
        });

        Board board2 = new Board(new int[][]{
                {3, 2, 1},
                {7, 8, 5},
                {6, 0, 4}
        });

        Board board3 = new Board(new int[][]{
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        });

        Board board4 = new Board(new int[][]{
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        });

        Assert.assertTrue(board1.equals(board1));
        Assert.assertFalse(board1.equals(null));
        Assert.assertFalse(board1.equals(board2));
        Assert.assertFalse(board1.equals(board3));
        Assert.assertFalse(board2.equals(board3));
        Assert.assertTrue(board3.equals(board4));
    }

    @Test
    public void testTwinBoard() {
        Board board1 = new Board(new int[][]{
                {0, 2, 3},
                {4, 5, 6},
                {7, 1, 8}
        });

        Board twin = board1.twin();

        System.out.println(twin);
    }

    @Test
    public void testNeighborsBoard() {
        Board board = new Board(new int[][]{
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        });

        for (Board neighbor : board.neighbors()) {
            System.out.println(neighbor);
        }
    }
}
