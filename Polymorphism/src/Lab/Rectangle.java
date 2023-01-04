package Lab;

public class Rectangle extends Shape {
    private final Double height;
    private final Double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public final Double getHeight() {
        return this.height;
    }

    public final Double getWidth() {
        return this.width;
    }

    @Override
    public Double calculatePerimeter() {
        return (this.height + this.width) * 2;
    }

    @Override
    public Double calculateArea() {
        return (this.height * this.width);
    }
}
