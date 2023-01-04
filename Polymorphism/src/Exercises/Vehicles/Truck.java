package Exercises.Vehicles;

public class Truck extends Vehicle {
    private final static double AIR_CONDITIONER_CONSUMPTION = 1.6;

    protected Truck(double fuelQuantity, double litersPerKm) {
        super(fuelQuantity, litersPerKm + AIR_CONDITIONER_CONSUMPTION);
    }

    public static Truck parseTruck(String[] data) {
        double fuelQuantity = Double.parseDouble(data[1]);
        double litersPerKm = Double.parseDouble(data[2]);

        return new Truck(fuelQuantity, litersPerKm);
    }

    @Override
    public void refuel(double liters) {
        super.refuel(liters * 0.95);
    }

    @Override
    public String drive(double distanceInKm) {
        return "Truck" + super.drive(distanceInKm);
    }

    @Override
    public String toString() {
        return "Truck" + super.toString();
    }
}
