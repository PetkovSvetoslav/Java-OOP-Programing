package Exercises.WildFarm.food;

public class Meat extends Food {
    public Meat(Integer quantity) {
        super(quantity);
    }

    @Override
    public String getType() {
        return "Meat";
    }
}
