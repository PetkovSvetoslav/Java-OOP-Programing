package Lab.geometry;

public class GeometricFactory {
    public static Rectangle createRectangle(int[] pointsInfo) {
        return new Rectangle(createPoint2D(pointsInfo[0], pointsInfo[1])
                , createPoint2D(pointsInfo[2], pointsInfo[3]));
    }

    public static Point2D createPoint2D(int x, int y) {
        return new Point2D(x, y);
    }

    public static Point2D createPoint2D(int[] pointInfo) {
        if (pointInfo.length != 2) {
            throw new IllegalStateException("Point can be created only by X and Y. " +
                    "There should be only two coordinates.");
        }
        return new Point2D(pointInfo[0], pointInfo[1]);
    }
}
