package Exercises.WildFarm;

import Exercises.WildFarm.animals.*;
import Exercises.WildFarm.food.Food;
import Exercises.WildFarm.food.Meat;
import Exercises.WildFarm.food.Vegetable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Animal> animals = new ArrayList<>();

        String line;
        while (!"End".equals(line = reader.readLine())) {

            String[] animalTokens = line.split("\\s+");
            String type = animalTokens[0];
            String name = animalTokens[1];
            double weight = Double.parseDouble(animalTokens[2]);
            String region = animalTokens[3];

            Animal animal;
            switch (type) {
                case "Cat":
                    String breed = animalTokens[4];
                    animal = new Cat(name, type, weight, region, breed);
                    break;
                case "Mouse":
                    animal = new Mouse(name, type, weight, region);
                    break;
                case "Zebra":
                    animal = new Zebra(name, type, weight, region);
                    break;
                case "Tiger":
                    animal = new Tiger(name, type, weight, region);
                    break;
                default:
                    continue;
            }
            animals.add(animal);

            animal.makeSound();

            String[] foodTokens = reader.readLine().split("\\s+");
            String foodType = foodTokens[0];
            int quantity = Integer.parseInt(foodTokens[1]);

            Food food = foodType.equals("Meat")
                    ? new Meat(quantity)
                    : new Vegetable(quantity);

            try {
                animal.eat(food);
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }

        animals.forEach(System.out::println);
    }
}
