package Exercises.WildFarm.animals;

public abstract class Mammal extends Animal {
    private String livingRegion;

    protected Mammal(String animalName, String animalType, Double animalWeight,
                     String livingRegion) {

        super(animalName, animalType, animalWeight);
        this.livingRegion = livingRegion;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder(super.toString());

        out.insert(out.lastIndexOf(",") + 1, " " + this.livingRegion + ",");

        return out.toString();
    }
}
