package Lab.geometry;

public class Point2D {
    private final int x;
    private final int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isGreaterOrEqualByX(Point2D other) {
        return this.x >= other.x;
    }

    public boolean isLessOrEqualByX(Point2D other) {
        return this.x <= other.x;
    }

    public boolean isGreaterOrEqualByY(Point2D other) {
        return this.y >= other.y;
    }

    public boolean isLessOrEqualByY(Point2D other) {
        return this.y <= other.y;
    }
}
