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
        Assert.assertFalse(board.isGoal());
        System.out.println(board);

        // Board 2
        board = new Board(new int[][]{
                {3, 2, 1},
                {7, 8, 5},
                {6, 0, 4}
        });

        Assert.assertEquals(7, board.hamming());
        Assert.assertFalse(board.isGoal());
        System.out.println(board);

        // Board 3
        board = new Board(new int[][]{
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        });

        Assert.assertEquals(5, board.hamming());
        Assert.assertFalse(board.isGoal());
        System.out.println(board);
    }


}
