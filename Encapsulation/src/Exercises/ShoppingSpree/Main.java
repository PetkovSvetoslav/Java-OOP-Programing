package Exercises.ShoppingSpree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Person> people = new LinkedHashMap<>();
        String[] peopleData = reader.readLine().split(";");
        for (int i = 0; i < peopleData.length; i++) {
            String[] tokens = peopleData[i].split("=");
            String name = tokens[0];
            double money = Double.parseDouble(tokens[1]);

            Person person = new Person(name, money);
            people.put(name, person);
        }

        Map<String, Product> products = new LinkedHashMap<>();
        String[] productData = reader.readLine().split(";");
        for (int i = 0; i < productData.length; i++) {
            String[] tokens = productData[i].split("=");
            String name = tokens[0];
            double cost = Double.parseDouble(tokens[1]);

            Product product = new Product(name, cost);
            products.put(name, product);
        }

        String input = reader.readLine();
        while (!"END".equals(input)) {
            String[] tokens = input.split("\\s+");
            String personName = tokens[0];
            String productName = tokens[1];

            try {
                people.get(personName).buyProduct(products.get(productName));
                System.out.println(personName + " bought " + productName);
            } catch (IllegalStateException exception) {
                System.out.println(exception.getMessage());
            }

            input = reader.readLine();
        }

        for (Person person : people.values()) {
            System.out.println(person);
        }
    }
}
