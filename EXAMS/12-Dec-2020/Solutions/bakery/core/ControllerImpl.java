package bakery.core;

import bakery.common.ExceptionMessages;
import bakery.common.OutputMessages;
import bakery.core.interfaces.Controller;
import bakery.entities.bakedFoods.Bread;
import bakery.entities.bakedFoods.Cake;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.Tea;
import bakery.entities.drinks.Water;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.InsideTable;
import bakery.entities.tables.OutsideTable;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.DrinkRepository;
import bakery.repositories.interfaces.FoodRepository;
import bakery.repositories.interfaces.TableRepository;

import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private final FoodRepository<BakedFood> foodRepository;
    private final DrinkRepository<Drink> drinkRepository;
    private final TableRepository<Table> tableRepository;

    private double totalIncome;

    public ControllerImpl(FoodRepository<BakedFood> foodRepository, DrinkRepository<Drink> drinkRepository, TableRepository<Table> tableRepository) {
        this.foodRepository = foodRepository;
        this.drinkRepository = drinkRepository;
        this.tableRepository = tableRepository;
        this.totalIncome = 0;
    }

    @Override
    public String addFood(String type, String name, double price) {
        BakedFood bakedFood = this.foodRepository.getByName(name);
        if (bakedFood == null) {
            bakedFood = createBakedFood(type, name, price);
            this.foodRepository.add(bakedFood);
            return String.format(OutputMessages.FOOD_ADDED,
                    name, type);
        } else {

            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST,
                            type, name)
            );
        }
    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {
        Drink drink = this.drinkRepository.getByNameAndBrand(name, brand);
        if (drink == null) {
            drink = createDrink(type, name, portion, brand);
            this.drinkRepository.add(drink);
            return String.format(OutputMessages.DRINK_ADDED,
                    name, brand);
        } else {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST,
                            type, name)
            );
        }
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = this.tableRepository.getByNumber(tableNumber);
        if (table == null) {
            table = crateTable(type, tableNumber, capacity);
            this.tableRepository.add(table);
            return String.format(OutputMessages.TABLE_ADDED,
                    tableNumber);
        } else {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.TABLE_EXIST,
                            tableNumber)
            );
        }
    }

    @Override
    public String reserveTable(int numberOfPeople) {
        for (Table table : this.tableRepository.getAll()) {
            if (!table.isReserved() && table.getCapacity() >= numberOfPeople) {
                table.reserve(numberOfPeople);
                return String.format(OutputMessages.TABLE_RESERVED,
                        table.getTableNumber(),
                        numberOfPeople);
            }
        }
        return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE,
                numberOfPeople);
    }

    @Override
    public String orderFood(int tableNumber, String foodName) {
        Table table = this.tableRepository.getByNumber(tableNumber);
        if (table == null || !table.isReserved()) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER,
                    tableNumber);
        }

        BakedFood food = this.foodRepository.getByName(foodName);
        if (food == null) {
            return String.format(OutputMessages.NONE_EXISTENT_FOOD,
                    foodName);
        }

        table.orderFood(food);

        return String.format(OutputMessages.FOOD_ORDER_SUCCESSFUL,
                tableNumber,
                foodName);
    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {
        Table table = this.tableRepository.getByNumber(tableNumber);
        if (table == null || !table.isReserved()) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER,
                    tableNumber);
        }

        Drink drink = this.drinkRepository.getByNameAndBrand(drinkName, drinkBrand);
        if (drink == null) {
            return String.format(OutputMessages.NON_EXISTENT_DRINK,
                    drinkName,
                    drinkBrand);
        }

        table.orderDrink(drink);

        return String.format(OutputMessages.DRINK_ORDER_SUCCESSFUL,
                tableNumber,
                drinkName,
                drinkBrand);
    }

    @Override
    public String leaveTable(int tableNumber) {
        Table table = this.tableRepository.getByNumber(tableNumber);
        double bill = table.getBill();
        this.totalIncome += bill;
        table.clear();

        return String.format(OutputMessages.BILL,
                tableNumber,
                bill);
    }

    @Override
    public String getFreeTablesInfo() {
        return this.tableRepository.getAll()
                .stream()
                .filter(t -> !t.isReserved())
                .map(Table::getFreeTableInfo)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String getTotalIncome() {
        return String.format(OutputMessages.TOTAL_INCOME,
                this.totalIncome);
    }

    private BakedFood createBakedFood(String type, String name, double price) {
        switch (type) {
            case "Bread":
                return new Bread(name, price);
            case "Cake":
                return new Cake(name, price);
            default:
                return null;
        }
    }

    private Drink createDrink(String type, String name, int portion, String brand) {
        switch (type) {
            case "Water":
                return new Water(name, portion, brand);
            case "Tea":
                return new Tea(name, portion, brand);
            default:
                return null;
        }
    }

    private Table crateTable(String type, int tableNumber, int capacity) {
        switch (type) {
            case "InsideTable":
                return new InsideTable(tableNumber, capacity);
            case "OutsideTable":
                return new OutsideTable(tableNumber, capacity);
            default:
                return null;
        }
    }
}
