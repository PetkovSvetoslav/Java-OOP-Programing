package Exercises.jediGalaxy;

public class Sith {
    public void move(int row, int col, int[][] matrix) {
        while (row >= 0 && col >= 0) {
            if (Array.isInBounds(row, col,matrix)) {
                matrix[row][col] = 0;
            }
            row--;
            col--;
        }
    }
}
