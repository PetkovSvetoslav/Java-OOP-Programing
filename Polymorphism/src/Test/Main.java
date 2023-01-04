package Test;

public class Main {
    public static void main(String[] args) {

        Shape shape = new Shape() {
            @Override
            public double calculateArea() {
                return 0;
            }

            @Override
            public double calculatePerimeter() {
                return 0;
            }
        };
    }
}


