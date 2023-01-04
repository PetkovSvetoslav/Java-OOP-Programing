package easterRaces.entities.cars;

public class SportsCar extends BaseCar {
    private static final double DEFAULT_CUBIC_CENTIMETERS = 3_000;
    private static final int MINIMUM_HORSEPOWER = 250;
    private static final int MAXIMUM_HORSEPOWER = 450;

    public SportsCar(String model, int horsePower) {
        super(model, MINIMUM_HORSEPOWER, MAXIMUM_HORSEPOWER,
                horsePower, DEFAULT_CUBIC_CENTIMETERS);
    }
}
