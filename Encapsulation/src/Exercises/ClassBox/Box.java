package Exercises.ClassBox;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    private void setLength(double length) {
        validateSide(length, "Length");
        this.length = length;
    }

    private void setWidth(double width) {
        validateSide(width, "Width");
        this.width = width;
    }

    private void setHeight(double height) {
        validateSide(height, "Height");
        this.height = height;
    }

    private void validateSide(double side, String messagePrefix) {
        if (side <= 0) {
            throw new IllegalArgumentException
                    (messagePrefix + " cannot be zero or negative.");
        }
    }

    public double calculateLateralSurfaceArea() {
        return (this.length + this.width) * this.height * 2;
    }

    public double calculateSurfaceArea() {
        return this.calculateLateralSurfaceArea() + (2 * this.length * this.width);
    }

    public double calculateVolume() {
        return (this.length * this.width) * this.height;
    }

    @Override
    public String toString() {
        return String.format("Surface Area - %.2f%n" +
                        "Lateral Surface Area - %.2f%n" +
                        "Volume - %.2f"
                , this.calculateSurfaceArea()
                , this.calculateLateralSurfaceArea()
                , this.calculateVolume());
    }
}
