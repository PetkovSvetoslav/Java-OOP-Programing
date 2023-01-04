package Exercises.PizzaCalories;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    public static Topping parseTopping(String line) {
        String[] tokens = line.split("\\s+");
        String toppingType = tokens[1];
        double weight = Double.parseDouble(tokens[2]);

        return new Topping(toppingType, weight);
    }

    private void setToppingType(String toppingType) {
        if (!Ingredients.TOPPING_TYPES.containsKey(toppingType)) {
            throw new IllegalArgumentException
                    ("Cannot place " + toppingType + " on top of your pizza.");
        }

        this.toppingType = toppingType;
    }

    private void setWeight(double weight) {
        if (1 > weight || weight > 50) {
            throw new IllegalArgumentException
                    (this.toppingType + " weight should be in the range [1..50].");
        }

        this.weight = weight;
    }

    public double calculateCalories() {
        return (2 * this.weight) * Ingredients.TOPPING_TYPES.get(this.toppingType);
    }
}
