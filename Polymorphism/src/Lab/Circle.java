package Lab;

import Lab.Shape;

public class Circle extends Shape {
    private final Double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public final Double getRadius() {
        return this.radius;
    }

    @Override
    public Double calculatePerimeter() {
        return 2 * this.radius * Math.PI;
    }

    @Override
    public Double calculateArea() {
        return Math.PI * this.radius * this.radius;
    }
}
