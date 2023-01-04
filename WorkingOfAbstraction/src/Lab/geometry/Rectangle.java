package Lab.geometry;

public class Rectangle {
    private final Point2D bottomLeft;
    private final Point2D topRight;

    public Rectangle(Point2D bottomLeft, Point2D topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    public boolean contains(Point2D point) {
        return point.isGreaterOrEqualByX(bottomLeft)
                && point.isLessOrEqualByX(topRight)
                && point.isGreaterOrEqualByY(bottomLeft)
                && point.isLessOrEqualByY(topRight);
    }
}
