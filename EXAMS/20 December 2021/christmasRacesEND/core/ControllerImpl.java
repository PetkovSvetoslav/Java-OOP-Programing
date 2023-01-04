package christmasRaces.core;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.common.OutputMessages;
import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static christmasRaces.common.ExceptionMessages.*;


public class ControllerImpl implements Controller {
    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
       if (driverRepository.getByName(driver) != null){
           throw new IllegalArgumentException(String.format(DRIVER_EXISTS,driver));
       }
       driverRepository.add(new DriverImpl(driver));
       return String.format(OutputMessages.DRIVER_CREATED,driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        if (carRepository.getByName(model) != null){
            throw new IllegalArgumentException(String.format(CAR_EXISTS,model));
        }
        switch (type){
           case "Muscle":
               carRepository.add(new MuscleCar(model,horsePower));
               return String.format(OutputMessages.CAR_CREATED,type+"Car",model);
            case "Sports":
                carRepository.add(new SportsCar(model,horsePower));
                return String.format(OutputMessages.CAR_CREATED,type+"Car",model);
        }
        return null;
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = driverRepository.getByName(driverName);
        if (driver == null){
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND,driverName));
        }
        Car car = carRepository.getByName(carModel);
        if (car == null){
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND,carModel));
        }
        driver.addCar(car);
        return String.format(OutputMessages.CAR_ADDED,driverName,carModel);

    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null){
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND,raceName));
        }
        Driver driver = driverRepository.getByName(driverName);
        if (driver == null){
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND,driverName));
        }
        race.addDriver(driver);
        return String.format(OutputMessages.DRIVER_ADDED,driverName,raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null){
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND,raceName));
        }
        Collection<Driver> raceDrivers = race.getDrivers();
        int neededDrivers = 3;
        if (raceDrivers.size() < 3){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID,raceName,neededDrivers));
        }
        int laps = race.getLaps();
        List<Driver> participants = raceDrivers.stream()
                .sorted((d1, d2) -> Double.compare(d2.getCar().calculateRacePoints(laps),
                        d1.getCar().calculateRacePoints(laps)))
                .limit(3)
                .collect(Collectors.toList());

        this.raceRepository.remove(race);
        Driver driver1 = participants.get(0);
        Driver driver2 = participants.get(1);
        Driver driver3 = participants.get(2);

        StringBuilder builder = new StringBuilder();
        builder.append(String.format(OutputMessages.DRIVER_FIRST_POSITION,driver1.getName(),raceName))
                .append(System.lineSeparator())
                .append(String.format(OutputMessages.DRIVER_SECOND_POSITION,driver2.getName(),raceName))
                .append(System.lineSeparator())
                .append(String.format(OutputMessages.DRIVER_THIRD_POSITION,driver3.getName(),raceName));

        return builder.toString();

    }

    @Override
    public String createRace(String name, int laps) {

        Race race = raceRepository.getByName(name);
        if (race != null){
            throw new IllegalArgumentException(String.format(RACE_EXISTS,name));
        }
        raceRepository.add(new RaceImpl(name,laps));
        return String.format(OutputMessages.RACE_CREATED,name);
    }
}
