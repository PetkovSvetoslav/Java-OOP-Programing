package garage;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GarageTests {
    private Garage garage;

    @Before
    public void setUp() {
        this.garage = new Garage();
    }

    @Test(expected = IllegalArgumentException.class)
    public void Add_Null_ThrowsException() {
        this.garage.addCar(null);
    }

    @Test
    public void Add_OneCar_IncreaseTheNumberOfCars() {
        Car car = new Car("Ford", 400, 20_000.00);

        assertEquals(0, this.garage.getCount());

        this.garage.addCar(car);

        assertEquals(1, this.garage.getCount());
    }

    @Test
    public void Add_ThreeCars_ReturnsThemInTheCorrectOrder() {
        Car firstCar = createAndAddCar("Mitsubishi", 350, 16_000.99);
        Car secondCar = createAndAddCar("Mitsubishi", 69, 999.99);
        Car thirdCar = createAndAddCar("Toyota", 420, 13_666.99);

        List<Car> expectedCars = List.of(firstCar, secondCar, thirdCar);

        List<Car> garageCars = this.garage.getCars();

        assertThat(garageCars, is(expectedCars));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void Get_ModifyingTheResult_ThrowsException() {
        createAndAddCar("Ferrari", 448, 111_111.11);

        List<Car> cars = this.garage.getCars();
        cars.set(0, new Car("Trabant", 700, 9_999_999.99));
    }

    @Test
    public void FindAllCarsWithMaxSpeedAbove_CarsAtALowerOrEqualSpeedThanRequired_EmptyCollection() {
        final int MIN_SPEED = 100;

        createAndAddCar("Maserati", MIN_SPEED, 10_000.00);
        createAndAddCar("Bugatti", MIN_SPEED - 1, 20_000.00);
        createAndAddCar("BMW", MIN_SPEED - 2, 30_00.00);

        List<Car> cars = this.garage.findAllCarsWithMaxSpeedAbove(MIN_SPEED);

        assertTrue(cars.isEmpty());
    }

    @Test
    public void FindAllCarsWithMaxSpeedAbove_CarsAtAHigherSpeedThanTheRequired_ReturnsThemAsWeAddedThem() {
        final int MIN_SPEED = 100;

        Car firstAddedCar = createAndAddCar("Moskvich", MIN_SPEED + 2, 10.00);
        Car secondAddedCar = createAndAddCar("Mercedes", MIN_SPEED + 1, 30_00.00);
        Car thirdAddedCar = createAndAddCar("Pagani", MIN_SPEED + MIN_SPEED, 20.00);

        List<Car> expectedCars = List.of(firstAddedCar, secondAddedCar, thirdAddedCar);

        List<Car> carsWithMaxSpeedAbove = this.garage.findAllCarsWithMaxSpeedAbove(MIN_SPEED);

        assertThat(carsWithMaxSpeedAbove, is(expectedCars));
    }

    @Test
    public void GetTheMostExpensiveCar_EmptyGarage_ReturnsNull() {
        Car car = this.garage.getTheMostExpensiveCar();

        assertNull(car);
    }

    @Test
    public void GetTheMostExpensiveCar_ThreeAddedCars_ReturnsTheMostExpensive() {
        final double HIGHEST_PRICE = 100_000.00;

        createAndAddCar("Tesla", 100, HIGHEST_PRICE - 0.1);
        Car expected = createAndAddCar("Tesla", 100, HIGHEST_PRICE);
        createAndAddCar("Tesla", 100, HIGHEST_PRICE - 1);

        Car mostExpensiveCar = this.garage.getTheMostExpensiveCar();

        assertEquals(expected, mostExpensiveCar);
    }

    @Test
    public void FindAllCarsByBrand_DifferentBrandThanTheRequiredOne_ReturnsEmptyCollection() {
        final String BRAND = "Volkswagen";

        createAndAddCar(BRAND + "n", 100, 10_000.00);

        List<Car> carsByBrand = this.garage.findAllCarsByBrand(BRAND);

        assertTrue(carsByBrand.isEmpty());
    }

    @Test
    public void FindAllCarsByBrand_ThreeCarWithTheSameBrand_ReturnsThemAsWeAddedThem() {
        final String BRAND = "Volkswagen";

        Car firstCar = createAndAddCar(BRAND, 300, 10_000.00);
        Car secondCar = createAndAddCar(BRAND, 200, 10_000.00);
        Car thirdCar = createAndAddCar(BRAND, 400, 10_000.00);
        Car forthCar = createAndAddCar(BRAND, 100, 10_000.00);

        List<Car> carsByBrand = this.garage.findAllCarsByBrand(BRAND);

        List<Car> expectedCars = List.of(firstCar, secondCar, thirdCar, forthCar);

        assertThat(carsByBrand, is(expectedCars));
    }

    private Car createAndAddCar(String brand, int maxSpeed, double price) {
        Car car = new Car(brand, maxSpeed, price);
        this.garage.addCar(car);

        return car;
    }
}