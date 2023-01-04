package Exercises.PizzaCalories;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    public static Dough parseDough(String line) {
        String[] tokens = line.split("\\s+");
        String flourType = tokens[1];
        String bakingTechnique = tokens[2];
        double weight = Double.parseDouble(tokens[3]);

        return new Dough(flourType, bakingTechnique, weight);
    }

    private void setFlourType(String flourType) {
        if (!Ingredients.FLOUR_TYPE.containsKey(flourType)) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }

        this.flourType = flourType;
    }

    private void setBakingTechnique(String bakingTechnique) {
        if (!Ingredients.BAKING_TECHNIQUE.containsKey(bakingTechnique)) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }

        this.bakingTechnique = bakingTechnique;
    }

    private void setWeight(double weight) {
        if (1 > weight || weight > 200) {
            throw new IllegalArgumentException
                    ("Dough weight should be in the range [1..200].");
        }

        this.weight = weight;
    }

    public double calculateCalories() {
        return 2 * this.weight
                * Ingredients.BAKING_TECHNIQUE.get(this.bakingTechnique)
                * Ingredients.FLOUR_TYPE.get(this.flourType);
    }
}
