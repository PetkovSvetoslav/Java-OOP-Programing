package Test;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * Math.PI * this.radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * this.radius * Math.PI;
    }
}
