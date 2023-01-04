package cats;

import org.junit.Assert;
import org.junit.Test;

public class HouseTests {

    //1. създаване на къща
    //1.1. невалидно capacity
    @Test(expected = IllegalArgumentException.class)
    public void testCreateHouseWithInvalidCapacity() {
        new House("House1", -4);
    }
    //1.2. невалидно име, което null
    @Test(expected = NullPointerException.class)
    public void testCreateHouseWithInvalidName() {
        new House(null, 5);
    }
    //1.3. невалидно име, което празно
    @Test(expected = NullPointerException.class)
    public void testCreateHouseWithInvalidNameEmpty() {
        new House("", 5);
    }
    //1.3. валидни стойности
    @Test
    public void testCreateHouse() {
        House house = new House("House1", 10);
        Assert.assertEquals("House1", house.getName());
        Assert.assertEquals(10, house.getCapacity());
    }

    //2. addCat
    //2.1. успешно добавяме котка
    @Test
    public void testAddCat() {
        House house = new House("House1", 10);
        Cat mike = new Cat("Mike");
        Assert.assertEquals(0, house.getCount());

        house.addCat(mike);
        Assert.assertEquals(1, house.getCount());
    }
    //2.2. доабвяме котка в пълна къща (няма място)
    @Test(expected = IllegalArgumentException.class)
    public void testAddCatThrowFilledHouse() {
        House house = new House("House1", 1);
        //къща, в която може да съхраним една котка
        Cat mike = new Cat("Mike");
        house.addCat(mike);
        Assert.assertEquals(1, house.getCount());

        //добавям втора котка
        Cat betty = new Cat("Betty");
        house.addCat(betty);
    }

    //3. removeCat
    //3.1. успешно премахваме котката
    @Test
    public void testRemoveCat() {
        House house = new House("House1", 10);
        Cat mike = new Cat("Mike");
        Cat betty = new Cat("Betty");
        house.addCat(mike);
        house.addCat(betty);
        //["Mike", "Betty"]
        Assert.assertEquals(2, house.getCount());

        house.removeCat("Betty");
        Assert.assertEquals(1, house.getCount());

        house.removeCat("Mike");
        Assert.assertEquals(0, house.getCount());
    }
    //3.2. нямаме такава котка в къщата
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingCat() {
        House house = new House("House1", 10);
        house.removeCat("Ivan");
    }

    //4. cat for sale
    //4.1. успешно да продадем котката -> гладна
    @Test
    public void testCatForSale () {
        House house = new House("House1", 10);
        Cat mike = new Cat("Mike"); //isHungry = true
        house.addCat(mike);

        Cat returnedCat = house.catForSale("Mike");
        Assert.assertFalse(returnedCat.isHungry());
    }
    //4.2. нямаме котка с даденото име
    @Test(expected = IllegalArgumentException.class)
    public void testSaleNonExistingCat() {
        House house = new House("House1", 10);
        house.catForSale("Ivan");
    }

    //5. statitics
    @Test
    public void testStatistics() {
        House house = new House("House1", 10);
        Cat mike = new Cat("Mike");
        Cat betty = new Cat("Betty");
        Cat john = new Cat("John");
        house.addCat(mike);
        house.addCat(betty);
        house.addCat(john);

        Assert.assertEquals("The cat Mike, Betty, John is in the house House1!", house.statistics());

    }



}
