import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int[][] blocks;

    public Board(int[][] blocks) {
        this.blocks = cloneMatrix(blocks);
    }

    public int dimension() {
        return blocks.length;
    }

    public int hamming() {
        int count = 0;
        for (int i = 0; i < dimension() * dimension() - 1; i++) {
            int[] correctRowCol = convert1DTo2D(i);
            if (blocks[correctRowCol[0]][correctRowCol[1]] != i + 1) count++;
        }

        return count;
    }

    public int manhattan() {
        int count = 0;
        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++) {
                if (blocks[row][col] == 0) continue;
                int[] correctRowCol = convert1DTo2D(blocks[row][col] - 1);

                count += Math.abs(row - correctRowCol[0]);
                count += Math.abs(col - correctRowCol[1]);
            }
        }
        return count;
    }

    private int[] convert1DTo2D(int index) {
        int[] rowCol = new int[2];

        rowCol[0] = index / dimension();
        rowCol[1] = index % dimension();
        return rowCol;
    }

    public boolean isGoal() {
        return manhattan() == 0;
    }

    public Board twin() {
        Board twin = new Board(cloneMatrix(blocks));
        int rowA = -1, colA = -1;
        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++) {
                if (blocks[row][col] != 0) {
                    if (rowA != -1) {
                        twin.blocks[row][col] = blocks[rowA][colA];
                        twin.blocks[rowA][colA] = blocks[row][col];
                        return twin;
                    } else {
                        rowA = row;
                        colA = col;
                    }
                }
            }
        }
        return twin;
    }

    private static int[][] cloneMatrix(int[][] src) {
        int length = src.length;
        int[][] target = new int[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }

    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;

        if (that.dimension() != this.dimension()) return false;

        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++) {
                if (that.blocks[row][col] != this.blocks[row][col]) return false;
            }
        }
        return true;
    }

    private void swapBlocks(int[][] matrix, int rowA, int colA, int rowB, int colB) {
        int tmp = matrix[rowA][colA];
        matrix[rowA][colA] = matrix[rowB][colB];
        matrix[rowB][colB] = tmp;
    }

    private int[][] cloneAndSwapBlocks(int[][] matrix, int rowA, int colA, int rowB, int colB) {
        int[][] clonedMatrix = cloneMatrix(matrix);
        swapBlocks(clonedMatrix, rowA, colA, rowB, colB);
        return clonedMatrix;
    }

    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();

        // Find blank coord
        int blankRow = -1, blankCol = -1;
        for (int row = 0; blankRow == -1 && row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++) {
                if (blocks[row][col] == 0) {
                    blankRow = row;
                    blankCol = col;
                    break;
                }
            }
        }

        // TOP
        if (blankRow - 1 >= 0) {
            neighbors.add(new Board(cloneAndSwapBlocks(blocks, blankRow, blankCol, blankRow - 1, blankCol)));
        }

        // BOTTOM
        if (blankRow + 1 < dimension()) {
            neighbors.add(new Board(cloneAndSwapBlocks(blocks, blankRow, blankCol, blankRow + 1, blankCol)));
        }

        // RIGHT
        if (blankCol + 1 < dimension()) {
            neighbors.add(new Board(cloneAndSwapBlocks(blocks, blankRow, blankCol, blankRow, blankCol + 1)));
        }

        // LEFT
        if (blankCol - 1 >= 0) {
            neighbors.add(new Board(cloneAndSwapBlocks(blocks, blankRow, blankCol, blankRow, blankCol - 1)));
        }

        return neighbors;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int n = dimension();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}
