package Exercises.VehicleExtension;

public class Car extends Vehicle {
    private final static double AIR_CONDITIONER_CONSUMPTION = 0.9;

    protected Car(double fuelQuantity, double litersPerKm, int consumption) {
        super(fuelQuantity, litersPerKm + AIR_CONDITIONER_CONSUMPTION, consumption);
    }

    public static Car parseCar(String[] data) {
        double fuelQuantity = Double.parseDouble(data[1]);
        double litersPerKm = Double.parseDouble(data[2]);
        int consumption = Integer.parseInt(data[3]);

        return new Car(fuelQuantity, litersPerKm, consumption);
    }

    @Override
    public String drive(double distanceInKm) {
        return "Car" + super.drive(distanceInKm);
    }

    @Override
    public String toString() {
        return "Car" + super.toString();
    }
}
