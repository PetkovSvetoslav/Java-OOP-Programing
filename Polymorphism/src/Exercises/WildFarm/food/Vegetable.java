package Exercises.WildFarm.food;

public class Vegetable extends Food {
    public Vegetable(Integer quantity) {
        super(quantity);
    }

    @Override
    public String getType() {
        return "Vegetable";
    }
}
