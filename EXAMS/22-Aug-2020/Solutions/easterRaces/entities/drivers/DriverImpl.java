package easterRaces.entities.drivers;

import easterRaces.common.ExceptionMessages;
import easterRaces.entities.cars.Car;

public class DriverImpl implements Driver {
    private String name;
    private Car car;
    private int numberOfWins;
    private boolean canParticipate;

    public DriverImpl(String name) {
        this.setName(name);
        this.numberOfWins = 0;
        this.canParticipate = false;
    }

    private void setName(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        final int MIN_LENGTH = 5;
        if (name == null || name.trim().length() < MIN_LENGTH) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.INVALID_NAME,
                            name, MIN_LENGTH)
            );
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Car getCar() {
        return this.car;
    }

    @Override
    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    @Override
    public void addCar(Car car) {
        validateCar(car);
        this.car = car;
        this.canParticipate = true;
    }

    private void validateCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException(ExceptionMessages.CAR_INVALID);
        }
    }

    @Override
    public void winRace() {
        this.numberOfWins++;
    }

    @Override
    public boolean getCanParticipate() {
        return this.canParticipate;
    }
}
