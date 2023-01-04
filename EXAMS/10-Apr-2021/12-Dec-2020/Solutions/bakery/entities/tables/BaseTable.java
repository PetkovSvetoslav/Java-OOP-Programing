package bakery.entities.tables;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

import static bakery.common.ExceptionMessages.*;

public abstract class BaseTable implements Table {
    private Collection<BakedFood> foodOrders;
    private Collection<Drink> drinkOrders;
    private int tableNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    protected BaseTable(int tableNumber, int capacity, double pricePerPerson) {
        this.foodOrders = new ArrayList<>();
        this.drinkOrders = new ArrayList<>();

        this.tableNumber = tableNumber;
        this.setCapacity(capacity);
        this.pricePerPerson = pricePerPerson;
    }

    private void setCapacity(int capacity) {
        validateCapacity(capacity);
        this.capacity = capacity;
    }

    private void setNumberOfPeople(int numberOfPeople) {
        validateNumberOfPeople(numberOfPeople);
        this.numberOfPeople = numberOfPeople;
    }

    private void validateCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
    }

    private void validateNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
    }

    @Override
    public int getTableNumber() {
        return this.tableNumber;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double getPricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void reserve(int numberOfPeople) {
        if (!this.isReserved && this.capacity >= numberOfPeople) {
            this.setNumberOfPeople(numberOfPeople);
            this.price = numberOfPeople * this.pricePerPerson;
            this.isReserved = true;
        }
    }

    @Override
    public void orderFood(BakedFood food) {
        this.foodOrders.add(food);
    }

    @Override
    public void orderDrink(Drink drink) {
        this.drinkOrders.add(drink);
    }

    @Override
    public double getBill() {
        double bill = this.getPrice();

        bill += this.foodOrders.stream()
                .mapToDouble(BakedFood::getPrice)
                .sum();

        bill += this.drinkOrders.stream()
                .mapToDouble(Drink::getPrice)
                .sum();

        return bill;
    }

    @Override
    public void clear() {
        this.foodOrders.clear();
        this.drinkOrders.clear();
        this.price = 0;
        this.isReserved = false;
    }

    @Override
    public String getFreeTableInfo() {
        return String.format("Table: %d%n" +
                        "Type: %s%n" +
                        "Capacity: %d%n" +
                        "Price per Person: %.2f",
                this.getTableNumber(),
                this.getClass().getSimpleName(),
                this.getCapacity(),
                this.getPricePerPerson());
    }
}
