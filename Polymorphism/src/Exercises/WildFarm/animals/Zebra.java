package Exercises.WildFarm.animals;

import Exercises.WildFarm.food.Food;

public class Zebra extends Mammal {
    public Zebra(String animalName, String animalType, Double animalWeight,
                 String livingRegion) {

        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void eat(Food food) {
        if (food.getType().equals("Meat")) {
            throw new IllegalStateException("Zebras are not eating that type of food!");
        }
        super.eat(food);
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }
}
