package Exercises.WildFarm.animals;

import Exercises.WildFarm.food.Food;

public class Tiger extends Felime {
    public Tiger(String animalName, String animalType, Double animalWeight,
                 String livingRegion) {

        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void eat(Food food) {
        if (!food.getType().equals("Meat")) {
            throw new IllegalStateException("Tigers are not eating that type of food!");
        }
        super.eat(food);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }
}
