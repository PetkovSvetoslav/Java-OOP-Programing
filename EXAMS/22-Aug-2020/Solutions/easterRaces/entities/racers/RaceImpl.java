package easterRaces.entities.racers;

import easterRaces.common.ExceptionMessages;
import easterRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RaceImpl implements Race {
    private String name;
    private int laps;
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.drivers = new ArrayList<>();
    }

    private void setName(String name) {
        validateName(name);
        this.name = name;
    }

    private void setLaps(int laps) {
        validateLaps(laps);
        this.laps = laps;
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

    private void validateLaps(int laps) {
        final int MIN_LAPS = 1;
        if (laps < MIN_LAPS) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_LAPS);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return Collections.unmodifiableCollection(this.drivers);
    }

    @Override
    public void addDriver(Driver driver) {
        validateDriver(driver);
        this.drivers.add(driver);
    }

    private void validateDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException(ExceptionMessages.DRIVER_INVALID);
        }

        if (!driver.getCanParticipate()) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.DRIVER_NOT_PARTICIPATE,
                            driver.getName())
            );
        }

        if (this.drivers.contains(driver)) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.DRIVER_ALREADY_ADDED,
                            driver.getName(), this.name)
            );
        }
    }
}
