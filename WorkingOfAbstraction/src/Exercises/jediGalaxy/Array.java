package Exercises.jediGalaxy;

import java.util.Arrays;

public class Array {
    public static int[] parseArray(String line, String separator) {
        return Arrays.stream(line.split(separator))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static int[][] generateMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        int counter = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = counter++;
            }
        }
        return matrix;
    }

    public static boolean isInBounds(int row, int col, int[][] matrix) {
        return 0 <= row && row < matrix.length
                && 0 <= col && col < matrix[row].length;
    }

}
