package Exercises.VehicleExtension;

import java.text.DecimalFormat;

public abstract class Vehicle {
    private final static DecimalFormat FORMATTER = new DecimalFormat("#.##");

    private double fuelQuantity;
    private double litersPerKm;
    private int capacity;

    protected Vehicle(double fuelQuantity, double litersPerKm, int capacity) {
        this.fuelQuantity = fuelQuantity;
        this.litersPerKm = litersPerKm;
        this.capacity = capacity;
    }

    public void refuel(double liters) {
        validateLitersOfFuel(liters);
        this.fuelQuantity += liters;
    }

    public String drive(double distanceInKm) {
        double neededFuel = this.litersPerKm * distanceInKm;

        String result;
        if (this.fuelQuantity >= neededFuel) {
            this.fuelQuantity -= neededFuel;

            result = String.format(" travelled %s km", FORMATTER.format(distanceInKm));
        } else {
            result = " needs refueling";
        }

        return result;
    }

    protected String driveWithIncreasedConsumption(double distance, double incrementInKm) {
        this.litersPerKm += incrementInKm;
        String result = this.drive(distance);
        this.litersPerKm -= incrementInKm;

        return result;
    }

    protected void validateLitersOfFuel(double liters) {
        if (liters <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        } else if (liters + this.fuelQuantity > this.capacity) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
    }

    @Override
    public String toString() {
        return String.format(": %.2f", this.fuelQuantity);
    }
}
