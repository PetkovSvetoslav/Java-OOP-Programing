package easterRaces.repositories;

import easterRaces.entities.cars.Car;
import easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CarRepository implements Repository<Car> {
    private Collection<Car> cars;

    public CarRepository() {
        this.cars = new ArrayList<>();
    }

    @Override
    public Car getByName(String model) {
        return this.cars
                .stream()
                .filter(d -> d.getModel().equals(model))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(this.cars);
    }

    @Override
    public void add(Car car) {
        this.cars.add(car);
    }

    @Override
    public boolean remove(Car car) {
        return this.cars.remove(car);
    }
}
