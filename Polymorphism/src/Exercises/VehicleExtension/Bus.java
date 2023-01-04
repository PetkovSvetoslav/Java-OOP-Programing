package Exercises.VehicleExtension;

public class Bus extends Vehicle {
    private final static double AIR_CONDITIONER_CONSUMPTION = 1.4;

    protected Bus(double fuelQuantity, double litersPerKm, int capacity) {
        super(fuelQuantity, litersPerKm, capacity);
    }

    public static Bus parseBus(String[] data) {
        double fuelQuantity = Double.parseDouble(data[1]);
        double litersPerKm = Double.parseDouble(data[2]);
        int capacity = Integer.parseInt(data[3]);

        return new Bus(fuelQuantity, litersPerKm, capacity);
    }

    public String driveWithPassengers(double distanceInKm) {
        return driveWithIncreasedConsumption(distanceInKm, AIR_CONDITIONER_CONSUMPTION);
    }

    @Override
    public String drive(double distanceInKm) {
        return "Bus" + super.drive(distanceInKm);
    }

    @Override
    public String toString() {
        return "Bus" + super.toString();
    }
}
