package christmasRaces.entities.races;

import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static christmasRaces.common.ExceptionMessages.*;

public class RaceImpl implements Race{
    private String name;
    private int laps;
    private List<Driver> drivers;

    public RaceImpl(String name, int laps) {
        setName(name);
       setLaps(laps);
        drivers = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5){
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.name = name;
    }

    public void setLaps(int laps) {
        if (laps < 1){
            throw new IllegalArgumentException(INVALID_NUMBER_OF_LAPS);
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLaps() {
        return laps;
    }

    @Override
    public List<Driver> getDrivers() {
        return this.drivers;
    }

    @Override
    public void addDriver(Driver driver) {
    if (driver == null){
        throw new IllegalArgumentException(DRIVER_INVALID);
    }else if (!driver.getCanParticipate()){
        throw new IllegalArgumentException(String.format(DRIVER_NOT_PARTICIPATE,driver.getName()));
    }else if (drivers.contains(driver)){
        throw new IllegalArgumentException(String.format(DRIVER_ALREADY_ADDED,driver.getName(),name));
    }
    drivers.add(driver);
    }
}
