package Exercises.WildFarm.animals;

import Exercises.WildFarm.food.Food;

import java.text.DecimalFormat;

public abstract class Animal implements ProduceSound, Eat {
    DecimalFormat formatter = new DecimalFormat("#.##");

    private String animalName;
    private String animalType;
    private Double animalWeight;
    private Integer foodEaten;

    Animal(String animalName, String animalType, Double animalWeight) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.animalWeight = animalWeight;
        this.foodEaten = 0;
    }

    @Override
    public void eat(Food food) {
        this.foodEaten += food.getQuantity();
    }

    @Override
    public String toString() {
        return String.format("%s[%s, %s, %d]"
                , this.animalType, this.animalName, formatter.format(this.animalWeight), this.foodEaten);
    }
}
