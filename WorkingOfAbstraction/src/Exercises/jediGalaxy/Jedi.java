package Exercises.jediGalaxy;

public class Jedi {
    public long move(int row, int col, int[][] matrix) {
        long collectedPower = 0L;
        while (row >= 0 && col < matrix[1].length) {
            if (Array.isInBounds(row, col, matrix)) {
                collectedPower += matrix[row][col];
            }
            row--;
            col++;
        }
        return collectedPower;
    }
}
