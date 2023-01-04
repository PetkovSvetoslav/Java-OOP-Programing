package Exercises.PizzaCalories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Pizza pizza = Pizza.parsePizza(reader.readLine());
        Dough dough = Dough.parseDough(reader.readLine());
        pizza.setDough(dough);

        String toppingData;
        while (!"END".equals(toppingData = reader.readLine())) {
            Topping topping = Topping.parseTopping(toppingData);
            pizza.addTopping(topping);
        }

        System.out.printf("%s - %.2f", pizza.getName(), pizza.getOverallCalories());
    }
}
