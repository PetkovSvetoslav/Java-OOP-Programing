package Exercises.jediGalaxy;

public class Galaxy {
    private int[][] matrix;
    private Sith sith;
    private Jedi jedi;

    public Galaxy(int[][] matrix) {
        this.matrix = matrix;
        this.sith = new Sith();
        this.jedi = new Jedi();
    }

    public void moveSith(int row, int col) {
        this.sith.move(row, col, this.matrix);
    }

    public long moveJedi(int row, int col) {
        return this.jedi.move(row, col, this.matrix);
    }
}
