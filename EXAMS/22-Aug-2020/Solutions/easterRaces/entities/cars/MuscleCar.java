package easterRaces.entities.cars;

public class MuscleCar extends BaseCar {
    private static final double DEFAULT_CUBIC_CENTIMETERS = 5_000;
    private static final int MINIMUM_HORSEPOWER = 400;
    private static final int MAXIMUM_HORSEPOWER = 600;

    public MuscleCar(String model, int horsePower) {
        super(model, MINIMUM_HORSEPOWER, MAXIMUM_HORSEPOWER,
                horsePower, DEFAULT_CUBIC_CENTIMETERS);
    }
}
