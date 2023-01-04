package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
    //TODO: Test Garage class
    Garage garage;
    Car car;
    Car car2;
    Car car3;

    @Before
public void  setUp(){
    this.garage = new Garage();
    car = new Car("ss", 100, 1000);
    car2 = new Car("saa", 200, 2000);
    car3 = new Car("sbb", 300, 3000);
}
    @Test(expected = IllegalArgumentException.class)
    public void testAddCarNull(){
       garage.addCar(null);
    }
    @Test
    public void testAddCar(){
        garage.addCar(car);
        Assert.assertEquals(1, garage.getCount());
    }
    @Test
    public void testGetCars(){
        garage.addCar(car);
        List<Car> cars = garage.getCars();
        Assert.assertEquals(1, garage.getCount());
        Assert.assertEquals(car.getBrand(), cars.get(0).getBrand());
    }
    @Test
    public void testFastestCars(){
        garage.addCar(car);
        garage.addCar(car2);
        garage.addCar(car3);
        List<Car> cars = garage.findAllCarsWithMaxSpeedAbove(250);
        Assert.assertEquals(car3.getBrand(), cars.get(0).getBrand());

    }
    @Test
    public void testexpensiveCars(){
        garage.addCar(car);
        garage.addCar(car2);
        garage.addCar(car3);
        Car cars = garage.getTheMostExpensiveCar();
        Assert.assertEquals(car3.getBrand(), cars.getBrand());

    }
    @Test
    public void testAllCarsNyBrand(){
        garage.addCar(car);
        garage.addCar(car3);
        garage.addCar(car3);
        List<Car> cars = garage.findAllCarsByBrand(car3.getBrand());
        Assert.assertEquals(2, cars.size());
    }


}