package easterRaces.core;

import easterRaces.common.ExceptionMessages;
import easterRaces.common.OutputMessages;
import easterRaces.core.interfaces.Controller;
import easterRaces.entities.cars.Car;
import easterRaces.entities.cars.MuscleCar;
import easterRaces.entities.cars.SportsCar;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.racers.Race;
import easterRaces.entities.racers.RaceImpl;
import easterRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private final Repository<Driver> driverRepository;
    private final Repository<Car> carRepository;
    private final Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driverName) {
        Driver driver = this.driverRepository.getByName(driverName);

        if (driver != null) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.DRIVER_EXISTS,
                            driverName)
            );
        }

        driver = new DriverImpl(driverName);
        this.driverRepository.add(driver);

        return String.format(OutputMessages.DRIVER_CREATED,
                driverName);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car = this.carRepository.getByName(model);

        if (car != null) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.CAR_EXISTS,
                            model)
            );
        }

        switch (type) {
            case "Muscle":
                car = new MuscleCar(model, horsePower);
                break;
            case "Sports":
                car = new SportsCar(model, horsePower);
                break;
            default:
                throw new IllegalArgumentException(
                        "Non-existent car type: " + type + "!"
                );
        }

        this.carRepository.add(car);

        return String.format(OutputMessages.CAR_CREATED,
                car.getClass().getSimpleName(), model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = this.driverRepository.getByName(driverName);
        ensureThatTheDriverExist(driver, driverName);

        Car car = this.carRepository.getByName(carModel);
        ensureThatTheCarExist(car, carModel);

        driver.addCar(car);

        return String.format(OutputMessages.CAR_ADDED
                , driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = this.raceRepository.getByName(raceName);
        ensureThatTheRaceExist(race, raceName);

        Driver driver = this.driverRepository.getByName(driverName);
        ensureThatTheDriverExist(driver, driverName);

        race.addDriver(driver);

        return String.format(OutputMessages.DRIVER_ADDED,
                driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = this.raceRepository.getByName(raceName);
        ensureThatTheRaceExist(race, raceName);

        Collection<Driver> drivers = race.getDrivers();

        final int MIN_NUMBER_OF_DRIVERS = 3;

        if (drivers.size() < MIN_NUMBER_OF_DRIVERS) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.RACE_INVALID,
                            raceName, MIN_NUMBER_OF_DRIVERS)
            );
        }

        List<Driver> sortedDrivers = drivers.stream()
                .sorted((d1, d2) -> {
                    double p1 = d1.getCar().calculateRacePoints(race.getLaps());
                    double p2 = d2.getCar().calculateRacePoints(race.getLaps());

                    return Double.compare(p2, p1);
                })
                .collect(Collectors.toList());

        Driver first = sortedDrivers.get(0);
        Driver second = sortedDrivers.get(1);
        Driver third = sortedDrivers.get(2);

        StringBuilder out = new StringBuilder();

        out.append(String.format(
                OutputMessages.DRIVER_FIRST_POSITION,
                first.getName(), race.getName()
        ))
                .append(System.lineSeparator());

        out.append(String.format(
                OutputMessages.DRIVER_SECOND_POSITION,
                second.getName(), race.getName()
        ))
                .append(System.lineSeparator());

        out.append(String.format(
                OutputMessages.DRIVER_THIRD_POSITION,
                third.getName(), race.getName()
        ));

        return out.toString();
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = this.raceRepository.getByName(name);

        if (race != null) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.RACE_EXISTS,
                            name)
            );
        }

        race = new RaceImpl(name, laps);
        this.raceRepository.add(race);

        return String.format(OutputMessages.RACE_CREATED,
                name);
    }

    private void ensureThatTheCarExist(Car car, String model) {
        if (car == null) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.CAR_NOT_FOUND,
                            model)
            );
        }
    }

    private void ensureThatTheDriverExist(Driver driver, String name) {
        if (driver == null) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.DRIVER_NOT_FOUND,
                            name)
            );
        }
    }

    private void ensureThatTheRaceExist(Race race, String name) {
        if (race == null) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.RACE_NOT_FOUND,
                            name)
            );
        }
    }
}
