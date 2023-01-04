package christmasRaces.repositories.interfaces;

import christmasRaces.entities.drivers.Driver;

import java.util.*;

public class DriverRepository implements Repository<Driver> {
    private Map<String, Driver> drivers;

    public DriverRepository() {
        this.drivers = new LinkedHashMap<>();
    }

    @Override
    public Driver getByName(String name) {
        return drivers.get(name);
    }

    @Override
    public Collection<Driver> getAll() {
        return Collections.unmodifiableCollection(drivers.values());
    }

    @Override
    public void add(Driver model) {
        drivers.put(model.getName(), model);
    }

    @Override
    public boolean remove(Driver model) {
       if (drivers.remove(model.getName())==null){
           return true;
       }
        return false;
    }
}
