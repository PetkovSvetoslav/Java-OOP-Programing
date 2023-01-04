package easterRaces.entities.cars;

import easterRaces.common.ExceptionMessages;

public abstract class BaseCar implements Car {
    private String model;
    private int horsePower;
    private double cubicCentimeters;

    private final Integer minHorsePower;
    private final Integer maxHorsePower;

    protected BaseCar(String model, int minHorsePower, int maxHorsePower,
                      int horsePower, double cubicCentimeters) {
        this.setModel(model);
        this.minHorsePower = minHorsePower;
        this.maxHorsePower = maxHorsePower;
        this.setHorsePower(horsePower);
        this.setCubicCentimeters(cubicCentimeters);
    }

    private void setModel(String model) {
        validateModel(model);
        this.model = model;
    }

    private void setHorsePower(int horsePower) {
        validateHorsePower(horsePower);
        this.horsePower = horsePower;
    }

    private void setCubicCentimeters(double cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }

    private void validateModel(String model) {
        final int MIN_LENGTH = 4;
        if (model == null || model.trim().length() < MIN_LENGTH) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.INVALID_MODEL,
                            model, MIN_LENGTH)
            );
        }
    }

    private void validateHorsePower(int horsePower) {
        if (this.minHorsePower > horsePower || horsePower > this.maxHorsePower) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.INVALID_HORSE_POWER
                            , horsePower)
            );
        }
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return this.cubicCentimeters / this.horsePower * laps;
    }
}
