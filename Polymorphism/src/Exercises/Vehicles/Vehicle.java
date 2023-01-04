package Exercises.Vehicles;

import java.text.DecimalFormat;

public abstract class Vehicle {
    private double fuelQuantity;
    private double litersPerKm;
    private final static DecimalFormat FORMATTER = new DecimalFormat("#.##");

    protected Vehicle(double fuelQuantity, double litersPerKm) {
        this.fuelQuantity = fuelQuantity;
        this.litersPerKm = litersPerKm;
    }

    public void refuel(double liters) {
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

    @Override
    public String toString() {
        return String.format(": %.2f", this.fuelQuantity);
    }
}
