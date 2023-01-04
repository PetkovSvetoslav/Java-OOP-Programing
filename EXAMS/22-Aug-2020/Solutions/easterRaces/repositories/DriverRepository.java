package easterRaces.repositories;

import easterRaces.entities.drivers.Driver;
import easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DriverRepository implements Repository<Driver> {
    private Collection<Driver> drivers;

    public DriverRepository() {
        this.drivers = new ArrayList<>();
    }

    @Override
    public Driver getByName(String name) {
        return this.drivers
                .stream()
                .filter(d -> d.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Driver> getAll() {
        return Collections.unmodifiableCollection(this.drivers);
    }

    @Override
    public void add(Driver driver) {
        this.drivers.add(driver);
    }

    @Override
    public boolean remove(Driver driver) {
        return this.drivers.remove(driver);
    }
}
