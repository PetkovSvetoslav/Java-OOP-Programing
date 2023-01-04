package Lab;

import Lab.IOUtils.ConsoleReader;
import Lab.IOUtils.InputParser;
import Lab.geometry.GeometricFactory;
import Lab.geometry.Point2D;
import Lab.geometry.Rectangle;

public class PointInRectangle {
    public static void main(String[] args) {
        ConsoleReader reader = new ConsoleReader();

        int[] rectangleInfo = InputParser.parseArray(reader.readLine(), " ");

        Rectangle rectangle = GeometricFactory.createRectangle(rectangleInfo);

        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            int[] pointCoordinates = InputParser.parseArray(reader.readLine(), " ");
            Point2D point = GeometricFactory.createPoint2D(pointCoordinates);

            System.out.println(rectangle.contains(point));
        }
    }
}
